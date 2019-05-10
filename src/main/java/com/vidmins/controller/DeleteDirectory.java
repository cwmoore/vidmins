package com.vidmins.controller;

import com.vidmins.entity.Directory;
import com.vidmins.entity.Note;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
import com.vidmins.util.SessionHelper;
import com.vidmins.persistence.DaoHelper;
import com.vidmins.persistence.GenericDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Delete a directory.
 * @author cwmoore
 */

@WebServlet(
        name = "deleteDirectory",
        urlPatterns = {"/delete-directory"}
)

public class DeleteDirectory extends HttpServlet {

    private Logger logger;
    private DaoHelper dao;
    private SessionHelper sessionHelper;

    /**
     * Initialize session
     */
    @Override
    public void init() throws ServletException {

        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting DeleteDirectory servlet");
        dao = new DaoHelper();
        sessionHelper = new SessionHelper();
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

        String url = "loadClient";
        Directory directory;
        if (request.getParameter("id") != null) {
            try {
                int directoryId = Integer.parseInt(request.getParameter("directoryId"));
                directory = dao.directory.getById(directoryId);

                dao.directory.delete(directory);

                logger.debug("delete directory by id: " + request.getParameter("directoryId") + "\n" + directory);

            } catch (NumberFormatException nfe) {
                logger.debug(nfe.toString());
            }
        } else {
            directory = null;
        }

        sessionHelper.resetAll(request);

        response.sendRedirect(url);
//        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//        dispatcher.forward(request, response);
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