package com.vidmins.controller;

import com.vidmins.entity.*;
import com.vidmins.persistence.GenericDao;
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

    GenericDao<User> userDao;
    GenericDao<Directory> directoryDao;
    GenericDao<Video> videoDao;
    GenericDao<Note> noteDao;

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

        if (userDao == null) {
            if (request.getSession().getAttribute("userDao") == null) {
                userDao = new GenericDao<>(User.class);
                request.getSession().setAttribute("userDao", userDao);
            } else {
                userDao = (GenericDao<User>) request.getSession().getAttribute("userDao");
            }
        }

        if (directoryDao == null) {
            if (request.getSession().getAttribute("directoryDao") == null) {
                directoryDao = new GenericDao<>(Directory.class);
                request.getSession().setAttribute("directoryDao", directoryDao);
            } else {
                directoryDao = (GenericDao<Directory>) request.getSession().getAttribute("directoryDao");
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

        String requestParams = "?";

        if (session == null) {
            session = request.getSession();
        }

        logger.debug("in loadClient");

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");


            // TODO investigate strategies for lazy loading user data
            // TODO start with a default view

            // get directories list for signed in user
            List<Directory> directories = directoryDao.findByPropertyEqual("user", user);
            session.setAttribute("directories", directories);

            // TODO optionally choose starting directory

            // get videos for first directory
            if (directories.size() > 0) {
                Directory defaultDirectory = directories.get(0);
                if (defaultDirectory != null) {
                    List<Video> videos = videoDao.findByPropertyEqual("directory", defaultDirectory);
                    session.setAttribute("videos", videos);
                }
            }

            // TODO optionally choose starting video
            if (request.getParameter("videoId") != null) {
                if (request.getParameter("videoId").matches("\\d+")) {

                    int videoId = Integer.parseInt(request.getParameter("videoId"));

                    Video currentVideo = videoDao.getById(videoId);
                    session.setAttribute("currentVideo", currentVideo);

                    // notes for the first video
                    session.setAttribute("notes",
                            noteDao.findByPropertyEqual("video", currentVideo));
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