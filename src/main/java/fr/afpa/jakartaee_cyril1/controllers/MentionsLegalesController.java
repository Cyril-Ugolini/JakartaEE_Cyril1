package fr.afpa.jakartaee_cyril1.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher la page des mentions légales.
 *
 * @author Cyril
 * @version 1.0
 */
public final class MentionsLegalesController implements ICommand {

    /** Logger du contrôleur. */
    private static final Logger LOG =
            Logger.getLogger(MentionsLegalesController.class.getName());

    /**
     * Exécute la commande et renvoie la page des mentions légales.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la JSP
     */
    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) {
        LOG.info("Affichage des mentions légales.");
        return "/WEB-INF/jsp/mentionsLegales.jsp";
    }
}
