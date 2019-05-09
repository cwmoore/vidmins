package com.vidmins.controller;

import com.vidmins.entity.Directory;
import com.vidmins.entity.Note;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
import com.vidmins.persistence.DaoHelper;
import com.vidmins.util.SessionHelper;
import com.vidmins.persistence.GenericDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Delete a note.
 * @author cwmoore
 */

@WebServlet(
        name = "deleteNote",
        urlPatterns = {"/delete-note"}
)

public class DeleteNote extends HttpServlet {

    private Logger logger;
    private DaoHelper dao;
    private SessionHelper sessionHelper;

    /**
     * Initialize session
     */
    @Override
    public void init() throws ServletException {

        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting DeleteNote servlet");
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
        Note note;
        if (request.getParameter("id") != null) {
            try {
                int noteId = Integer.parseInt(request.getParameter("id"));
                note = dao.note.getById(noteId);
                Video currentVideo = note.getVideo();

                dao.note.delete(note);

                logger.debug("delete note by id: " + request.getParameter("id") + "\n" + note);

                sessionHelper.resetAll(request);

            } catch (NumberFormatException nfe) {
                logger.debug(nfe.toString());
            }
        } else {
            note = null;
        }

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