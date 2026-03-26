package fr.afpa.jakartaee_cyril1.auth;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Filtre d'authentification.
 *
 * <p>Autorise certaines commandes publiques (accueil, login, listes…)
 * et bloque toutes les autres si l'utilisateur n'est pas connecté.</p>
 */
@WebFilter("/FrontController")
public class AuthFilter implements Filter {

    /** Logger du filtre. */
    private static final Logger LOG =
            Logger.getLogger(AuthFilter.class.getName());

    /**
     * Commandes accessibles sans authentification.
     */
    private static final Set<String> PUBLIC_CMDS = Set.of(
            "login",
            "accueil",
            "clientListe",
            "clientView",
            "prospectListe",
            "prospectView",
            "initAdmin",
            "template"   // ⚠️ souvent nécessaire pour ton header/footer
    );

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpSession session = request.getSession(false);

        String cmd = request.getParameter("cmd");
        LOG.fine("Commande reçue par le filtre : " + cmd);

        if (cmd == null || cmd.isBlank()) {
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

        // Sinon → redirection vers login
        request.setAttribute("error",
                "Vous devez être connecté pour accéder à cette page.");
        request.getRequestDispatcher("/WEB-INF/jsp/auth/Login.jsp")
                .forward(req, res);
    }
}
