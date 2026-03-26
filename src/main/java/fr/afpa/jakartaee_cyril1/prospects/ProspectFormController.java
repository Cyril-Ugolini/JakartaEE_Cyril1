package fr.afpa.jakartaee_cyril1.prospects;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Logger;
import fr.afpa.jakartaee_cyril1.models.Adresse;
import fr.afpa.jakartaee_cyril1.models.Interesse;
import fr.afpa.jakartaee_cyril1.models.Prospect;

/**
 * Contrôleur chargé d'afficher et de traiter le formulaire
 * de création ou de modification d'un prospect.
 *
 * <p>Gère les requêtes GET (affichage) et POST (validation).</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ProspectFormController implements ICommand {

    /** Logger du ProspectFormController. */
    private static final Logger LOG =
            Logger.getLogger(ProspectFormController.class.getName());

    /** Validator Jakarta Bean Validation. */
    private final Validator validator;

    /**
     * Constructeur : initialise le Validator manuellement.
     */
    public ProspectFormController() {
        final ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Point d'entrée du contrôleur.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la JSP à afficher
     * @throws Exception en cas d'erreur interne
     */
    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {
        LOG.info("ProspectFormController exécuté.");
        try {
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                return afficherFormulaire(request);
            }
            return traiterSoumission(request);
        } catch (Exception e) {
            LOG.severe("Erreur dans ProspectFormController : "
                    + e.getMessage());
            throw e;
        }
    }

    /**
     * Affiche le formulaire prospect vide.
     *
     * @param request requête HTTP
     * @return chemin de la JSP du formulaire prospect
     */
    private String afficherFormulaire(
            final HttpServletRequest request) {
        LOG.info("Affichage du formulaire prospect (GET).");
        final Prospect prospect = new Prospect();
        prospect.setAdresse(new Adresse());
        request.setAttribute("prospect", prospect);
        return "/WEB-INF/jsp/prospects/ProspectForm.jsp";
    }

    /**
     * Traite la soumission du formulaire prospect (POST).
     *
     * @param request requête HTTP
     * @return JSP du formulaire ou redirection
     */
    private String traiterSoumission(
            final HttpServletRequest request) {
        LOG.info("Traitement du formulaire prospect (POST).");

        final Prospect prospect = new Prospect();
        prospect.setRaisonSociale(getParam(request, "raisonSociale"));
        prospect.setTelephone(getParam(request, "telephone"));
        prospect.setAdresseMail(getParam(request, "adresseMail"));

        final Adresse adr = new Adresse();
        adr.setNumeroRue(getParam(request, "numeroRue"));
        adr.setNomRue(getParam(request, "nomRue"));
        adr.setCodePostal(getParam(request, "codePostal"));
        adr.setVille(getParam(request, "ville"));
        prospect.setAdresse(adr);

        final String dateStr = getParam(request, "dateProspection");
        if (!dateStr.isBlank()) {
            prospect.setDateProspection(LocalDate.parse(dateStr));
        }

        final String inter = getParam(request, "interesse");
        if (!inter.isBlank()) {
            prospect.setInteresse(Interesse.valueOf(inter));
        }

        final Set<ConstraintViolation<Prospect>> errors =
                validator.validate(prospect);

        if (!errors.isEmpty()) {
            LOG.warning("Erreurs de validation : " + errors.size());
            request.setAttribute("errors", errors);
            request.setAttribute("prospect", prospect);
            return "/WEB-INF/jsp/prospects/ProspectForm.jsp";
        }

        LOG.info("Prospect valide. Redirection vers la liste.");
        return "FrontController?cmd=prospectListe";
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
}
