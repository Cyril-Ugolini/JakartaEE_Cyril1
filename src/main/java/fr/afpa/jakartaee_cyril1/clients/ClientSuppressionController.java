package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.DAO.ClientDao;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Client;
import java.util.logging.Logger;

public final class ClientSuppressionController implements ICommand {

    private static final Logger LOG =
            Logger.getLogger(ClientSuppressionController.class.getName());

    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        LOG.info("ClientSuppressionController exécuté.");
        ClientDao clientDao = new ClientDao();

        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);

        // POST = confirmation de suppression
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            clientDao.delete(id);
            LOG.info("Client supprimé : ID=" + id);
            return "FrontController?cmd=clientListe";
        }

        // GET = affichage de la page de confirmation
        Client client = clientDao.findById(id);
        request.setAttribute("client", client);
        return "/WEB-INF/jsp/clients/ClientSuppression.jsp";
    }
}
