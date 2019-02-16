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
     * @param request the request object
     * @param response the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // if (request.getSession().getAttribute("user").isLoggedIn();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.json.jsp");
        dispatcher.forward(request, response);
    }
}