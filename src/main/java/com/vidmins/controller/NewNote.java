package com.vidmins.controller;

import com.vidmins.entity.Note;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
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
import java.time.LocalDateTime;
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
    private DaoHelper dao;

    /**
     * Initialize session
     */
    @Override
    public void init() throws ServletException {

        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting NewNote servlet");

        dao = new DaoHelper();
    }

    /**
     * Handle a POST request of Note data
     * (edit existing note if ID present, else store new note)
     *
     * @param request the request object
     * @param response the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // make DAOs available
        dao.loadHelpers(request);

        List<String> requestParams = new ArrayList<>();

        logger.debug(request.getParameterMap());

        if (request.getParameter("label") != null &&
                request.getParameter("note_text") != null &&
                request.getParameter("timeStampStart") != null &&
                request.getParameter("videoId") != null)
        {

            Note noteFromFormData = new Note();
            try {
                int noteIdInt = Integer.parseInt(request.getParameter("noteId"));
                noteFromFormData.setId(noteIdInt);
            } catch (NumberFormatException nfe) {
                logger.debug("Note ID not an int", nfe);
            }

            noteFromFormData.setLabel(request.getParameter("label"));
            noteFromFormData.setText(request.getParameter("note_text"));
            noteFromFormData.setStart(Integer.parseInt(request.getParameter("timeStampStart")));

            try {
                int videoId = Integer.parseInt(request.getParameter("videoId"));
                noteFromFormData.setVideo(dao.video.getById(videoId));
            } catch (NumberFormatException nfe) {
                logger.debug("Could not process videoId", nfe);
            } catch (Exception e) {
                logger.debug("Could not locate video", e);
            }
            noteFromFormData.setCreateDatetime(LocalDateTime.now());

            logger.debug("noteFromFormData before: " + noteFromFormData.toString());

            if (noteFromFormData.getId() > 0) {
                logger.debug("This is a note to be updated: " + noteFromFormData.getId());
                dao.note.saveOrUpdate(noteFromFormData);
                noteFromFormData = dao.note.getById(noteFromFormData.getId());
            } else {
                logger.debug("This is a new note");
                int insertId = dao.note.insert(noteFromFormData);

                noteFromFormData = dao.note.getById(insertId);
            }

            logger.debug("noteFromFormData after: " + noteFromFormData.toString());

            request.getSession().setAttribute("currentNote", noteFromFormData);
            request.getSession().setAttribute("currentVideo", noteFromFormData.getVideo());
            request.getSession().setAttribute("currentDirectory", noteFromFormData.getVideo().getDirectory());
            //requestParams.add("videoId=" + request.getParameter("videoId"));

            if (request.getParameter("timeStampStart").matches("\\d+")) {
                requestParams.add("startTime=" + request.getParameter("timeStampStart"));
            }

        } else {
            // error messages
            logger.debug("New Note failed");
        }

        String url = buildUrlParams("loadClient", requestParams);
        logger.debug("URL to redirect from NewNote: " + url);
        response.sendRedirect(url);
    }

    private String buildUrlParams(String url, List<String> requestParams) {

        // build URL params
        if (requestParams.size() > 0) {
            url += "?";
            for (String param : requestParams) {
                url += param + "&";
            }
            // remove trailing ampersand
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }
}