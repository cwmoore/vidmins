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
        String url = "loadClient";

        logger.debug(request.getParameterMap());

        if (request.getParameter("label") != null &&
                request.getParameter("note_text") != null &&
                request.getParameter("timeStampStart") != null &&
                request.getParameter("videoId") != null)
        {

            Note noteFromFormData = new Note();
            if (request.getParameter("noteId") != null) {
                noteFromFormData.setId(Integer.parseInt(request.getParameter("noteId")));
            }

            noteFromFormData.setLabel(request.getParameter("label"));
            noteFromFormData.setText(request.getParameter("note_text"));
            noteFromFormData.setStart(Integer.parseInt(request.getParameter("timeStampStart")));

            noteFromFormData.setVideo(dao.video.getById(Integer.parseInt(request.getParameter("videoId"))));
            noteFromFormData.setCreateDatetime(LocalDateTime.now());

            //logger.debug("noteFromFormData before: " + noteFromFormData.toString());

            if (noteFromFormData.getId() > 0) {
                logger.debug("This is a note to be updated: " + noteFromFormData.getId());
                dao.note.saveOrUpdate(noteFromFormData);
            } else {
                logger.debug("This is a new note");
                dao.note.insert(noteFromFormData);
            }

            logger.debug("noteFromFormData after: " + noteFromFormData.toString());

            request.getSession().setAttribute("currentNote", noteFromFormData);
            request.getSession().setAttribute("currentVideo", noteFromFormData.getVideo());
            request.getSession().setAttribute("currentDirectory", noteFromFormData.getVideo().getDirectory());
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
        response.sendRedirect(url);
    }
}