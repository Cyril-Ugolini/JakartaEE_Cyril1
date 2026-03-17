package fr.afpa.jakartaee_cyril1.auth;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher la page de déconnexion.
 *
 * <p>Dans cette version (OPTION A), aucune session serveur
 * n'est utilisée : l'état de connexion est simulé côté client
 * via localStorage. Ce contrôleur affiche la page Logout.jsp
 * qui demande confirmation et supprime localStorage.</p>
 *
 * <p>Commande associée : {@code cmd=logout}</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class LogoutController implements ICommand {

    /** Logger du LogoutController. */
    private static final Logger LOG =
            Logger.getLogger(LogoutController.class.getName());

    /**
     * Renvoie la page de confirmation de déconnexion.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la vue : /WEB-INF/jsp/auth/Logout.jsp
     * @throws Exception en cas d'erreur interne
     */
    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {
        LOG.info("Affichage de la page de déconnexion.");
        try {
            return "/WEB-INF/jsp/auth/Logout.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans LogoutController : "
                    + e.getMessage());
            throw e;
        }
    }
}
