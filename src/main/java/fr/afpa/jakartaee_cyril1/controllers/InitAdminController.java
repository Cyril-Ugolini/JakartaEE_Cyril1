package fr.afpa.jakartaee_cyril1.controllers;

import fr.afpa.jakartaee_cyril1.DAO.UserDao;
import models.User;
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
 * <p>Crée un utilisateur admin avec mot de passe hashé via Argon2.
 * Sans effet si l'admin existe déjà.</p>
 *
 * @author UGOLINI Cyril
 * @version 0.0.3
 * @since 24/03/2026
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

            // Hash du mot de passe avec Argon2
            Argon2 argon2 = Argon2Factory.create();
            String hash = argon2.hash(10, 65536, 1,
                    ADMIN_PASSWORD.toCharArray());

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
