package fr.afpa.jakartaee_cyril1.auth;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import fr.afpa.jakartaee_cyril1.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * Contrôleur de déconnexion.
 *
 * <p>Workflow :
 * GET  → affichage de la page de confirmation de déconnexion.
 * POST → invalidation de la session et redirection vers le login.</p>
 *
 * <p>Commande : cmd=logout</p>
 *
 * @author UGOLINI Cyril
 * @version 0.0.3
 * @since 24/03/2026
 */
public final class LogoutController implements ICommand {

    /** Logger du contrôleur. */
    private static final Logger LOG =
            Logger.getLogger(LogoutController.class.getName());

    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws Exception {

        LOG.info("LogoutController exécuté.");

        // --------------------------------------------------------
        // GET → affichage de la page de confirmation
        // --------------------------------------------------------
        if ("GET".equalsIgnoreCase(request.getMethod())) {

            HttpSession session = request.getSession(false);

            if (session == null
                    || session.getAttribute("user") == null) {
                LOG.warning("Logout GET sans session active.");
                return "redirect:FrontController?cmd=login";
            }

            return "/WEB-INF/jsp/auth/Logout.jsp";
        }

        // --------------------------------------------------------
        // POST → invalidation de la session
        // --------------------------------------------------------
        HttpSession session = request.getSession(false);

        if (session != null) {

            // Récupération du user AVANT invalidation
            User user = (User) session.getAttribute("user");
            String username = (user != null)
                    ? user.getUsername() : "inconnu";

            LOG.info("Déconnexion de : " + username);

            // Retrait du registre des utilisateurs connectés
            if (user != null) {
                UserRegistry.remove(username);
                LOG.info("Utilisateur retiré du registre : " + username);
            }

            try {
                session.invalidate();
                LOG.info("Session invalidée.");
            } catch (Exception e) {
                LOG.warning("Erreur invalidation session : "
                        + e.getMessage());
            }

        } else {
            LOG.warning("Logout POST sans session active.");
        }

        return "redirect:FrontController?cmd=accueil";
    }
}
