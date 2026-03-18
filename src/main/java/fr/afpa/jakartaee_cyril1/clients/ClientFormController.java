package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.logging.Logger;
import models.Adresse;
import models.Client;

/**
 * Contrôleur chargé d'afficher et de traiter le formulaire
 * de création ou de modification d'un client.
 *
 * <p>Gère les requêtes GET (affichage) et POST (validation).</p>
 *
 * <p>La validation est effectuée via Jakarta Bean Validation.
 * Le {@link Validator} est instancié manuellement car CDI
 * n'est pas actif dans ce projet (Tomcat).</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ClientFormController implements ICommand {

    /** Logger du ClientFormController. */
    private static final Logger LOG =
            Logger.getLogger(ClientFormController.class.getName());

    /** Validator Jakarta Bean Validation. */
    private final Validator validator;

    /**
     * Constructeur : initialise le Validator manuellement.
     */
    public ClientFormController() {
        final ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Point d'entrée du contrôleur.
     *
     * @param request  requête HTTP contenant les données
     * @param response réponse HTTP à construire
     * @return chemin de la JSP à afficher ou redirection
     * @throws Exception en cas d'erreur interne
     */
    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {
        LOG.info("ClientFormController exécuté.");
        try {
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                return afficherFormulaire(request);
            }
            return traiterSoumission(request);
        } catch (Exception e) {
            LOG.severe("Erreur dans ClientFormController : "
                    + e.getMessage());
            throw e;
        }
    }

    /**
     * Affiche le formulaire de création ou modification d'un client.
     *
     * @param request requête HTTP
     * @return chemin de la JSP du formulaire client
     */
    private String afficherFormulaire(
            final HttpServletRequest request) {
        LOG.info("Affichage du formulaire client (GET).");
        final Client client = new Client();
        client.setAdresse(new Adresse());
        request.setAttribute("client", client);
        return "/WEB-INF/jsp/clients/ClientForm.jsp";
    }

    /**
     * Traite la soumission du formulaire client (POST).
     *
     * @param request requête HTTP contenant les données
     * @return JSP du formulaire en cas d'erreurs, sinon redirection
     */
    private String traiterSoumission(
            final HttpServletRequest request) {
        LOG.info("Traitement du formulaire client (POST).");

        final Client client = new Client();
        client.setIdClient(
                parseInt(getParam(request, "idClient")));
        client.setRaisonSociale(
                getParam(request, "raisonSociale"));
        client.setTelephone(
                getParam(request, "telephone"));
        client.setAdresseMail(
                getParam(request, "adresseMail"));
        client.setCommentaires(
                getParam(request, "commentaires"));
        client.setChiffreAffaires(
                parseLong(getParam(request, "chiffreAffaires")));
        client.setNombreEmployes(
                parseInt(getParam(request, "nombreEmployes")));

        final Adresse adr = new Adresse();
        adr.setIdAdresse(
                parseInt(getParam(request, "idAdresse")));
        adr.setNumeroRue(getParam(request, "numeroRue"));
        adr.setNomRue(getParam(request, "nomRue"));
        adr.setCodePostal(getParam(request, "codePostal"));
        adr.setVille(getParam(request, "ville"));
        client.setAdresse(adr);

        final Set<ConstraintViolation<Client>> erreurs =
                validator.validate(client);

        if (!erreurs.isEmpty()) {
            LOG.warning("Erreurs de validation : "
                    + erreurs.size());
            request.setAttribute("errors", erreurs);
            request.setAttribute("client", client);
            return "/WEB-INF/jsp/clients/ClientForm.jsp";
        }

        LOG.info("Client valide. Redirection vers la liste.");
        return "FrontController?cmd=clientListe";
    }

    /**
     * Retourne le paramètre de la requête ou une chaîne vide.
     *
     * @param request requête HTTP
     * @param name    nom du paramètre
     * @return valeur ou chaîne vide
     */
    private String getParam(
            final HttpServletRequest request,
            final String name) {
        final String val = request.getParameter(name);
        return val != null ? val.trim() : "";
    }

    /**
     * Convertit une chaîne en entier.
     *
     * @param v chaîne à convertir
     * @return entier ou {@code null} si invalide
     */
    private Integer parseInt(final String v) {
        try {
            return v.isBlank() ? null : Integer.parseInt(v);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Convertit une chaîne en long.
     *
     * @param v chaîne à convertir
     * @return valeur numérique ou 0 si invalide
     */
    private long parseLong(final String v) {
        try {
            return v.isBlank() ? 0L : Long.parseLong(v);
        } catch (Exception e) {
            return 0L;
        }
    }
}
