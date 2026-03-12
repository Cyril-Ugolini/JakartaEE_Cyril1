package fr.afpa.jakartaee_cyril1.servlet;

import fr.afpa.jakartaee_cyril1.auth.LoginController;
import fr.afpa.jakartaee_cyril1.auth.LogoutController;
import fr.afpa.jakartaee_cyril1.clients.ClientFormController;
import fr.afpa.jakartaee_cyril1.clients.ClientListeController;
import fr.afpa.jakartaee_cyril1.clients.ClientSuppressionController;
import fr.afpa.jakartaee_cyril1.clients.ClientViewController;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import fr.afpa.jakartaee_cyril1.controllers.PageAccueilController;
import fr.afpa.jakartaee_cyril1.prospects.ProspectFormController;
import fr.afpa.jakartaee_cyril1.prospects.ProspectListeController;
import fr.afpa.jakartaee_cyril1.prospects.ProspectSuppressionController;
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
 * FrontController centralisant l'ensemble des requêtes de l'application.
 *
 * <p>Ce servlet implémente le pattern Front Controller : toutes les requêtes
 * transitent par ce point unique, qui se charge ensuite de déléguer le
 * traitement à la commande appropriée via le pattern Command.</p>
 *
 * <p>Chaque action de l'application (affichage d'une liste, d'un formulaire,
 * suppression, connexion, etc.) est représentée par une implémentation de
 * {@link ICommand}. Le FrontController sélectionne la commande à exécuter
 * en fonction du paramètre {@code cmd} présent dans la requête.</p>
 *
 * <p>Ce mécanisme permet une architecture modulaire, extensible et facile
 * à maintenir : pour ajouter une nouvelle fonctionnalité, il suffit
 * d'ajouter une nouvelle commande et de l'enregistrer dans la méthode
 * {@link #init()}.</p>
 *
 * @author Cyril
 * @version 1.0
 */
@WebServlet(name = "FrontController", value = "/FrontController")
public class FrontController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(FrontController.class.getName());

    /** Map associant une commande (clé) à son contrôleur (valeur). */
    private Map<String, ICommand> commands = new HashMap<>();

    @Override
    public void init() {

        LOG.info("Initialisation du FrontController…");

        // Accueil
        commands.put(null, new PageAccueilController());
        commands.put("accueil", new PageAccueilController());

        // Clients
        commands.put("clientForm",         new ClientFormController());
        commands.put("clientView",         new ClientViewController());
        commands.put("clientListe",        new ClientListeController());
        commands.put("clientSuppression",  new ClientSuppressionController());

        // Prospects
        commands.put("prospectForm",         new ProspectFormController());
        commands.put("prospectView",         new ProspectViewController());
        commands.put("prospectListe",        new ProspectListeController());
        commands.put("prospectSuppression",  new ProspectSuppressionController());

        // Login / Logout
        commands.put("login",                new LoginController());
        commands.put("logout",               new LogoutController());

        LOG.info("FrontController initialisé avec " + commands.size() + " commandes.");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String urlSuite = "";

        try {
            String cmd = request.getParameter("cmd");
            LOG.info("Commande reçue : " + cmd);

            ICommand com = commands.get(cmd);

            if (com == null) {
                LOG.warning("Commande inconnue : " + cmd);
                urlSuite = "/WEB-INF/jsp/erreur.jsp";
            } else {
                LOG.info("Exécution du contrôleur : " + com.getClass().getSimpleName());
                urlSuite = com.execute(request, response);
            }

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Erreur dans le FrontController", e);
            urlSuite = "/WEB-INF/jsp/erreur.jsp";

        } finally {
            try {
                LOG.info("Forward vers : " + urlSuite);
                request.getRequestDispatcher(urlSuite).forward(request, response);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Erreur lors du forward", e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}