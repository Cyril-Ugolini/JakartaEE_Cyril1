package fr.afpa.jakartaee_cyril1.prospects;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller chargé d'afficher le formulaire de création ou de modification d'un prospect.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque la commande
 * {@code cmd=prospectForm} est reçue. Il renvoie simplement la vue JSP
 * contenant le formulaire permettant de saisir ou modifier les informations
 * d'un prospect.</p>
 *
 * <p>Aucun traitement métier n'est effectué ici. La logique de validation
 * ou d'enregistrement pourra être ajoutée ultérieurement dans un contrôleur
 * dédié ou dans une couche service/DAO.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public class ProspectFormController implements ICommand {

    /**
     * Exécute la commande et renvoie la page JSP du formulaire prospect.
     *
     * @param request  l'objet {@link HttpServletRequest} contenant les données de la requête HTTP
     * @param response l'objet {@link HttpServletResponse} permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher : {@code /WEB-INF/jsp/prospects/ProspectForm.jsp}
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "WEB-INF/jsp/prospects/ProspectForm.jsp";
    }
}