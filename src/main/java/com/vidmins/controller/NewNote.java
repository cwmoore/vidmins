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
        name = "newNote",
        urlPatterns = {"/new-note"}
)

public class NewNote extends HttpServlet {

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
        logger.info("Starting NewNote servlet");
    }


    /**
     * Initialize database helpers
     */
    public void loadHelpers(HttpServletRequest request) {
        logger = LogManager.getLogger(this.getClass());
        logger.debug("init()");

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
     * Handle a POST request
     * @param request the request object
     * @param response the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadHelpers(request);

        List<String> requestParams = new ArrayList<>();
        String url = "/loadClient";
        NoteData noteData = new NoteData();

        logger.debug(request.getParameterMap());

        if (request.getParameter("label") != null &&
                request.getParameter("note_text") != null &&
                request.getParameter("timeStampStart") != null &&
                /* request.getParameter("timeStampEnd") != null && */
                request.getParameter("userId") != null &&
                request.getParameter("videoId") != null) {

            //Note noteFromFormData = new Note();

            // logger.debug("noteFromFormData: " + noteFromFormData.toString());

            Note noteFromFormData = new Note(request.getParameter("label")
                    , request.getParameter("note_text")
                    , Integer.parseInt(request.getParameter("timeStampStart"))
                   /*  , Integer.parseInt(request.getParameter("timeStampEnd"))*/
                    , Integer.parseInt(request.getParameter("userId"))
                    , Integer.parseInt(request.getParameter("videoId"))
            );

            logger.debug("noteFromFormData before: " + noteFromFormData.toString());

            if (request.getParameter("noteId") != null) {
                noteFromFormData.setId(Integer.parseInt(request.getParameter("noteId")));
                noteFromFormData = noteData.updateNote(noteFromFormData);
            } else {
                noteFromFormData = noteData.setNewNote(noteFromFormData);
            }

            logger.debug("noteFromFormData after: " + noteFromFormData.toString());

            request.getSession().setAttribute("note", null);
            requestParams.add("videoId=" + request.getParameter("videoId"));

            if (request.getParameter("timeStampStart").matches("\\d+")) {
                requestParams.add("startTime=" + request.getParameter("timeStampStart"));
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

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/loadClient");
//        dispatcher.forward(request, response);
        response.sendRedirect(url);
    }
}