package fr.afpa.jakartaee_cyril1.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Contrôleur chargé d'afficher le template de l'application.
 *
 * @author Cyril
 * @version 1.0
 */
public final class TemplateController implements ICommand {

    /**
     * Retourne le chemin vers le template JSP.
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la vue : /WEB-INF/jsp/Template.jsp
     * @throws Exception en cas d'erreur interne
     */
    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {
        return "/WEB-INF/jsp/Template.jsp";
    }
}
