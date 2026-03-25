package fr.afpa.jakartaee_cyril1.auth;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Filtre d'authentification.
 *
 * <p>Laisse passer certaines commandes publiques (listes + vues),
 * mais bloque toutes les autres si l'utilisateur n'est pas connecté.</p>
 */
@WebFilter("/FrontController")
public class AuthFilter implements Filter {

    /** Commandes accessibles sans être connecté. */
    private static final Set<String> PUBLIC_CMDS = Set.of(
            "login",
            "accueil",
            "clientListe",
            "clientView",
            "prospectListe",
            "prospectView"
    );

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);

        String cmd = request.getParameter("cmd");

        if (cmd == null) {
            cmd = "accueil";
        }

        // Commande publique → OK
        if (PUBLIC_CMDS.contains(cmd)) {
            chain.doFilter(req, res);
            return;
        }

        // Utilisateur connecté → OK
        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(req, res);
            return;
        }

        // Sinon → message + redirection vers login
        request.setAttribute("error",
                "Vous devez être connecté pour accéder à cette page.");
        request.getRequestDispatcher("/WEB-INF/jsp/auth/Login.jsp")
                .forward(req, res);
    }
}
