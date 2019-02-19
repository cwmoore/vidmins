package com.vidmins.controller;

import com.vidmins.persistence.NoteData;
import com.vidmins.persistence.UserData;
import com.vidmins.entity.*;
import com.vidmins.persistence.VideoData;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 * @author pwaite
 *
 * Add search handling
 * @author cwmoore
 */

@WebServlet(
        name = "loadClient",
        urlPatterns = {"/loadClient"}
)

public class LoadClient extends HttpServlet {
    private Logger logger;

    UserData userData;
    VideoData videoData;
    NoteData noteData;

    HttpSession session;

    /**
     * Initialize session
     */
    public void init() throws ServletException {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting NewNote servlet");
    }

    /**
     * Initialize database helpers
     */
    public void loadHelpers(HttpServletRequest request) {
        logger = LogManager.getLogger(this.getClass());
        logger.debug("loadHelpers()");

        session = request.getSession();

        if (userData == null) {
            if (session.getAttribute("userData") == null) {
                userData = new UserData();
                session.setAttribute("userData", userData);
            } else {
                userData = (UserData) session.getAttribute("userData");
            }
        }

        if (videoData == null) {
            if (session.getAttribute("videoData") == null) {
                videoData = new VideoData();
                session.setAttribute("videoData", videoData);
            } else {
                videoData = (VideoData) session.getAttribute("videoData");
            }
        }

        if (noteData == null) {
            if (session.getAttribute("noteData") == null) {
                noteData = new NoteData();
                session.setAttribute("noteData", noteData);
            } else {
                noteData = (NoteData) session.getAttribute("noteData");
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

        String requestParams = "?";

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");

            List<Video> videos = userData.getUserVideos(user);
            session.setAttribute("videos", videos);

            if (request.getParameter("videoId") != null) {
                if (request.getParameter("videoId").matches("\\d+")) {

                    session.setAttribute("currentVideo",
                            videoData.fromId(Integer.parseInt(request.getParameter("videoId"))));

                    session.setAttribute("notes",
                            videoData.getVideoNotes(user.getId(), Integer.parseInt(request.getParameter("videoId"))));
                }
            }
            //if (session.getAttribute("currentVideo") != null) {
                // session.setAttribute("notes", userData.getVideoNotes(Integer.toString(videos.get(1).getId())));
                // session.setAttribute("currentVideo", videos.get(1));
            //}


            session.setAttribute("title", "The Video Minutes App");
        } // else { // user is not logged in }

        Enumeration keys = session.getAttributeNames();

        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String msg = key + ": " + session.getAttribute(key) + '\n';
            logger.debug(msg);
        }

        String url = "/index.jsp";
        if (requestParams.length() > 1) {
            url += requestParams;
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}