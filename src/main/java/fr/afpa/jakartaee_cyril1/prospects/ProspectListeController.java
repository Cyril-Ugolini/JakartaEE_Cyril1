package fr.afpa.jakartaee_cyril1.prospects;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller chargé d'afficher la liste complète des prospects.
 *
 * <p>Ce contrôleur est invoqué par le FrontController lorsque la commande
 * {@code cmd=prospectListe} est reçue. Il renvoie simplement la vue JSP
 * affichant la liste des prospects. La récupération des données pourra être
 * ajoutée ultérieurement lorsque la couche DAO ou service sera intégrée.</p>
 *
 * <p>La JSP retournée présente l'ensemble des prospects sous forme de tableau
 * ou de liste, selon la structure définie dans la vue.</p>
 *
 * @version 1.0
 * @author Cyril
 */
public class ProspectListeController implements ICommand {

    /**
     * Exécute la commande et renvoie la page JSP contenant la liste des prospects.
     *
     * @param request  l'objet {@link HttpServletRequest} contenant les informations de la requête HTTP
     * @param response l'objet {@link HttpServletResponse} permettant de construire la réponse HTTP
     * @return le chemin de la JSP à afficher : {@code /WEB-INF/jsp/prospects/ProspectListe.jsp}
     * @throws Exception si une erreur survient lors du traitement
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception
    {
        return "WEB-INF/jsp/prospects/ProspectListe.jsp";
    }
}