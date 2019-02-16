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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadHelpers(request);

        String requestParams = "?";

        if (request.getSession().getAttribute("user") != null) {
            User user = (User) request.getSession().getAttribute("user");

            List<Video> videos = userData.getUserVideos(user);
            request.getSession().setAttribute("videos", videos);

            if (request.getParameter("videoId") != null) {
                if (request.getParameter("videoId").matches("\\d+")) {

                    request.getSession().setAttribute("currentVideo",
                            videoData.fromId(Integer.parseInt(request.getParameter("videoId"))));

                    request.getSession().setAttribute("notes",
                            videoData.getVideoNotes(user.getId(), Integer.parseInt(request.getParameter("videoId"))));
                }
            }
            //if (request.getSession().getAttribute("currentVideo") != null) {
                // request.getSession().setAttribute("notes", userData.getVideoNotes(Integer.toString(videos.get(1).getId())));
                // request.getSession().setAttribute("currentVideo", videos.get(1));
            //}


            request.getSession().setAttribute("title", "The Video Minutes App");
        } // else { // user is not logged in }

        HttpSession session = request.getSession();
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