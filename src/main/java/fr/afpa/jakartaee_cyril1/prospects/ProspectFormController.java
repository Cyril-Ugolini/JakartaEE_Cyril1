package fr.afpa.jakartaee_cyril1.prospects;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher le formulaire
 * de création ou de modification d'un prospect.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque
 * la commande {@code cmd=prospectForm} est reçue. Il renvoie
 * la vue JSP contenant le formulaire prospect.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ProspectFormController implements ICommand {

    /** Logger du ProspectFormController. */
    private static final Logger LOG =
            Logger.getLogger(ProspectFormController.class.getName());

    /**
     * Exécute la commande et renvoie le formulaire prospect.
     *
     * @param request  l'objet {@link HttpServletRequest}
     *                 contenant les données de la requête HTTP
     * @param response l'objet {@link HttpServletResponse}
     *                 permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {
        LOG.info("Affichage du formulaire prospect.");
        try {
            return "/WEB-INF/jsp/prospects/ProspectForm.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans ProspectFormController : "
                    + e.getMessage());
            throw e;
        }
    }
}
