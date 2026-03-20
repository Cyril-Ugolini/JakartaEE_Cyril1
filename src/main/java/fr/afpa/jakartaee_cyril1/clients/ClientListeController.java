package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.DAO.ClientDao;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;
import models.Client;

/**
 * Contrôleur chargé d'afficher la liste complète des clients.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque
 * la commande {@code cmd=clientListe} est reçue. Il récupère
 * la liste des clients via la DAO et la passe à la JSP.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ClientListeController implements ICommand {

    /** Logger du ClientListeController. */
    private static final Logger LOG =
            Logger.getLogger(ClientListeController.class.getName());

    /**
     * Exécute la commande et renvoie la liste des clients.
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
        LOG.info("Affichage de la liste des clients.");
        try {
            final ClientDao clientDao = new ClientDao();
            final List<Client> clients = clientDao.findAll();
            request.setAttribute("clients", clients);
            LOG.info("Nombre de clients chargés : "
                    + clients.size());
            return "/WEB-INF/jsp/clients/ClientListe.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans ClientListeController : "
                    + e.getMessage());
            throw e;
        }
    }
}
