package fr.afpa.jakartaee_cyril1.prospects;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller chargé d'afficher la page de suppression d'un prospect.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque la commande
 * {@code cmd=prospectSuppression} est reçue. Il renvoie simplement la vue JSP
 * permettant à l'utilisateur de sélectionner ou confirmer la suppression
 * d'un prospect.</p>
 *
 * <p>Aucun traitement métier n'est effectué ici. La logique de suppression
 * pourra être ajoutée ultérieurement dans un contrôleur dédié ou dans une
 * couche service/DAO.</p>
 *
 * @version 1.0
 * @author Cyril
 */
public class ProspectSuppressionController implements ICommand {

    /**
     * Exécute la commande et renvoie la page JSP de suppression d'un prospect.
     *
     * @param request  l'objet {@link HttpServletRequest} contenant les informations de la requête HTTP
     * @param response l'objet {@link HttpServletResponse} permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher : {@code /WEB-INF/jsp/prospects/ProspectSuppression.jsp}
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "WEB-INF/jsp/prospects/ProspectSuppression.jsp";
    }
}