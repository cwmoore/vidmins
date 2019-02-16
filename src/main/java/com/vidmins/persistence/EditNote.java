package com.vidmins.controller;

import com.vidmins.entity.Note;
import com.vidmins.persistence.NoteData;
import com.vidmins.persistence.UserData;
import com.vidmins.persistence.VideoData;
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

    UserData userData;
    VideoData videoData;
    NoteData noteData;

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

        if (userData == null) {
            if (request.getSession().getAttribute("userData") == null) {
                userData = new UserData();
                request.getSession().setAttribute("userData", userData);
            } else {
                userData = (UserData) request.getSession().getAttribute("userData");
            }
        }

        if (videoData == null) {
            if (request.getSession().getAttribute("videoData") == null) {
                videoData = new VideoData();
                request.getSession().setAttribute("videoData", videoData);
            } else {
                videoData = (VideoData) request.getSession().getAttribute("videoData");
            }
        }

        if (noteData == null) {
            if (request.getSession().getAttribute("noteData") == null) {
                noteData = new NoteData();
                request.getSession().setAttribute("noteData", noteData);
            } else {
                noteData = (NoteData) request.getSession().getAttribute("noteData");
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
            note = noteData.fromId(noteId);
            request.getSession().setAttribute("note", note);
        } else {
            note = null;
        }

        logger.debug("note from id: " + request.getParameter("noteId") + "\n" + note);

        String url = "/";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}