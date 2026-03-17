package fr.afpa.jakartaee_cyril1.prospects;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher la liste complète des prospects.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque
 * la commande {@code cmd=prospectListe} est reçue. Il se contente
 * de renvoyer la vue JSP affichant la liste des prospects.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ProspectListeController implements ICommand {

    /** Logger du ProspectListeController. */
    private static final Logger LOG =
            Logger.getLogger(ProspectListeController.class.getName());

    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        LOG.info("Affichage de la liste des prospects.");

        try {
            return "/WEB-INF/jsp/prospects/ProspectListe.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans ProspectListeController : " + e.getMessage());
            throw e;
        }
    }
}
