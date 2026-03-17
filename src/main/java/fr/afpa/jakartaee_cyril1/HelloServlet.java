package fr.afpa.jakartaee_cyril1;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet de démonstration Hello World.
 *
 * @author Cyril
 * @version 1.0
 */
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public final class HelloServlet extends HttpServlet {

    /** Message affiché par le servlet. */
    private String message;

    /**
     * Initialise le servlet avec le message Hello World.
     */
    @Override
    public void init() {
        message = "Hello World!";
    }

    /**
     * Traite la requête GET et affiche le message.
     * @param request  requête HTTP
     * @param response réponse HTTP
     * @throws IOException en cas d'erreur d'entrée/sortie
     */
    @Override
    public void doGet(
            final HttpServletRequest request,
            final HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    /**
     * Libère les ressources du servlet.
     */
    @Override
    public void destroy() {
    }
}
