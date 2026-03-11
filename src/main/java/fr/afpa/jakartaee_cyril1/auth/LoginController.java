package fr.afpa.jakartaee_cyril1.auth;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller chargé d'afficher la page de connexion de l'application.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque la commande
 * {@code cmd=login} est reçue. Il renvoie simplement la vue JSP contenant
 * le formulaire de connexion. La logique d'authentification pourra être
 * ajoutée ultérieurement dans une couche service ou dans un contrôleur
 * dédié au traitement du login.</p>
 *
 * <p>Cette page permet à l'utilisateur de saisir ses identifiants afin
 * d'accéder aux fonctionnalités protégées de l'application.</p>
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 11/03/2026
 */
public class LoginController implements ICommand {

    /**
     * Exécute la commande et renvoie la page JSP du formulaire de connexion.
     *
     * @param request  l'objet {@link HttpServletRequest} contenant les données de la requête HTTP
     * @param response l'objet {@link HttpServletResponse} permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher : {@code /WEB-INF/jsp/Login.jsp}
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "WEB-INF/jsp/Login.jsp";
    }
}