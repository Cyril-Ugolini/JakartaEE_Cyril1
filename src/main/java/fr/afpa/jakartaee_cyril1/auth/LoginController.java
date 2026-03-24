package fr.afpa.jakartaee_cyril1.auth;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * Controller responsible for displaying the login page
 * and initializing the CSRF token after login.
 *
 * <p>This controller does not validate credentials (ECF context).
 * It only displays the login form (GET) and initializes the
 * session after a fake login (POST).</p>
 *
 * <p>Command: cmd=login</p>
 */
public final class LoginController implements ICommand {

    /** Logger. */
    private static final Logger LOG =
            Logger.getLogger(LoginController.class.getName());

    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        LOG.info("LoginController executed.");

        // --------------------------------------------------------
        // GET : display login page
        // --------------------------------------------------------
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return "/WEB-INF/jsp/auth/Login.jsp";
        }

        // --------------------------------------------------------
        // POST : fake login + CSRF token creation
        // --------------------------------------------------------
        LOG.info("Fake login accepted. Initializing session.");

        // Create CSRF token
        String csrf = UUID.randomUUID().toString();
        request.getSession().setAttribute("csrfToken", csrf);

        LOG.info("CSRF token generated: " + csrf);

        // Redirect to home page
        return "redirect:FrontController?cmd=accueil";
    }
}
