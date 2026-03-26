package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.DAO.ClientDao;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import fr.afpa.jakartaee_cyril1.models.Client;

import java.util.logging.Logger;

/**
 * Contrôleur chargé de gérer la suppression d'un client.
 *
 * <p>Workflow :</p>
 * <ul>
 *     <li>GET  → affichage de la page de confirmation</li>
 *     <li>POST → validation du token CSRF, suppression, redirection</li>
 * </ul>
 *
 * <p>Sécurité :</p>
 * <ul>
 *     <li>Validation CSRF obligatoire pour POST</li>
 *     <li>L'identifiant du client doit être fourni</li>
 * </ul>
 */
public final class ClientSuppressionController implements ICommand {

    /** Logger du contrôleur de suppression client. */
    private static final Logger LOG =
            Logger.getLogger(ClientSuppressionController.class.getName());

    /** DAO permettant la recherche et la suppression de clients. */
    private final ClientDao clientDao;

    /**
     * Constructeur : initialise le DAO client.
     */
    public ClientSuppressionController() {
        try {
            this.clientDao = new ClientDao();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to initialize ClientDao", e);
        }
    }

    /**
     * Traite la requête HTTP pour la suppression d'un client.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la vue ou redirection
     * @throws Exception en cas d’erreur interne
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws Exception {

        LOG.info("ClientSuppressionController executed.");

        // Retrieve client ID
        final String idParam = request.getParameter("idClient");
        if (idParam == null || idParam.isBlank()) {
            LOG.warning("Missing idClient parameter.");
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Missing idClient parameter");
            return null;
        }

        final int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            LOG.warning("Invalid idClient format: " + idParam);
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid idClient format");
            return null;
        }

        // POST = deletion request
        if ("POST".equalsIgnoreCase(request.getMethod())) {

            // CSRF validation
            final String tokenSession =
                    (String) request.getSession().getAttribute("csrfToken");
            final String tokenForm = request.getParameter("csrfToken");

            if (tokenSession == null || !tokenSession.equals(tokenForm)) {
                LOG.warning("Invalid CSRF token.");
                response.sendError(
                        HttpServletResponse.SC_FORBIDDEN,
                        "CSRF token invalide");
                return null;
            }

            // Delete client
            clientDao.delete(id);
            LOG.info("Client deleted: ID=" + id);

            return "redirect:" + request.getContextPath()
                    + "/FrontController?cmd=clientListe";
        }

        // GET = display confirmation page
        final Client client = clientDao.findById(id);
        request.setAttribute("client", client);

        return "/WEB-INF/jsp/clients/ClientSuppression.jsp";
    }
}
