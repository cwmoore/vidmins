package com.vidmins.controller;

import com.vidmins.entity.User;
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
        name = "subscribe",
        urlPatterns = {"/subscribe"}
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
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Handle a POST request
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // current user gets access to offered material
        User user = (User) request.getSession().getAttribute("user");

        
        String url = "/";
        response.sendRedirect(url);
    }
}