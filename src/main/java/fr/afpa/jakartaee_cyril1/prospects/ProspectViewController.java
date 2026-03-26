package fr.afpa.jakartaee_cyril1.prospects;

import fr.afpa.jakartaee_cyril1.DAO.ProspectDao;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import fr.afpa.jakartaee_cyril1.models.Prospect;

/**
 * Contrôleur chargé d'afficher la visualisation détaillée
 * d'un prospect.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque
 * la commande {@code cmd=prospectView} est reçue. Il renvoie
 * la vue JSP affichant les informations complètes d'un
 * prospect sélectionné.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ProspectViewController implements ICommand {

    /** Logger du ProspectViewController. */
    private static final Logger LOG =
            Logger.getLogger(ProspectViewController.class.getName());

    /**
     * Exécute la commande et renvoie les détails d'un prospect.
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
        LOG.info("Affichage des détails d'un prospect.");
        try {
            final String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isBlank()) {
                final ProspectDao dao = new ProspectDao();
                final Prospect prospect =
                        dao.findById(Integer.parseInt(idParam));
                request.setAttribute("prospect", prospect);
            }
            return "/WEB-INF/prospects/ProspectView.jsp";
        } catch (Exception e) {
            LOG.severe("Erreur dans ProspectViewController : "
                    + e.getMessage());
            throw e;
        }
    }
}
