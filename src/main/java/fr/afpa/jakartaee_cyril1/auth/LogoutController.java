package fr.afpa.jakartaee_cyril1.auth;

import fr.afpa.jakartaee_cyril1.controllers.ICommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

/**
 * Controller responsible for displaying the logout confirmation page
 * and clearing the session on logout.
 *
 * <p>Workflow:
 * <ul>
 *     <li>GET  -> Display logout confirmation page</li>
 *     <li>POST -> Invalidate session and redirect to login</li>
 * </ul>
 *
 * <p>Command: cmd=logout</p>
 */
public final class LogoutController implements ICommand {

    /** Logger. */
    private static final Logger LOG =
            Logger.getLogger(LogoutController.class.getName());

    @Override
    public String execute(
            final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        LOG.info("LogoutController executed.");

        // --------------------------------------------------------
        // GET : display logout confirmation page
        // --------------------------------------------------------
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return "/WEB-INF/jsp/auth/Logout.jsp";
        }

        // --------------------------------------------------------
        // POST : invalidate session
        // --------------------------------------------------------
        LOG.info("Invalidating session and removing CSRF token.");

        try {
            request.getSession().invalidate();
        } catch (Exception e) {
            LOG.warning("Session already invalid or error: " + e.getMessage());
        }

        // Redirect to login page
        return "redirect:FrontController?cmd=login";
    }
}
