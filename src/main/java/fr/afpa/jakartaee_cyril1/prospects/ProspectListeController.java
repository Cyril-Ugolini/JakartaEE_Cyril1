package fr.afpa.jakartaee_cyril1.prospects;

import fr.afpa.jakartaee_cyril1.DAO.ProspectDao;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;
import fr.afpa.jakartaee_cyril1.models.Prospect;

/**
 * Contrôleur chargé d'afficher la liste complète des prospects.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque
 * la commande {@code cmd=prospectListe} est reçue. Il récupère
 * la liste des prospects via la DAO et la passe à la JSP.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ProspectListeController implements ICommand {

    /** Logger du ProspectListeController. */
    private static final Logger LOG =
            Logger.getLogger(ProspectListeController.class.getName());

    /**
     * Exécute la commande et renvoie la liste des prospects.
     *
     * @param request  l'objet {@link HttpServletRequest}
     *                 contenant les informations de la requête HTTP
     * @param response l'objet {@link HttpServletResponse}
     *                 permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {
        LOG.info("Affichage de la liste des prospects.");
        try {
            final ProspectDao prospectDao = new ProspectDao();
            final List<Prospect> prospects =
                    prospectDao.findAll();
            request.setAttribute("prospects", prospects);
            LOG.info("Nombre de prospects chargés : "
                    + prospects.size());
            return "/WEB-INF/jsp/prospects/ProspectListe.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans ProspectListeController : "
                    + e.getMessage());
            throw e;
        }
    }
}
