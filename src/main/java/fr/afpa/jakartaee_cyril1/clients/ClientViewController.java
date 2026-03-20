package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import fr.afpa.jakartaee_cyril1.DAO.ClientDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import models.Client;

/**
 * Contrôleur chargé d'afficher la visualisation détaillée d'un client.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque
 * la commande {@code cmd=clientView} est reçue. Il renvoie la
 * vue JSP affichant les informations complètes d'un client.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ClientViewController implements ICommand {

    /** Logger du ClientViewController. */
    private static final Logger LOG =
            Logger.getLogger(ClientViewController.class.getName());

    /**
     * Exécute la commande et renvoie les détails d'un client.
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
        LOG.info("Affichage des détails d'un client.");
        try {
            final String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isBlank()) {
                final ClientDao dao = new ClientDao();
                final Client client =
                        dao.findById(Integer.parseInt(idParam));
                request.setAttribute("client", client);
            }
            return "/WEB-INF/jsp/clients/ClientView.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans ClientViewController : "
                    + e.getMessage());
            throw e;
        }
    }
}
