package fr.afpa.jakartaee_cyril1.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller chargé d'afficher la page d'accueil de l'application.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque la commande
 * {@code cmd=accueil} (ou toute commande configurée pour pointer vers lui)
 * est reçue. Il renvoie simplement la vue JSP correspondant à la page
 * d'accueil du site.</p>
 *
 * <p>Cette page constitue généralement le point d'entrée de l'application
 * et peut contenir des liens vers les différentes fonctionnalités
 * (clients, prospects, etc.).</p>
 *
 * @author Cyril
 * @version 1.0
 */
public class PageAccueilController implements ICommand {

    /**
     * Exécute la commande et renvoie la page JSP d'accueil.
     *
     * @param request  l'objet {@link HttpServletRequest}
     *                 contenant les données de la requête HTTP
     * @param response l'objet {@link HttpServletResponse}
     *                 permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher :
     * {@code index.jsp}
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception {
        return "/WEB-INF/jsp/Accueil.jsp";
    }
}
