package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller chargé d'afficher le formulaire de création ou de modification d'un client.
 *
 * <p>Ce contrôleur est appelé via le FrontController lorsque la commande
 * {@code cmd=clientForm} est reçue. Il se contente de renvoyer la vue JSP
 * correspondante, sans effectuer de traitement métier.</p>
 *
 * <p>La JSP retournée contient le formulaire permettant à l'utilisateur
 * de saisir ou modifier les informations d'un client.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public class ClientFormController implements ICommand {

    /**
     * Exécute la commande et renvoie la page JSP du formulaire client.
     *
     * @param request  l'objet {@link HttpServletRequest}
     *                 contenant les données de la requête HTTP
     * @param response l'objet {@link HttpServletResponse}
     *                 permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher :
     * {@code /WEB-INF/jsp/clients/ClientForm.jsp}
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception {
        return "/WEB-INF/jsp/clients/ClientForm.jsp";
    }
}
