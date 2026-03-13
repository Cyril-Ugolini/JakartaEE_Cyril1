package fr.afpa.jakartaee_cyril1.auth;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Contrôleur chargé d'afficher la page de connexion.
 *
 * <p>Ce contrôleur ne réalise aucune logique d'authentification :
 * il se contente d'afficher la vue Login.jsp. La validation
 * et la vérification des identifiants sont gérées côté client
 * dans la page JSP (compte admin fictif pour l'ECF).</p>
 *
 * <p>Commande associée : {@code cmd=login}</p>
 *
 * @author Cyril
 * @version 1.0
 */
public class LoginController implements ICommand {

    /**
     * Renvoie simplement la page JSP du formulaire de connexion.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la vue : /WEB-INF/jsp/Login.jsp
     * @throws Exception en cas d'erreur interne
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/WEB-INF/jsp/auth/Login.jsp";
    }
}