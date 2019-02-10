package com.vidmins.controller;

import com.vidmins.entity.Note;
import com.vidmins.persistence.NoteData;
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
        urlPatterns = {"/new-note"}
)

public class NewNote extends HttpServlet {

    private int previousDigit;
    private Logger logger;

    /**
     * Initialize variables
     */
    public void init() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting NewNote servlet");
    }
    /**
     * Handle a POST request (new note)
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NoteData noteData = new NoteData();

        logger.debug(req.getParameterMap());

        if (req.getParameter("label") != null &&
                req.getParameter("note_text") != null &&
                req.getParameter("timeStampStart") != null &&
                req.getParameter("timeStampEnd") != null &&
                req.getParameter("videoId") != null &&
                req.getParameter("userId") != null) {

            Map<String, String[]> noteFields = new TreeMap<>(req.getParameterMap());
            int insertId = noteData.setNewNote(noteFields);

            req.getSession().setAttribute("noteId", insertId);
            logger.debug("New Note succeeded: #" + insertId);
        } else {
            // error messages
            logger.debug("New Note failed");
        }

        resp.sendRedirect("/loadClient");
    }
}