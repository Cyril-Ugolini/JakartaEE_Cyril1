package fr.afpa.jakartaee_cyril1.servlet;

import fr.afpa.jakartaee_cyril1.controllers.InitAdminController;
import fr.afpa.jakartaee_cyril1.auth.LoginController;
import fr.afpa.jakartaee_cyril1.auth.LogoutController;
import fr.afpa.jakartaee_cyril1.clients.ClientFormController;
import fr.afpa.jakartaee_cyril1.clients.ClientListeController;
import fr.afpa.jakartaee_cyril1.clients.ClientSuppressionController;
import fr.afpa.jakartaee_cyril1.clients.ClientViewController;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import fr.afpa.jakartaee_cyril1.controllers.MentionsLegalesController;
import fr.afpa.jakartaee_cyril1.controllers.PageAccueilController;
import fr.afpa.jakartaee_cyril1.controllers
        .PolitiqueConfidentialiteController;
import fr.afpa.jakartaee_cyril1.controllers.TemplateController;
import fr.afpa.jakartaee_cyril1.prospects.ProspectFormController;
import fr.afpa.jakartaee_cyril1.prospects.ProspectListeController;
import fr.afpa.jakartaee_cyril1.prospects
        .ProspectSuppressionController;
import fr.afpa.jakartaee_cyril1.prospects.ProspectViewController;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FrontController centralisant l'ensemble des requêtes.
 *
 * <p>Implémente le pattern Front Controller : toutes les requêtes
 * transitent par ce point unique, qui délègue le traitement à la
 * commande appropriée via le pattern Command.</p>
 *
 * <p>Chaque action est représentée par une implémentation de
 * {@link ICommand}. Le FrontController sélectionne la commande
 * en fonction du paramètre {@code cmd}.</p>
 */
@WebServlet(name = "FrontController",
        value = "/FrontController")
public final class FrontController extends HttpServlet {

    /** Logger du FrontController. */
    private static final Logger LOG =
            Logger.getLogger(FrontController.class.getName());

    /** Map associant une commande à son contrôleur. */
    private final Map<String, ICommand> commands =
            new HashMap<>();

    // ============================================================
    // INITIALISATION
    // ============================================================

    /** Initialise le FrontController. */
    @Override
    public void init() {
        LOG.info("Initialisation du FrontController…");

        commands.put(null, new PageAccueilController());
        commands.put("accueil", new PageAccueilController());
        commands.put("template", new TemplateController());

        // Clients
        commands.put("clientForm", new ClientFormController());
        commands.put("clientView", new ClientViewController());
        commands.put("clientListe", new ClientListeController());
        commands.put("clientSuppression",
                new ClientSuppressionController());

        // Prospects
        commands.put("prospectForm", new ProspectFormController());
        commands.put("prospectView", new ProspectViewController());
        commands.put("prospectListe",
                new ProspectListeController());
        commands.put("prospectSuppression",
                new ProspectSuppressionController());

        // Authentification
        commands.put("login", new LoginController());
        commands.put("logout", new LogoutController());

        // Pages légales
        commands.put("mentionsLegales",
                new MentionsLegalesController());
        commands.put("confidentialite",
                new PolitiqueConfidentialiteController());

        // Initialisation admin
        commands.put("initAdmin", new InitAdminController());

        LOG.info("FrontController initialisé avec "
                + commands.size() + " commandes.");
    }

    // ============================================================
    // DESTRUCTION
    // ============================================================

    /** Libère les ressources. */
    @Override
    public void destroy() {
        LOG.info("Destruction du FrontController.");
        commands.clear();
    }

    // ============================================================
    // TRAITEMENT CENTRALISÉ
    // ============================================================

    /**
     * Traite la requête en déléguant à la commande appropriée.
     *
     * @param request requête HTTP
     * @param response réponse HTTP
     * @throws IOException erreur d'E/S
     */
    protected void processRequest(
            final HttpServletRequest request,
            final HttpServletResponse response)
            throws IOException {

        String urlSuite = "";

        try {
            final String cmd = request.getParameter("cmd");

            LOG.info("Commande reçue : " + cmd);

            final ICommand com = commands.get(cmd);

            if (com == null) {
                LOG.warning("Commande inconnue : " + cmd);
                urlSuite = "/erreur.jsp";
            } else {
                LOG.info("Exécution : "
                        + com.getClass().getSimpleName());
                urlSuite = com.execute(request, response);
            }

        } catch (Exception e) {
            LOG.log(Level.SEVERE,
                    "Erreur dans le FrontController", e);
            urlSuite = "/erreur.jsp";
        }

        try {
            // Redirection
            if (urlSuite != null
                    && urlSuite.startsWith("redirect:")) {

                final String target =
                        urlSuite.substring(
                                "redirect:".length());

                LOG.info("Redirection vers : " + target);

                response.sendRedirect(target);
                return;
            }

            // Forward
            LOG.info("Forward vers : " + urlSuite);

            request.getRequestDispatcher(urlSuite)
                    .forward(request, response);

        } catch (Exception e) {
            LOG.log(Level.SEVERE,
                    "Erreur lors du forward", e);
        }
    }

    // ============================================================
    // MÉTHODES HTTP
    // ============================================================

    /** Traite les requêtes GET. */
    @Override
    protected void doGet(
            final HttpServletRequest request,
            final HttpServletResponse response)
            throws IOException {

        processRequest(request, response);
    }

    /** Traite les requêtes POST. */
    @Override
    protected void doPost(
            final HttpServletRequest request,
            final HttpServletResponse response)
            throws IOException {

        processRequest(request, response);
    }
}
