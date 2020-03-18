package com.vidmins.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Terms of service and privacy policy
 *
 * @author cwmoore
 */

@WebServlet(
        urlPatterns = {"/terms-and-privacy"}
)

public class Terms extends HttpServlet {

    /**
     * Handle a request for Terms and Privacy Policy
     *
     * @param req  the request object
     * @param resp the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException      indicates an IO problem
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "/terms-and-privacy.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
