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
 * @version 1.1
 */
public final class ClientViewController implements ICommand {

    /** Logger du ClientViewController. */
    private static final Logger LOG =
            Logger.getLogger(ClientViewController.class.getName());

    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        LOG.info("Affichage des détails d'un client.");

        try {
            //  Correction : on lit bien idClient
            final String idParam = request.getParameter("idClient");

            if (idParam != null && !idParam.isBlank()) {

                int id = Integer.parseInt(idParam);
                LOG.info("Chargement du client ID=" + id);

                ClientDao dao = new ClientDao();
                Client client = dao.findById(id);

                request.setAttribute("client", client);
            } else {
                LOG.warning("Aucun idClient reçu dans la requête.");
            }

            return "/WEB-INF/jsp/clients/ClientView.jsp";

        } catch (Exception e) {
            LOG.severe("Erreur dans ClientViewController : " + e.getMessage());
            throw e;
        }
    }
}
