package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        return "/WEB-INF/jsp/clients/ClientView.jsp";
    }
}
