package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import fr.afpa.jakartaee_cyril1.DAO.ClientDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import fr.afpa.jakartaee_cyril1.models.Adresse;
import fr.afpa.jakartaee_cyril1.models.Client;

import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Contrôleur chargé de gérer l'affichage et la soumission
 * du formulaire de création ou modification d'un client.
 *
 * <p>GET  → affichage du formulaire<br>
 * POST → validation, sauvegarde, redirection</p>
 */
public final class ClientFormController implements ICommand {

    /** Logger du contrôleur ClientForm. */
    private static final Logger LOG =
            Logger.getLogger(ClientFormController.class.getName());

    /** Validator Jakarta Bean Validation. */
    private final Validator validator;

    /** DAO permettant les opérations CRUD sur les clients. */
    private final ClientDao clientDao;

    /**
     * Constructeur : initialise le validator et le DAO.
     */
    public ClientFormController() {
        final ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();

        try {
            this.clientDao = new ClientDao();
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Impossible d'initialiser ClientDao", e);
        }
    }

    /**
     * Traite la requête HTTP en distinguant GET et POST.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return chemin de la vue ou redirection
     * @throws Exception en cas d’erreur interne
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws Exception {

        LOG.info("ClientFormController exécuté.");

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return afficherFormulaire(request);
        }

        return traiterSoumission(request, response);
    }

    /**
     * Affiche le formulaire de création ou modification d’un client.
     *
     * @param request requête HTTP
     * @return chemin de la JSP du formulaire
     */
    private String afficherFormulaire(final HttpServletRequest request) {

        LOG.info("Affichage du formulaire client (GET).");

        final Integer idClient =
                parseInt(request.getParameter("idClient"));

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

        // Nouveau token CSRF à chaque affichage
        final String csrf = UUID.randomUUID().toString();
        request.getSession().setAttribute("csrfToken", csrf);

        request.setAttribute("client", client);
        return "/WEB-INF/jsp/clients/ClientForm.jsp";
    }

    /**
     * Traite la soumission du formulaire client (POST).
     *
     * @param request  requête HTTP contenant les données du formulaire
     * @param response réponse HTTP
     * @return redirection vers la liste ou JSP en cas d’erreur
     */
    private String traiterSoumission(final HttpServletRequest request,
                                     final HttpServletResponse response) {

        LOG.info("Traitement du formulaire client (POST).");

        // Vérification CSRF
        final String sessionToken =
                (String) request.getSession().getAttribute("csrfToken");
        final String formToken = request.getParameter("csrfToken");

        if (sessionToken == null
                || formToken == null
                || !sessionToken.equals(formToken)) {

            LOG.warning("CSRF token invalide !");
            try {
                response.sendError(
                        HttpServletResponse.SC_FORBIDDEN,
                        "CSRF token invalide");
            } catch (Exception e) {
                throw new RuntimeException("Erreur CSRF", e);
            }
            return null;
        }

        // Construction du Client
        final Client client = new Client();
        client.setIdClient(parseInt(getParam(request, "idClient")));
        client.setRaisonSociale(getParam(request, "raisonSociale"));
        client.setTelephone(getParam(request, "telephone"));
        client.setAdresseMail(getParam(request, "adresseMail"));
        client.setCommentaires(getParam(request, "commentaires"));
        client.setChiffreAffaires(
                parseLong(getParam(request, "chiffreAffaires")));
        client.setNombreEmployes(
                parseInt(getParam(request, "nombreEmployes")));

        final Adresse adr = new Adresse();
        adr.setIdAdresse(parseInt(getParam(request, "idAdresse")));
        adr.setNumeroRue(getParam(request, "numeroRue"));
        adr.setNomRue(getParam(request, "nomRue"));
        adr.setCodePostal(getParam(request, "codePostal"));
        adr.setVille(getParam(request, "ville"));

        client.setAdresse(adr);

        // Validation Jakarta
        final Set<ConstraintViolation<Client>> erreurs =
                validator.validate(client);

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
                LOG.info("Mise à jour du client ID="
                        + client.getIdClient());
                clientDao.update(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erreur lors de la sauvegarde du client", e);
        }

        return "redirect:" + request.getContextPath()
                + "/FrontController?cmd=clientListe";
    }

    /**
     * Récupère un paramètre HTTP en supprimant les espaces inutiles.
     *
     * @param request requête HTTP
     * @param name    nom du paramètre
     * @return valeur nettoyée ou chaîne vide
     */
    private String getParam(final HttpServletRequest request,
                            final String name) {

        final String val = request.getParameter(name);
        return val != null ? val.trim() : "";
    }

    /**
     * Convertit une chaîne en entier.
     *
     * @param v chaîne à convertir
     * @return entier ou null si invalide
     */
    private Integer parseInt(final String v) {
        try {
            return (v == null || v.isBlank())
                    ? null : Integer.parseInt(v);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Convertit une chaîne en long.
     *
     * @param v chaîne à convertir
     * @return valeur long ou 0 si invalide
     */
    private long parseLong(final String v) {
        try {
            return (v == null || v.isBlank())
                    ? 0L : Long.parseLong(v);
        } catch (Exception e) {
            return 0L;
        }
    }
}
