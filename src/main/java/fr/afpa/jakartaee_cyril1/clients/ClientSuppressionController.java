package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher la page de suppression d'un client.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque
 * la commande {@code cmd=clientSuppression} est reçue. Il renvoie
 * la vue JSP permettant de confirmer la suppression d'un client.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ClientSuppressionController implements ICommand {

    /** Logger du ClientSuppressionController. */
    private static final Logger LOG =
            Logger.getLogger(ClientSuppressionController.class.getName());

    /**
     * Exécute la commande et renvoie la page de suppression.
     *
     * @param request  l'objet {@link HttpServletRequest}
     *                 contenant les informations de la requête HTTP
     * @param response l'objet {@link HttpServletResponse}
     *                 permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {
        LOG.info("Affichage de la page de suppression d'un client.");
        try {
            return "/WEB-INF/jsp/clients/ClientSuppression.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans ClientSuppressionController : "
                    + e.getMessage());
            throw e;
        }
    }
}
