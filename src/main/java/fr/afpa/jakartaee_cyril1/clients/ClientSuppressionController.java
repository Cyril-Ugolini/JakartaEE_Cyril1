package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller chargé d'afficher la page de suppression d'un client.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque la commande
 * {@code cmd=clientSuppression} est reçue. Il renvoie simplement la vue JSP
 * permettant à l'utilisateur de sélectionner ou confirmer la suppression
 * d'un client.</p>
 *
 * <p>Aucun traitement métier n'est effectué ici. La logique de suppression
 * pourra être ajoutée ultérieurement dans un contrôleur dédié ou dans une DAO.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public class ClientSuppressionController implements ICommand {

    /**
     * Exécute la commande et renvoie la page JSP de suppression d'un client.
     *
     * @param request  l'objet {@link HttpServletRequest} contenant les informations de la requête HTTP
     * @param response l'objet {@link HttpServletResponse} permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher : {@code /WEB-INF/jsp/clients/ClientSuppression.jsp}
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception
    {
        return "WEB-INF/jsp/clients/ClientSuppression.jsp";
    }
}