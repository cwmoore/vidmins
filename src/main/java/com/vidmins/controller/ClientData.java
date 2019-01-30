package com.vidmins.controller;

import com.vidmins.persistence.UserData;
import com.vidmins.entity.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Get a JSON object for the app model.
 * @author cwmoore
 */

@WebServlet(
        urlPatterns = {"/clientData"}
)

public class ClientData extends HttpServlet {

    /**
     * Handle a GET request
     * @param req the request object
     * @param resp the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // if (req.getSession().getAttribute("user").isLoggedIn();

        RequestDispatcher dispatcher = req.getRequestDispatcher("/profile.json.jsp");
        dispatcher.forward(req, resp);
    }
}