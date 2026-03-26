package fr.afpa.jakartaee_cyril1.auth;

import fr.afpa.jakartaee_cyril1.DAO.UserDao;
import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import fr.afpa.jakartaee_cyril1.security.SecurityConfig;
import fr.afpa.jakartaee_cyril1.models.User;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * Contrôleur de connexion.
 *
 * <p>Gère l'affichage du formulaire (GET) et l'authentification (POST).
 * La vérification utilise Argon2 avec sel automatique et poivre
 * (valeur secrète définie dans SecurityConfig).</p>
 *
 * @author UG
 */
public final class LoginController implements ICommand {

    /** Logger du contrôleur. */
    private static final Logger LOG =
            Logger.getLogger(LoginController.class.getName());

    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws Exception {

        LOG.info("LoginController exécuté.");

        // --------------------------------------------------------
        // GET → afficher la page de login
        // --------------------------------------------------------
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return "/WEB-INF/jsp/auth/Login.jsp";
        }

        // --------------------------------------------------------
        // POST → authentification
        // --------------------------------------------------------
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");

        // Validation basique
        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            request.setAttribute("error",
                    "Identifiant et mot de passe obligatoires.");
            return "/WEB-INF/jsp/auth/Login.jsp";
        }

        LOG.info("Tentative de connexion pour : " + username);

        UserDao dao = new UserDao();
        User user = dao.findByUsername(username);

        if (user == null) {
            LOG.warning("Utilisateur inconnu : " + username);
            request.setAttribute("error",
                    "Identifiant ou mot de passe incorrect.");
            return "/WEB-INF/jsp/auth/Login.jsp";
        }

        // --------------------------------------------------------
        // Vérification du mot de passe Argon2 + POIVRE
        // --------------------------------------------------------
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        boolean ok = argon2.verify(
                user.getPasswordHash(),
                (password + SecurityConfig.PEPPER).toCharArray()
        );

        if (!ok) {
            LOG.warning("Mot de passe incorrect pour : " + username);
            request.setAttribute("error",
                    "Identifiant ou mot de passe incorrect.");
            return "/WEB-INF/jsp/auth/Login.jsp";
        }

        // Connexion réussie
        LOG.info("Connexion réussie pour : " + username);

        // Objet user complet en session
        request.getSession().setAttribute("user", user);

        // Ajout dans la liste des utilisateurs connectés
        UserRegistry.add(username);
        LOG.info("Utilisateur ajouté au registre : " + username);

        // Génération du token CSRF
        final String csrf = UUID.randomUUID().toString();
        request.getSession().setAttribute("csrfToken", csrf);
        LOG.info("Token CSRF généré : " + csrf);

        return "redirect:FrontController?cmd=accueil";
    }
}
