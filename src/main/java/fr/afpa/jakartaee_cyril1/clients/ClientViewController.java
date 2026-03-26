package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.DAO.ClientDao;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import fr.afpa.jakartaee_cyril1.models.Client;

/**
 * Contrôleur chargé d'afficher la visualisation détaillée d'un client.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque
 * la commande {@code cmd=clientView} est reçue. Il renvoie la
 * vue JSP affichant les informations complètes d'un client.</p>
 */
public final class ClientViewController implements ICommand {

    /** Logger du ClientViewController. */
    private static final Logger LOG =
            Logger.getLogger(ClientViewController.class.getName());

    /** DAO permettant la récupération des données client. */
    private final ClientDao clientDao;

    /**
     * Constructeur : initialise le DAO client.
     */
    public ClientViewController() {
        try {
            this.clientDao = new ClientDao();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Impossible d'initialiser ClientDao", e);
        }
    }

    /**
     * Affiche les détails d’un client identifié par son id.
     *
     * <p>Vérifie la présence et la validité du paramètre {@code idClient},
     * charge le client correspondant, puis transmet l'objet à la JSP.</p>
     *
     * @param request  requête HTTP contenant l'identifiant du client
     * @param response réponse HTTP
     * @return chemin de la JSP de visualisation ou null en cas d’erreur
     * @throws Exception en cas d’erreur interne
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws Exception {

        LOG.info("Affichage des détails d'un client.");

        try {
            // Lecture du paramètre idClient
            final String idParam = request.getParameter("idClient");

            if (idParam == null || idParam.isBlank()) {
                LOG.warning("Aucun idClient reçu dans la requête.");
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Paramètre idClient manquant");
                return null;
            }

            // Conversion sécurisée
            final int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                LOG.warning("Format idClient invalide : " + idParam);
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Format idClient invalide");
                return null;
            }

            LOG.info("Chargement du client ID=" + id);

            // Récupération du client
            final Client client = clientDao.findById(id);

            if (client == null) {
                LOG.warning("Client introuvable pour ID=" + id);
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Client introuvable");
                return null;
            }

            // Envoi à la JSP
            request.setAttribute("client", client);

            return "/WEB-INF/jsp/clients/ClientView.jsp";

        } catch (Exception e) {
            LOG.severe("Erreur dans ClientViewController : "
                    + e.getMessage());
            throw e;
        }
    }
}
