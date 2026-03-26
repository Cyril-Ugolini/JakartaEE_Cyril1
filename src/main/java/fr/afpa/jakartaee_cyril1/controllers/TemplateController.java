package fr.afpa.jakartaee_cyril1.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher le template de l'application.
 */
public final class TemplateController implements ICommand {

    /** Logger du TemplateController. */
    private static final Logger LOG =
            Logger.getLogger(TemplateController.class.getName());

    /**
     * Retourne le chemin vers le template JSP.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la vue : /WEB-INF/jsp/Template.jsp
     * @throws Exception en cas d'erreur interne
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws Exception {

        LOG.info("Affichage du template.");

        try {
            return "/WEB-INF/jsp/Template.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans TemplateController : "
                    + e.getMessage());
            throw e;
        }
    }
}
