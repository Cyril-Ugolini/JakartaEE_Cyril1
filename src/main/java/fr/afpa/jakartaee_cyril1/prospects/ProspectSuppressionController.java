package fr.afpa.jakartaee_cyril1.prospects;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Contrôleur chargé d'afficher la page de suppression d'un prospect.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque
 * la commande {@code cmd=prospectSuppression} est reçue. Il
 * renvoie la vue JSP de confirmation de suppression.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ProspectSuppressionController implements ICommand {

    /**
     * Exécute la commande et renvoie la page de suppression.
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
        return "/WEB-INF/jsp/prospects/ProspectSuppression.jsp";
    }
}
