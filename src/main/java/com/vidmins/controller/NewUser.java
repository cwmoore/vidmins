package com.vidmins.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet template
 * @author cwmoore
 */

@WebServlet(
        name = "newUser",
        urlPatterns = {"/new-user"}
)

public class NewUser extends HttpServlet {
    private Logger logger;

    /**
     * Initialize variables
     */
    public void init() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting servlet");
    }

    /**
     * Handle a POST request
     *
     * @param req the HttpServletRequest
     * @param resp the HttpServletResponse
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // extract field data
        // verify complete and correct
        // add new user
        // go to start page
        String url = "/";
        resp.sendRedirect(url);
    }
}