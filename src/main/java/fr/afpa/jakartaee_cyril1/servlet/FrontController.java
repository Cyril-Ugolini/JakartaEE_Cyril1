package fr.afpa.jakartaee_cyril1.servlet;

import fr.afpa.jakartaee_cyril1.clients.ClientCreationController;
import fr.afpa.jakartaee_cyril1.clients.ClientListeController;
import fr.afpa.jakartaee_cyril1.clients.ClientModificationController;
import fr.afpa.jakartaee_cyril1.clients.ClientSuppressionController;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import fr.afpa.jakartaee_cyril1.controllers.PageAccueilController;
import fr.afpa.jakartaee_cyril1.controllers.clients.*;
import fr.afpa.jakartaee_cyril1.prospects.ProspectCreationController;
import fr.afpa.jakartaee_cyril1.prospects.ProspectListeController;
import fr.afpa.jakartaee_cyril1.prospects.ProspectModificationController;
import fr.afpa.jakartaee_cyril1.prospects.ProspectSuppressionController;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontController", value = "/FrontController")
public class FrontController extends HttpServlet {

    private Map<String, ICommand> commands = new HashMap<>();

    @Override
    public void init() {

        // Accueil
        commands.put(null, new PageAccueilController());
        commands.put("accueil", new PageAccueilController());

        // Clients
        commands.put("clientListe",        new ClientListeController());
        commands.put("clientCreation",     new ClientCreationController());
        commands.put("clientModification", new ClientModificationController());
        commands.put("clientSuppression",  new ClientSuppressionController());

        // Prospects
        commands.put("prospectListe",        new ProspectListeController());
        commands.put("prospectCreation",     new ProspectCreationController());
        commands.put("prospectModification", new ProspectModificationController());
        commands.put("prospectSuppression",  new ProspectSuppressionController());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String urlSuite = "";
        try {
            String cmd = request.getParameter("cmd");
            ICommand com = commands.get(cmd);
            urlSuite = com.execute(request, response);
        } catch (Exception e) {
            urlSuite = "/WEB-INF/jsp/erreur.jsp";
        } finally {
            try {
                request.getRequestDispatcher(urlSuite).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
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