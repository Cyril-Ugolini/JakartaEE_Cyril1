package fr.afpa.jakartaee_cyril1.prospects;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import models.Adresse;
import models.Interesse;
import models.Prospect;

import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Logger;

public final class ProspectFormController implements ICommand {

    private static final Logger LOG =
            Logger.getLogger(ProspectFormController.class.getName());

    private final Validator validator;

    public ProspectFormController() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        LOG.info("ProspectFormController exécuté.");

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return afficherFormulaire(request);
        }

        return traiterSoumission(request);
    }

    /**
     * GET → affiche un prospect vide
     */
    private String afficherFormulaire(final HttpServletRequest request) {

        Prospect prospect = new Prospect();
        prospect.setAdresse(new Adresse()); // IMPORTANT : évite les NPE dans la JSP

        request.setAttribute("prospect", prospect);

        return "/WEB-INF/jsp/prospects/ProspectForm.jsp";
    }

    /**
     * POST → traite la soumission + validation
     */
    private String traiterSoumission(final HttpServletRequest request) {

        Prospect prospect = new Prospect();

        // Champs hérités de Societe
        prospect.setRaisonSociale(request.getParameter("raisonSociale"));
        prospect.setTelephone(request.getParameter("telephone"));
        prospect.setAdresseMail(request.getParameter("adresseMail"));

        // Adresse
        Adresse adr = new Adresse();
        adr.setNumeroRue(request.getParameter("numeroRue"));
        adr.setNomRue(request.getParameter("nomRue"));
        adr.setCodePostal(request.getParameter("codePostal"));
        adr.setVille(request.getParameter("ville"));
        prospect.setAdresse(adr);

        // Date prospection
        String dateStr = request.getParameter("dateProspection");
        if (dateStr != null && !dateStr.isBlank()) {
            prospect.setDateProspection(LocalDate.parse(dateStr));
        }

        // Intérêt
        String inter = request.getParameter("interesse");
        if (inter != null && !inter.isBlank()) {
            prospect.setInteresse(Interesse.valueOf(inter));
        }

        // Validation Jakarta
        Set<ConstraintViolation<Prospect>> errors =
                validator.validate(prospect);

        if (!errors.isEmpty()) {

            request.setAttribute("errors", errors);
            request.setAttribute("prospect", prospect);

            return "/WEB-INF/jsp/prospects/ProspectForm.jsp";
        }

        return "FrontController?cmd=prospectListe";
    }
}
