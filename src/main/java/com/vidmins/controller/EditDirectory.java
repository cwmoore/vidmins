package com.vidmins.controller;

import com.vidmins.entity.Directory;
import com.vidmins.persistence.DaoHelper;

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
 * Edit a directory.
 * @author cwmoore
 */

@WebServlet(
        name = "editDirectory",
        urlPatterns = {"/edit-directory"}
)

public class EditDirectory extends HttpServlet {

    private Logger logger;
    private DaoHelper dao;

    /**
     * Initialize session
     */
    @Override
    public void init() throws ServletException {

        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting EditDirectory servlet");
        dao = new DaoHelper();
    }


    /**
     * Handle a GET request
     * @param request the request object
     * @param response the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        dao.loadHelpers(request);

        Directory directory = null;

        if (request.getParameter("directoryId") != null) {
            try {
                int directoryId = Integer.parseInt(request.getParameter("directoryId"));

                directory = dao.directory.getById(directoryId);
                request.getSession().setAttribute("editDirectory", directory);
            } catch (NumberFormatException nfe) {
                logger.debug("directoryId didn't parse as an int", nfe);
            }
        } else {
            request.getSession().removeAttribute("editDirectory");
        }

        logger.debug("directory from id: " + request.getParameter("directoryId") + "\n" + directory);

        String url = "/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Handle a GET request
     * @param request the request object
     * @param response the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}