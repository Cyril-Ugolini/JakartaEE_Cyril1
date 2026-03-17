package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller chargé d'afficher la liste complète des clients.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque la commande
 * {@code cmd=clientListe} est reçue. Il se contente de renvoyer la vue JSP
 * affichant la liste des clients. La récupération des données pourra être
 * ajoutée ultérieurement si un modèle ou une DAO est intégrée.</p>
 *
 * <p>La JSP retournée présente l'ensemble des clients sous forme de tableau
 * ou de liste, selon la structure définie dans la vue.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public class ClientListeController implements ICommand {

    /**
     * Exécute la commande et renvoie la page JSP contenant la liste des clients.
     *
     * @param request  l'objet {@link HttpServletRequest} contenant les informations de la requête HTTP
     * @param response l'objet {@link HttpServletResponse} permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher : {@code /WEB-INF/jsp/clients/ClientListe.jsp}
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception
    {
        return "/WEB-INF/jsp/clients/ClientListe.jsp";
    }
}