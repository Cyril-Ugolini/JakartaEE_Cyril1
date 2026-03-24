package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.DAO.ClientDao;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Client;

import java.util.logging.Logger;

/**
 * Controller responsible for handling client deletion.
 *
 * <p>Workflow:
 * <ul>
 *     <li>GET  -> Display confirmation page</li>
 *     <li>POST -> Validate CSRF token, delete client, redirect to list</li>
 * </ul>
 *
 * <p>Security:
 * <ul>
 *     <li>CSRF token validation required for POST</li>
 *     <li>Client ID must be provided</li>
 * </ul>
 */
public final class ClientSuppressionController implements ICommand {

    private static final Logger LOG =
            Logger.getLogger(ClientSuppressionController.class.getName());

    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        LOG.info("ClientSuppressionController executed.");

        ClientDao clientDao = new ClientDao();

        // Retrieve client ID
        String idParam = request.getParameter("idClient");
        if (idParam == null) {
            LOG.warning("Missing idClient parameter.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing idClient parameter");
            return null;
        }

        int id = Integer.parseInt(idParam);

        // POST = deletion request
        if ("POST".equalsIgnoreCase(request.getMethod())) {

            // CSRF validation
            String tokenSession = (String) request.getSession().getAttribute("csrfToken");
            String tokenForm = request.getParameter("csrfToken");

            if (tokenSession == null || !tokenSession.equals(tokenForm)) {
                LOG.warning("Invalid CSRF token.");
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF token invalide");
                return null;
            }

            // Delete client
            clientDao.delete(id);
            LOG.info("Client deleted: ID=" + id);

            // Redirect to client list
            return "redirect:FrontController?cmd=clientListe";
        }

        // GET = display confirmation page
        Client client = clientDao.findById(id);
        request.setAttribute("client", client);

        return "/WEB-INF/jsp/clients/ClientSuppression.jsp";
    }
}
