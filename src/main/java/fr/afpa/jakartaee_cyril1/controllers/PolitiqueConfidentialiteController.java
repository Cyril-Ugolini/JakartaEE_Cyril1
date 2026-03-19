package fr.afpa.jakartaee_cyril1.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher la politique de confidentialité.
 *
 * @author Cyril
 * @version 1.0
 */
public final class PolitiqueConfidentialiteController implements ICommand {

    /** Logger du contrôleur. */
    private static final Logger LOG =
            Logger.getLogger(PolitiqueConfidentialiteController.class.getName());

    /**
     * Exécute la commande et renvoie la page de politique de confidentialité.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la JSP
     */
    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) {
        LOG.info("Affichage de la politique de confidentialité.");
        return "/WEB-INF/jsp/PolitiqueConfidentialite.jsp";
    }
}
