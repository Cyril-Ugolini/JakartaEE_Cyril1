package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher le formulaire
 * de création ou de modification d'un client.
 *
 * <p>Ce contrôleur est appelé via le FrontController
 * lorsque la commande {@code cmd=clientForm} est reçue.
 * Il se contente de renvoyer la vue JSP correspondante,
 * sans effectuer de traitement métier.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ClientFormController implements ICommand {

    /** Logger du ClientFormController. */
    private static final Logger LOG =
            Logger.getLogger(ClientFormController.class.getName());

    /**
     * Exécute la commande et renvoie le formulaire client.
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
        LOG.info("Affichage du formulaire client.");
        try {
            return "/WEB-INF/jsp/clients/ClientForm.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans ClientFormController : "
                    + e.getMessage());
            throw e;
        }
    }
}
