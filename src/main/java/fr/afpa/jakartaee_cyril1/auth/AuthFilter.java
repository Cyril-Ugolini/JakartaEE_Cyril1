package fr.afpa.jakartaee_cyril1.auth;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Filtre d'authentification.
 *
 * <p>Ce filtre vérifie si l'utilisateur est connecté avant d'accéder
 * aux commandes protégées. Certaines commandes publiques restent
 * accessibles sans authentification.</p>
 */
@WebFilter("/FrontController")
public final class AuthFilter implements Filter {

    /** Logger du filtre. */
    private static final Logger LOG =
            Logger.getLogger(AuthFilter.class.getName());

    /** Commandes accessibles sans authentification. */
    private static final Set<String> PUBLIC_CMDS = Set.of(
            "login",
            "accueil",
            "clientListe",
            "clientView",
            "prospectListe",
            "prospectView",
            "initAdmin",
            "template"
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

    @Override
    public void init(final FilterConfig filterConfig) {
        // Rien à initialiser
    }

    @Override
    public void destroy() {
        // Rien à détruire
    }
}
