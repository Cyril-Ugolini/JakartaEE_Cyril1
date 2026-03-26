package fr.afpa.jakartaee_cyril1.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher la page d'accueil de l'application.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque
 * la commande {@code cmd=accueil} est reçue. Il renvoie la vue
 * JSP correspondant à la page d'accueil du site.</p>
 */
public final class PageAccueilController implements ICommand {

    /** Logger du PageAccueilController. */
    private static final Logger LOG =
            Logger.getLogger(PageAccueilController.class.getName());

    /**
     * Exécute la commande et renvoie la page d'accueil.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la JSP à afficher
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws Exception {

        LOG.info("Affichage de la page d'accueil.");

        try {
            return "/WEB-INF/jsp/Accueil.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans PageAccueilController : "
                    + e.getMessage());
            throw e;
        }
    }
}
