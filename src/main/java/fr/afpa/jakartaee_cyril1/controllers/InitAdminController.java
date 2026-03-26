package fr.afpa.jakartaee_cyril1.controllers;

import fr.afpa.jakartaee_cyril1.DAO.UserDao;
import fr.afpa.jakartaee_cyril1.security.SecurityConfig;
import fr.afpa.jakartaee_cyril1.models.User;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Initialise l'administrateur par défaut.
 *
 * <p>Crée un utilisateur admin avec un mot de passe haché via Argon2.
 * Le hachage inclut automatiquement un sel (géré par Argon2) et ajoute
 * manuellement un poivre (valeur secrète définie dans SecurityConfig).</p>
 *
 * <p>Si l'administrateur existe déjà, aucune action n'est effectuée.</p>
 */
public final class InitAdminController implements ICommand {

    /** Logger du contrôleur. */
    private static final Logger LOG =
            Logger.getLogger(InitAdminController.class.getName());

    /** Nom d'utilisateur de l'admin. */
    private static final String ADMIN_USERNAME = "admin";

    /** Paramètre Argon2 : itérations. */
    private static final int ARGON_ITER = 3;

    /** Paramètre Argon2 : mémoire. */
    private static final int ARGON_MEMORY = 65536;

    /** Paramètre Argon2 : parallélisme. */
    private static final int ARGON_PARALLELISM = 1;

    /** Mot de passe admin lu depuis variable d'environnement. */
    private static final String ADMIN_PASSWORD;

    static {
        final String env = System.getenv("ADMIN_PASSWORD");
        if (env == null || env.isBlank()) {
            ADMIN_PASSWORD = "voici_mon_nouveau_code_!2026";
        } else {
            ADMIN_PASSWORD = env;
        }
    }

    /**
     * Exécute l'initialisation de l'administrateur.
     *
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @return redirection vers index.jsp
     * @throws ServletException si une erreur survient
     * @throws IOException si une erreur d'E/S survient
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {

        LOG.info("Initialisation de l'administrateur...");

        try {
            final UserDao dao = new UserDao();

            // Vérifie si l'admin existe déjà
            if (dao.findByUsername(ADMIN_USERNAME) != null) {
                LOG.warning("Administrateur déjà existant, aucune action.");
                return "redirect:index.jsp";
            }

            // ================================
            // Hachage Argon2 + POIVRE
            // ================================
            final Argon2 argon2 =
                    Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

            final String hash = argon2.hash(
                    ARGON_ITER,
                    ARGON_MEMORY,
                    ARGON_PARALLELISM,
                    (ADMIN_PASSWORD + SecurityConfig.PEPPER).toCharArray()
            );

            // Création de l'utilisateur admin
            final User admin = new User(null, ADMIN_USERNAME, hash);
            dao.create(admin);

            LOG.info("Administrateur créé avec succès.");
            return "redirect:index.jsp";

        } catch (Exception e) {
            LOG.severe("Erreur init admin : " + e.getMessage());
            throw new ServletException(e);
        }
    }
}
