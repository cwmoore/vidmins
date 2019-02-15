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
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        NoteData noteData;
        if (req.getSession().getAttribute("noteData") == null){
            noteData = new NoteData();
            req.getSession().setAttribute("noteData", noteData);
        } else {
            noteData = (NoteData) req.getSession().getAttribute("noteData");
        }

        Note note;
        if (req.getParameter("editNote") != null) {
            int noteId = Integer.parseInt(req.getParameter("editNote"));
            note = noteData.fromId(noteId);
            req.getSession().setAttribute("note", note);
        } else {
            note = null;
        }
        
        logger.debug("note from id: " + req.getParameter("editNote") + "\n" + note);

        String url = "/";
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
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

        List<String> requestParams = new ArrayList<>();
        String url = "/loadClient";
        NoteData noteData = new NoteData();

        logger.debug(req.getParameterMap());

        if (req.getParameter("label") != null &&
                req.getParameter("note_text") != null &&
                req.getParameter("timeStampStart") != null &&
                /* req.getParameter("timeStampEnd") != null && */
                req.getParameter("userId") != null &&
                req.getParameter("videoId") != null) {

            //Note noteFromFormData = new Note();

            // logger.debug("noteFromFormData: " + noteFromFormData.toString());

            Note noteFromFormData = new Note(req.getParameter("label")
                    , req.getParameter("note_text")
                    , Integer.parseInt(req.getParameter("timeStampStart"))
                   /*  , Integer.parseInt(req.getParameter("timeStampEnd"))*/
                    , Integer.parseInt(req.getParameter("userId"))
                    , Integer.parseInt(req.getParameter("videoId"))
            );

            logger.debug("noteFromFormData before: " + noteFromFormData.toString());

            noteFromFormData = noteData.setNewNote(noteFromFormData);

            logger.debug("noteFromFormData after: " + noteFromFormData.toString());

            req.getSession().setAttribute("note", noteFromFormData);
            requestParams.add("videoId=" + req.getParameter("videoId"));

            if (req.getParameter("timeStampStart").matches("\\d+")) {
                requestParams.add("startTime=" + req.getParameter("timeStampStart"));
            }

        } else {
            // error messages
            logger.debug("New Note failed");
        }

        // build URL params
        if (requestParams.size() > 0) {
            url += "?";
            for (String param : requestParams) {
                url += param + "&";
            }
            // remove trailing ampersand
            url = url.substring(0, url.length() - 1);
        }
        logger.debug("URL to redirect from NewNote: " + url);

//        RequestDispatcher dispatcher = req.getRequestDispatcher("/loadClient");
//        dispatcher.forward(req, resp);
        resp.sendRedirect(url);
    }
}