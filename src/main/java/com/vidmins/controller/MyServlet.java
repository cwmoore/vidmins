import org.apache.catalina.Realm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "myServlet", urlPatterns = {"/my-servlet"})
public class MyServlet extends HttpServlet {

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.debug("in MyServlet.doGet()");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
//
//        try {
//            Realm realm = this.getServletContext().getContainer().getRealm();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        if (shouldAuthenticate(req)) {
            boolean authenticated = req.authenticate(resp);
            if (authenticated) {
                if (req.getUserPrincipal() != null) {
                    writer.println("user authenticated " + req.getUserPrincipal().getName());
                }
            } else {
                return;
            }
        }

        writer.println("<p>some data</p>");
    }

    private boolean shouldAuthenticate(HttpServletRequest req) {
        //todo: apply some real condition
        return true;
    }
}