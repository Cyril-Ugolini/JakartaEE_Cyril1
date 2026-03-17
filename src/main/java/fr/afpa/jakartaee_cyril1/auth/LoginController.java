package fr.afpa.jakartaee_cyril1.auth;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher la page de connexion.
 *
 * <p>Ce contrôleur ne réalise aucune logique d'authentification :
 * il se contente d'afficher la vue Login.jsp. La validation
 * et la vérification des identifiants sont gérées côté client
 * dans la page JSP (compte admin fictif pour l'ECF).</p>
 *
 * <p>Commande associée : {@code cmd=login}</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class LoginController implements ICommand {

    /** Logger du LoginController. */
    private static final Logger LOG =
            Logger.getLogger(LoginController.class.getName());

    /**
     * Renvoie simplement la page JSP du formulaire de connexion.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la vue : /WEB-INF/jsp/auth/Login.jsp
     * @throws Exception en cas d'erreur interne
     */
    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {
        LOG.info("Affichage de la page de connexion.");
        try {
            return "/WEB-INF/jsp/auth/Login.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans LoginController : "
                    + e.getMessage());
            throw e;
        }
    }
}
