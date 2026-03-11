package fr.afpa.jakartaee_cyril1.prospects;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller chargé d'afficher la page de visualisation détaillée d'un prospect.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque la commande
 * {@code cmd=prospectView} est reçue. Il renvoie simplement la vue JSP
 * permettant d'afficher les informations complètes d'un prospect sélectionné.</p>
 *
 * <p>La récupération des données du prospect (via un identifiant passé en paramètre)
 * pourra être ajoutée ultérieurement lorsque la couche DAO ou service sera intégrée.</p>
 *
 * @version 1.0
 * @author Cyril
 */
public class ProspectViewController implements ICommand {

    /**
     * Exécute la commande et renvoie la page JSP affichant les détails d'un prospect.
     *
     * @param request  l'objet {@link HttpServletRequest} contenant les informations de la requête HTTP
     * @param response l'objet {@link HttpServletResponse} permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher : {@code /WEB-INF/jsp/prospects/ProspectView.jsp}
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "WEB-INF/jsp/prospects/ProspectView.jsp";
    }
}