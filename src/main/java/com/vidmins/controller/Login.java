package com.vidmins.controller;

import com.vidmins.entity.User;
import com.vidmins.persistence.GenericDao;
import com.vidmins.auth.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Log in a user.
 * @author cwmoore
 */

@WebServlet(
        name = "login",
        urlPatterns = {"/login"}
)

public class Login extends HttpServlet {

    Logger logger;

    /**
     * Initialize session
     */
    @Override
    public void init() throws ServletException {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting Login servlet");
    }
    /**
     * Handle a POST request (login)
     *
     * @param request the request object
     * @param response the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
/*
// TODO reimplement login
        String username = request.getRemoteUser();
        request.isUserInRole("admin");
        request.isUserInRole("normal");
        request.isUserInRole("guest");
*/
        GenericDao<User> userDao = new GenericDao<>(User.class);

        if (request.getParameter("userName") != null && request.getParameter("password") != null) {

            try {
                logger.debug(request.getParameter("userName") + request.getParameter("password"));

                request.login(request.getParameter("userName"), request.getParameter("password"));

                User user = userDao.findByPropertyEqual("userName", request.getRemoteUser()).get(0);

                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("errors", null);
                    response.sendRedirect("loadClient");
                } else {
                    throw new Exception("could not locate user");
                }

            } catch (ServletException se) {
                logger.debug(se.toString());
                request.getSession().setAttribute("errors", "Authentication error, please check username and password and try again.");
                response.sendRedirect("index.jsp");
            } catch (Exception e) {
                logger.debug(e.toString());
            }
        } else if (request.getRemoteUser() != null) {
            request.getSession().setAttribute("errors", null);
            response.sendRedirect("loadClient");
        }
    }
}