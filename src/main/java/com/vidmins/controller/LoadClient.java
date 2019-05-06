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

    private GenericDao<User> userDao;
    private GenericDao<Directory> directoryDao;
    private GenericDao<Video> videoDao;
    private GenericDao<Note> noteDao;

    /**
     * Initialize session
     */
    @Override
    public void init() throws ServletException {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting LoadClient servlet");
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

        if (request.authenticate(response)) {
            logger.debug("authenticated");
            logger.debug(request.getRemoteUser());
            logger.debug(request.getUserPrincipal());
            logger.debug(request.getAuthType());
        } else {
            logger.debug("not authenticated");
            throw new ServletException("NOT AUTHENTICATED");
        }

        loadHelpers(request);

        String requestParams = "?";

        HttpSession session = request.getSession();

        logger.debug("in loadClient");

        // logged in user, gather related objects
        if (session.getAttribute("user") != null) {
            logger.debug("user != null", session.getAttribute("user"));
            //User user = (User) session.getAttribute("user");
        }

        if (request.getRemoteUser() != null) { // TODO check role too

            logger.debug(request.getRemoteUser());
            User user = userDao.findByPropertyEqual("userName", request.getRemoteUser()).get(0);
            logger.debug("User: " + user);
            session.setAttribute("user", user);

            List<Directory> directories = user.getDirectories();
            Video currentVideo = null;
            Directory defaultDirectory;

            if (directories.size() == 0) {
                defaultDirectory = new Directory(
                        "First Directory"
                        , "Directories organize sets of videos."
                        , user);
                int dirInsertId = directoryDao.insert(defaultDirectory);
                user.addDirectory(directoryDao.getById(dirInsertId));

                directories.add(defaultDirectory);
                // TODO add beginner video for first time user

            } else { // get videos for first directory
                logger.debug("Found " + directories.size() + " directories");

                defaultDirectory = directories.get(0);

            }

            // change this to use a different video to preload
            if (session.getAttribute("currentVideo") != null) {
                currentVideo = (Video) session.getAttribute("currentVideo");
            }

            if (defaultDirectory != null) {

                List<Video> videos = defaultDirectory.getVideos();

                session.setAttribute("videos", videos);
                // TODO select the best video (instead of just the first)
                if (videos.size() > 0) {
                    currentVideo = videos.get(0);
                }
                session.setAttribute("defaultDirectory", defaultDirectory);
            }
            session.setAttribute("directories", directories);


            // TODO move url param into a clean URL session object
            if (request.getParameter("videoId") != null) {
                if (request.getParameter("videoId").matches("\\d+")) {

                    int videoId = Integer.parseInt(request.getParameter("videoId"));
                    currentVideo = videoDao.getById(videoId);
                }
            }

            // TODO dedup this with line 135
            if (currentVideo != null) {
                session.setAttribute("currentVideo", currentVideo);

                // notes for the first video
                session.setAttribute("notes", currentVideo.getNotes());
                // TODO undupe the notes attribute of video by changing the properties in the JSP
            }

            session.setAttribute("title", "The Video Minutes App");
        } else {
            // user is not logged in
            logger.debug("Not logged in.");
            //request.getRequestDispatcher("/").forward(request, response);
        }
/*
        // write out session attributes for debugging
        Enumeration keys = session.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            if (session.getAttribute(key) != null){
                String msg = key + ": " + session.getAttribute(key) + "\n";
                logger.debug(msg);
            } else {
                logger.debug(key + " is NULL");
            }
        }*/

        // build URL params as needed
        String url = "/index.jsp";
        if (requestParams.length() > 1) {
            url += requestParams;
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }


    /**
     * Handle a POST request
     * @param request the request object
     * @param response the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }
}