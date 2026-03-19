package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import fr.afpa.jakartaee_cyril1.DAO.ClientDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import models.Adresse;
import models.Client;

import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher et de traiter le formulaire
 * de création ou de modification d'un client.
 *
 * <p>Gère GET (affichage) et POST (validation + sauvegarde).</p>
 *
 * @author Cyril
 * @version 2.0
 */
public final class ClientFormController implements ICommand {

    /** Logger du contrôleur. */
    private static final Logger LOG =
            Logger.getLogger(ClientFormController.class.getName());

    /** Validator Jakarta Bean Validation. */
    private final Validator validator;

    /** DAO Client. */
    private final ClientDao clientDao;

    /**
     * Constructeur : initialise Validator + DAO.
     */
    public ClientFormController() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();

        try {
            this.clientDao = new ClientDao();
        } catch (SQLException e) {
            throw new RuntimeException("Impossible d'initialiser ClientDao", e);
        }
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {

        LOG.info("ClientFormController exécuté.");

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return afficherFormulaire(request);
        }

        return traiterSoumission(request);
    }

    /* ============================================================
       GET : AFFICHAGE DU FORMULAIRE
       ============================================================ */

    private String afficherFormulaire(HttpServletRequest request) {

        LOG.info("Affichage du formulaire client (GET).");

        Integer idClient = parseInt(request.getParameter("idClient"));
        Client client;

        if (idClient != null) {
            LOG.info("Mode édition : chargement du client ID=" + idClient);
            try {
                client = clientDao.findById(idClient);
            } catch (SQLException e) {
                throw new RuntimeException("Erreur findById()", e);
            }

            if (client == null) {
                LOG.warning("Client introuvable, création d'un nouveau.");
                client = new Client();
                client.setAdresse(new Adresse());
            }
        } else {
            LOG.info("Mode création : nouveau client.");
            client = new Client();
            client.setAdresse(new Adresse());
        }

        request.setAttribute("client", client);
        return "/WEB-INF/jsp/clients/ClientForm.jsp";
    }

    /* ============================================================
       POST : TRAITEMENT DU FORMULAIRE
       ============================================================ */

    private String traiterSoumission(HttpServletRequest request) {

        LOG.info("Traitement du formulaire client (POST).");

        Client client = new Client();
        client.setIdClient(parseInt(getParam(request, "idClient")));
        client.setRaisonSociale(getParam(request, "raisonSociale"));
        client.setTelephone(getParam(request, "telephone"));
        client.setAdresseMail(getParam(request, "adresseMail"));
        client.setCommentaires(getParam(request, "commentaires"));
        client.setChiffreAffaires(parseLong(getParam(request, "chiffreAffaires")));
        client.setNombreEmployes(parseInt(getParam(request, "nombreEmployes")));

        Adresse adr = new Adresse();
        adr.setIdAdresse(parseInt(getParam(request, "idAdresse")));
        adr.setNumeroRue(getParam(request, "numeroRue"));
        adr.setNomRue(getParam(request, "nomRue"));
        adr.setCodePostal(getParam(request, "codePostal"));
        adr.setVille(getParam(request, "ville"));
        client.setAdresse(adr);

        // Validation Jakarta
        Set<ConstraintViolation<Client>> erreurs = validator.validate(client);

        if (!erreurs.isEmpty()) {
            LOG.warning("Erreurs de validation : " + erreurs.size());
            request.setAttribute("errors", erreurs);
            request.setAttribute("client", client);
            return "/WEB-INF/jsp/clients/ClientForm.jsp";
        }

        // Sauvegarde
        try {
            if (client.getIdClient() == null) {
                LOG.info("Création du client.");
                clientDao.create(client);
            } else {
                LOG.info("Mise à jour du client ID=" + client.getIdClient());
                clientDao.update(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du client", e);
        }

        return "FrontController?cmd=clientListe";
    }

    /* ============================================================
       HELPERS
       ============================================================ */

    private String getParam(HttpServletRequest request, String name) {
        String val = request.getParameter(name);
        return val != null ? val.trim() : "";
    }

    private Integer parseInt(String v) {
        try {
            return (v == null || v.isBlank()) ? null : Integer.parseInt(v);
        } catch (Exception e) {
            return null;
        }
    }

    private long parseLong(String v) {
        try {
            return (v == null || v.isBlank()) ? 0L : Long.parseLong(v);
        } catch (Exception e) {
            return 0L;
        }
    }
}
