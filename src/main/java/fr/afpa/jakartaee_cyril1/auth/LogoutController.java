package fr.afpa.jakartaee_cyril1.auth;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller chargé de gérer la déconnexion de l'utilisateur.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque la commande
 * {@code cmd=logout} est reçue. Il renvoie simplement la vue JSP confirmant
 * la déconnexion. La suppression de la session ou des attributs associés
 * pourra être ajoutée ultérieurement dans une couche service ou directement
 * dans ce contrôleur selon les besoins de l'application.</p>
 *
 * <p>Cette page permet d'informer l'utilisateur qu'il a bien été déconnecté
 * et peut proposer un lien de retour vers la page de connexion.</p>
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 11/03/2026
 */
public class LogoutController implements ICommand {

    /**
     * Exécute la commande et renvoie la page JSP de confirmation de déconnexion.
     *
     * @param request  l'objet {@link HttpServletRequest} contenant les données de la requête HTTP
     * @param response l'objet {@link HttpServletResponse} permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher : {@code /WEB-INF/jsp/Logout.jsp}
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "WEB-INF/jsp/Logout.jsp";
    }
}