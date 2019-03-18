package com.vidmins.controller;

import com.vidmins.entity.Note;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
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
 * Store a note.
 * @author cwmoore
 */

@WebServlet(
        name = "editNote",
        urlPatterns = {"/edit-note"}
)

public class EditNote extends HttpServlet {

    private int previousDigit;
    private Logger logger;

    private GenericDao<User> userDao;
    private GenericDao<Video> videoDao;
    private GenericDao<Note> noteDao;

    /**
     * Initialize session
     */
    @Override
    public void init() throws ServletException {

        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting EditNote servlet");
    }


    /**
     * Initialize database helpers
     */
    public void loadHelpers(HttpServletRequest request) {
        logger = LogManager.getLogger(this.getClass());
        logger.debug("loadHelpers()");

        if (userDao == null) {
            if (request.getSession().getAttribute("userDao") == null) {
                userDao = new GenericDao<>(User.class);
                request.getSession().setAttribute("userDao", userDao);
            } else {
                userDao = (GenericDao<User>) request.getSession().getAttribute("userDao");
            }
        }

        if (videoDao == null) {
            if (request.getSession().getAttribute("videoDao") == null) {
                videoDao = new GenericDao<>(Video.class);
                request.getSession().setAttribute("videoDao", videoDao);
            } else {
                videoDao = (GenericDao<Video>) request.getSession().getAttribute("videoDao");
            }
        }

        if (noteDao == null) {
            if (request.getSession().getAttribute("noteDao") == null) {
                noteDao = new GenericDao<>(Note.class);
                request.getSession().setAttribute("noteDao", noteDao);
            } else {
                noteDao = (GenericDao<Note>) request.getSession().getAttribute("noteDao");
            }
        }
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
        loadHelpers(request);

        Note note;
        if (request.getParameter("noteId") != null) {
            int noteId = Integer.parseInt(request.getParameter("noteId"));
            note = noteDao.getById(noteId);
            request.getSession().setAttribute("note", note);
        } else {
            note = null;
        }

        logger.debug("note from id: " + request.getParameter("noteId") + "\n" + note);

        String url = "/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}