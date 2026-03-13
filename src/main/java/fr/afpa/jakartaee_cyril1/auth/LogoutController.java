package fr.afpa.jakartaee_cyril1.auth;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Contrôleur chargé d'afficher la page de déconnexion.
 *
 * <p>Dans cette version (OPTION A), aucune session serveur n'est utilisée :
 * l'état de connexion est simulé côté client via localStorage.
 * Ce contrôleur se contente donc d'afficher la page Logout.jsp,
 * qui demande confirmation et supprime localStorage côté client.</p>
 *
 * <p>Commande associée : {@code cmd=logout}</p>
 *
 * @author Cyril
 * @version 1.0
 */
public class LogoutController implements ICommand {

    /**
     * Renvoie la page de confirmation de déconnexion.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la vue : /WEB-INF/jsp/Logout.jsp
     * @throws Exception en cas d'erreur interne
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/WEB-INF/jsp/auth/Logout.jsp";
    }
}