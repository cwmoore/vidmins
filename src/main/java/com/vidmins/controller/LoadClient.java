package com.vidmins.controller;

import com.vidmins.entity.*;
import com.vidmins.persistence.DaoHelper;
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
    private DaoHelper dao;
    HttpSession session;

    /**
     * Initialize session
     */
    @Override
    public void init() throws ServletException {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting LoadClient servlet");
        dao = new DaoHelper();
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

        logger.debug(request.getRemoteUser());
        logger.debug(request.getUserPrincipal());
        logger.debug(request.getAuthType());

        dao.loadHelpers(request);

        session = request.getSession();

        logger.debug("in loadClient");

        // logged in user, gather related objects
        if (session.getAttribute("user") != null) {
            logger.debug("user != null", session.getAttribute("user"));
            //User user = (User) session.getAttribute("user");
        }

        String userName = request.getRemoteUser();
        //org.apache.catalina.authenticator.Principle principle = request.getUserPrincipal();
        //logger.debug("principle: " + principle);
        if (userName != null) { // TODO check role too
            try {
                loadUserData(request);
            } catch (Exception e) {
                logger.debug(e.toString());
            }
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
//        }*/
//
//        // build URL params as needed
//        String url = "/index.jsp";
//        if (requestParams.length() > 1) {
//            url += requestParams;
//        }

        String url = "/index.jsp";
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


    /**
     * Load data for a user's session
     *
     * @param request the HttpServletRequest
     * @throws Exception an Exception
     */
    public void loadUserData(HttpServletRequest request)
            throws Exception {

        dao.loadHelpers(request);
        String userName = request.getRemoteUser();
        session = request.getSession();

        logger.debug(userName);

        User user;
        List<User> users = dao.user.findByPropertyEqual("userName", userName);
        if (users.size() == 1) {
            user = users.get(0);
        } else {
            throw new Exception("userName mismatch: users.size!=1");
        }

        logger.debug("User: " + user);

        session.setAttribute("user", user);


// TODO proper logic use a method like requireContext()
// get current note if possible request.getParameter("noteId")
// if current note, set currentVideo = note.getVideo()
        // else if current video: currentVideo = currentVideo
// if current video set currentDirectory = currentVideo.getDirectory()




        // previously set current objects
        Directory currentDirectory = (Directory) session.getAttribute("currentDirectory");
        Video currentVideo = (Video) session.getAttribute("currentVideo");
        Note currentNote = (Note) session.getAttribute("currentNote");






        // first time user's first directory
        if (user.getDirectories().size() == 0) {
            currentDirectory = createDefaultDirectory(user);
        } else {
            // set the current directory to the user's first
            List<Directory> directories = user.getDirectories();

            session.setAttribute("directories", directories);

            logger.debug("Found " + directories.size() + " directories");
            if (currentDirectory == null) {
                currentDirectory = directories.get(0);
            }
        }









        // set current directory from directoryId parameter
        if (request.getParameter("directoryId") != null) {
            String inputDefaultId = request.getParameter("directoryId");

            try {
                int defaultId = Integer.parseInt(inputDefaultId);

                if (defaultId > 0) {
                    currentDirectory = dao.directory.getById(defaultId);
                }
            } catch (NumberFormatException nfe) {
                logger.debug("defaultDirectory id...", nfe);
            }
        }






        // load videos from chosen directory
        if (currentDirectory != null) {

            List<Video> videos = currentDirectory.getVideos();

            // TODO don't use 'videos' use 'currentDirectory.videos'
            session.setAttribute("videos", videos);

            if (videos.size() > 0) {
                // TODO select the best video (instead of just the first)
                currentVideo = videos.get(0);
            }
            session.setAttribute("currentDirectory", currentDirectory);
        }







        // load current video from videoId parameter
        if (request.getParameter("videoId") != null) {
            if (request.getParameter("videoId").matches("\\d+")) {

                int videoId = Integer.parseInt(request.getParameter("videoId"));
                currentVideo = dao.video.getById(videoId);

                session.setAttribute("currentVideo", currentVideo);
                session.setAttribute("currentDirectory", currentVideo.getDirectory());

                // notes for the current video
                session.setAttribute("notes", currentVideo.getNotes());

                if (session.getAttribute("currentNote") == null) {
                    if (currentVideo.getNotes().size() > 0) {
                        session.setAttribute("currentNote", currentVideo.getNotes().get(0));
                    }
                }
            }
        }


        session.setAttribute("title", "The Video Minutes App");
    }

    /**
     * Creates a default directory for a new user account
     * @param user the user
     * @return the new directory
     */
    public Directory createDefaultDirectory(User user) {

        Directory defaultDirectory = new Directory(
                "First Directory"
                , "Directories organize sets of videos."
                , user
        );

        // TODO add beginner video for first time user);
        Video newUserVideo = new Video(dao.youTubeVideo.getById(1), "To Begin");
        defaultDirectory.addVideo(newUserVideo);

        // create the directory
        int dirInsertId = dao.directory.insert(defaultDirectory);

        // add the new directory to the user object
        user.addDirectory(dao.directory.getById(dirInsertId));

        // save
        dao.user.saveOrUpdate(user);

        return user.getDirectories().get(0);
    }
}