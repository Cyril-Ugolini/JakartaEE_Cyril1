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
 *
 * @author UGOLINI
 */
public class InitAdminController implements ICommand {

    /** Logger du contrôleur. */
    private static final Logger LOG =
            Logger.getLogger(InitAdminController.class.getName());

    /** Nom d'utilisateur de l'admin. */
    private static final String ADMIN_USERNAME = "admin";

    /** Mot de passe par défaut de l'admin. */
    private static final String ADMIN_PASSWORD = "password123";

    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {

        LOG.info("Initialisation de l'administrateur...");

        try {
            UserDao dao = new UserDao();

            // Vérifie si l'admin existe déjà
            if (dao.findByUsername(ADMIN_USERNAME) != null) {
                LOG.warning("Administrateur déjà existant, aucune action.");
                return "redirect:index.jsp";
            }

            // ================================
            // Hachage Argon2 + POIVRE
            // ================================
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

            String hash = argon2.hash(
                    3,          // itérations
                    65536,      // mémoire
                    1,          // parallélisme
                    (ADMIN_PASSWORD + SecurityConfig.PEPPER).toCharArray()
            );

            // Création de l'utilisateur admin
            User admin = new User(null, ADMIN_USERNAME, hash);
            dao.create(admin);

            LOG.info("Administrateur créé avec succès.");
            return "redirect:index.jsp";

        } catch (Exception e) {
            LOG.severe("Erreur init admin : " + e.getMessage());
            throw new ServletException(e);
        }
    }
}
