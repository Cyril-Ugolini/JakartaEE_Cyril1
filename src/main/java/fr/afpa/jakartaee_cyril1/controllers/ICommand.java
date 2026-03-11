package fr.afpa.jakartaee_cyril1.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface représentant une commande exécutée par le FrontController.
 *
 * <p>Chaque implémentation de cette interface correspond à une action précise
 * de l'application (affichage d'une liste, d'un formulaire, suppression, etc.).
 * Le FrontController utilise cette interface pour déléguer le traitement
 * à la classe appropriée en fonction de la commande reçue.</p>
 *
 * <p>Le modèle repose sur le pattern Command, permettant une architecture
 * extensible et facilement maintenable.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public interface ICommand {

    /**
     * Exécute la commande associée et renvoie le chemin de la vue à afficher.
     *
     * @param request  l'objet {@link HttpServletRequest} contenant les données de la requête HTTP
     * @param response l'objet {@link HttpServletResponse} permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher (souvent sous {@code /WEB-INF/...})
     * @throws Exception si une erreur survient lors de l'exécution de la commande
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}