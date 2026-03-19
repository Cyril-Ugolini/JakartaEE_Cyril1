package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.DAO.ClientDao;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;
import models.Client;

public final class ClientListeController implements ICommand {

    private static final Logger LOG =
            Logger.getLogger(ClientListeController.class.getName());

    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {
        LOG.info("Affichage de la liste des clients.");
        try {
            ClientDao clientDao = new ClientDao();
            List<Client> clients = clientDao.findAll();
            request.setAttribute("clients", clients);
            LOG.info("Nombre de clients chargés : " + clients.size());
            return "/WEB-INF/jsp/clients/ClientListe.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans ClientListeController : "
                    + e.getMessage());
            throw e;
        }
    }
}
