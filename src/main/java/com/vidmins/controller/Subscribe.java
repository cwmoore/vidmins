package com.vidmins.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

/**
 * Servlet template
 * @author cwmoore
 */

@WebServlet(
        name = "",
        urlPatterns = {"/"}
)

public class Subscribe extends HttpServlet {
    private Logger logger;

    /**
     * Initialize variables
     */
    public void init() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting servlet");
    }

    /**
     *  Handle a GET request
     *
     * @param req the HttpServletRequest
     * @param resp the HttpServletResponse
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String url = "/";
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
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
        String url = "/";
        resp.sendRedirect(url);
    }
}