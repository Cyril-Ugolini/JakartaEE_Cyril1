package fr.afpa.jakartaee_cyril1.clients;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import models.Adresse;
import models.Client;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Contrôleur chargé d'afficher et de traiter le formulaire
 * de création ou de modification d'un client.
 *
 * <p>Ce contrôleur gère à la fois :
 * <ul>
 *     <li>la requête GET : affichage du formulaire vide ou prérempli</li>
 *     <li>la requête POST : validation des données saisies</li>
 * </ul>
 * </p>
 *
 * <p>La validation est effectuée via Jakarta Bean Validation.
 * Comme CDI n'est pas actif dans ce projet (Tomcat),
 * le {@link Validator} est instancié manuellement.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ClientFormController implements ICommand {

    /** Logger du contrôleur. */
    private static final Logger LOG =
            Logger.getLogger(ClientFormController.class.getName());

    /** Validator Jakarta Bean Validation (instancié manuellement). */
    private final Validator validator;

    /**
     * Constructeur : initialise le Validator manuellement
     * car CDI n'est pas actif dans ce projet.
     */
    public ClientFormController() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Point d'entrée du contrôleur.
     *
     * <p>Selon la méthode HTTP utilisée :
     * <ul>
     *     <li>GET → affichage du formulaire</li>
     *     <li>POST → traitement + validation</li>
     * </ul>
     * </p>
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

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return afficherFormulaire(request);
        }

        return traiterSoumission(request);
    }

    /**
     * Affiche le formulaire de création ou modification d'un client.
     *
     * <p>Cette méthode initialise un objet {@link Client}
     * contenant une {@link Adresse} vide afin d'éviter les erreurs
     * de type NullPointerException dans la JSP.</p>
     *
     * @param request requête HTTP
     * @return chemin de la JSP du formulaire client
     */
    private String afficherFormulaire(final HttpServletRequest request) {

        LOG.info("Affichage du formulaire client (GET).");

        Client client = new Client();
        client.setAdresse(new Adresse());

        request.setAttribute("client", client);

        return "/WEB-INF/jsp/clients/ClientForm.jsp";
    }

    /**
     * Traite la soumission du formulaire client (POST).
     *
     * <p>Étapes :
     * <ol>
     *     <li>Reconstruction de l'objet {@link Client} à partir des paramètres</li>
     *     <li>Validation via Jakarta Bean Validation</li>
     *     <li>Renvoi des erreurs à la JSP si nécessaire</li>
     *     <li>Sinon, passage à l'étape suivante (DAO plus tard)</li>
     * </ol>
     * </p>
     *
     * @param request requête HTTP contenant les données du formulaire
     * @return JSP du formulaire en cas d'erreurs, sinon redirection
     */
    private String traiterSoumission(final HttpServletRequest request) {

        LOG.info("Traitement du formulaire client (POST).");

        // 1. Reconstruction du bean Client
        Client client = new Client();

        client.setIdClient(parseInt(request.getParameter("idClient")));
        client.setRaisonSociale(request.getParameter("raisonSociale"));
        client.setTelephone(request.getParameter("telephone"));
        client.setAdresseMail(request.getParameter("adresseMail"));
        client.setCommentaires(request.getParameter("commentaires"));
        client.setChiffreAffaires(parseLong(request.getParameter("chiffreAffaires")));
        client.setNombreEmployes(parseInt(request.getParameter("nombreEmployes")));

        Adresse adr = new Adresse();
        adr.setIdAdresse(parseInt(request.getParameter("idAdresse")));
        adr.setNumeroRue(request.getParameter("numeroRue"));
        adr.setNomRue(request.getParameter("nomRue"));
        adr.setCodePostal(request.getParameter("codePostal"));
        adr.setVille(request.getParameter("ville"));

        client.setAdresse(adr);

        // 2. Validation du bean
        Set<ConstraintViolation<Client>> erreurs = validator.validate(client);

        if (!erreurs.isEmpty()) {
            LOG.warning("Erreurs de validation détectées : " + erreurs.size());

            request.setAttribute("errors", erreurs);
            request.setAttribute("client", client);

            return "/WEB-INF/jsp/clients/ClientForm.jsp";
        }

        // 3. Si tout est valide → DAO plus tard
        LOG.info("Client valide. Redirection vers la liste.");
        return "FrontController?cmd=clientListe";
    }

    /**
     * Convertit une chaîne en entier.
     *
     * @param v chaîne à convertir
     * @return entier ou {@code null} si invalide
     */
    private Integer parseInt(final String v) {
        try {
            return (v == null || v.isBlank()) ? null : Integer.parseInt(v);
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
            return (v == null || v.isBlank()) ? 0 : Long.parseLong(v);
        } catch (Exception e) {
            return 0;
        }
    }
}
