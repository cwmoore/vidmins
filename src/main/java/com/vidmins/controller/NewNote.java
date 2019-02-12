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

//            noteFromFormData.setLabel(req.getParameter("label"));
//            noteFromFormData.setText(req.getParameter("note_text"));
//            noteFromFormData.setStart(Integer.parseInt(req.getParameter("timeStampStart")));
//            noteFromFormData.setEnd(Integer.parseInt(req.getParameter("timeStampEnd")));
//            noteFromFormData.setVideoId(Integer.parseInt(req.getParameter("videoId")));
//            noteFromFormData.setUserId(Integer.parseInt(req.getParameter("userId")));

//            Map<String, String[]> noteFields = new TreeMap<>(req.getParameterMap());
//
//            noteData.setNewNoteFromAttributes(noteFields);
            noteFromFormData = noteData.setNewNote(noteFromFormData);
//            int insertId = noteData.setNewNote(noteFromFormData);
//            noteFromFormData.setId(insertId);
            logger.debug("noteFromFormData: " + noteFromFormData.toString());

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