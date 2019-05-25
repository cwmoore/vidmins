package com.vidmins.controller;

import com.vidmins.entity.*;
import com.vidmins.persistence.DaoHelper;
import com.vidmins.persistence.GenericDao;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

        try {
            reliableContext(request, user);
        } catch (Exception e) {
            logger.debug("reliableContext error", e);
        }
//
//
//// TODO proper logic use a method like requireContext()
//// get current note if possible request.getParameter("noteId")
//// if current note, set currentVideo = note.getVideo()
//        // else if current video: currentVideo = currentVideo
//// if current video set currentDirectory = currentVideo.getDirectory()
//
//
//
//
//        // previously set current objects
//        Directory currentDirectory = (Directory) session.getAttribute("currentDirectory");
//        Video currentVideo = (Video) session.getAttribute("currentVideo");
//        Note currentNote = (Note) session.getAttribute("currentNote");
//
//
//
//
//
//
//        // first time user's first directory
//        if (user.getDirectories().size() == 0) {
//            currentDirectory = createDefaultDirectory(user);
//        } else {
//            // set the current directory to the user's first
//            List<Directory> directories = user.getDirectories();
//
//            session.setAttribute("directories", directories);
//
//            logger.debug("Found " + directories.size() + " directories");
//            if (currentDirectory == null) {
//                currentDirectory = directories.get(0);
//            }
//        }
//
//
//
//
//
//
//
//
//
//        // set current directory from directoryId parameter
//        if (request.getParameter("directoryId") != null) {
//            String inputDefaultId = request.getParameter("directoryId");
//
//            try {
//                int defaultId = Integer.parseInt(inputDefaultId);
//
//                if (defaultId > 0) {
//                    currentDirectory = dao.directory.getById(defaultId);
//                }
//            } catch (NumberFormatException nfe) {
//                logger.debug("defaultDirectory id...", nfe);
//            }
//        }
//
//
//
//
//
//
//        // load videos from chosen directory
//        if (currentDirectory != null) {
//
//            List<Video> videos = currentDirectory.getVideos();
//
//            // TODO don't use 'videos' use 'currentDirectory.videos'
//            session.setAttribute("videos", videos);
//
//            if (videos.size() > 0) {
//                // TODO select the best video (instead of just the first)
//                currentVideo = videos.get(0);
//            }
//            session.setAttribute("currentDirectory", currentDirectory);
//        }
//
//
//
//
//
//
//
//        // load current video from videoId parameter
//        if (request.getParameter("videoId") != null) {
//            if (request.getParameter("videoId").matches("\\d+")) {
//
//                int videoId = Integer.parseInt(request.getParameter("videoId"));
//                currentVideo = dao.video.getById(videoId);
//
//                session.setAttribute("currentVideo", currentVideo);
//                session.setAttribute("currentDirectory", currentVideo.getDirectory());
//
//                // notes for the current video
//                session.setAttribute("notes", currentVideo.getNotes());
//
//                if (session.getAttribute("currentNote") == null) {
//                    if (currentVideo.getNotes().size() > 0) {
//                        session.setAttribute("currentNote", currentVideo.getNotes().get(0));
//                    }
//                }
//            }
//        }


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

        // add beginner video for first time user
        Video newUserVideo = new Video(dao.youTubeVideo.getById(1), "To Begin");
        defaultDirectory.addVideo(newUserVideo);

        // create the directory
        //int dirInsertId = dao.directory.insert(defaultDirectory);

        // add the new directory to the user object
        //defaultDirectory = dao.directory.getById(dirInsertId);
        user.addDirectory(defaultDirectory);

        // save
        dao.user.saveOrUpdate(user);

        return user.getDirectories().get(0);
    }

    /**
     * Set all session data to a useful state, continuous with the previous actions
     * @param request the HttpServletRequest
     * @param user the user
     */
    public void reliableContext(HttpServletRequest request, User user) {
        logger.debug("In reliableContext");
        HttpSession session = request.getSession();

        // store previous settings
        Note previousNote = null;
        if (session.getAttribute("currentNote") != null) {
            previousNote = (Note) session.getAttribute("currentNote");
        }
        session.setAttribute("currentNote", null);

        Video previousVideo = null;
        if (session.getAttribute("currentVideo") != null) {
            previousVideo = (Video) session.getAttribute("currentVideo");
        }
        session.setAttribute("currentVideo", null);

        Directory previousDirectory = null;
        if (session.getAttribute("currentDirectory") != null) {
            previousDirectory = (Directory) session.getAttribute("currentDirectory");
        }
        session.setAttribute("currentDirectory", null);


        Note currentNote = null;
        Video currentVideo = null;
        Directory currentDirectory = null;

        if (request.getParameter("noteId") != request.getParameter("noteId")
                && request.getParameter("noteId").matches("\\d+")) {

            logger.debug("Using noteId");
            currentNote = dao.note.getById(Integer.parseInt(request.getParameter("noteId")));
            if (currentNote != null) {
                previousNote = null;
                previousVideo = null;
                previousDirectory = null;
                setAllFromNote(session, currentNote);
            }

        } else if (request.getParameter("videoId") != null
                && request.getParameter("videoId").matches("\\d+")) {

            logger.debug("Using videoId");
            currentVideo = dao.video.getById(Integer.parseInt(request.getParameter("videoId")));
            if (currentVideo != null) {
                previousNote = null;
                previousVideo = null;
                previousDirectory = null;
                setAllFromVideo(session, currentVideo);
            }

        } else if (request.getParameter("directoryId") != null
                && request.getParameter("directoryId").matches("\\d+")) {

            logger.debug("Using directoryId");
            currentDirectory = dao.directory.getById(Integer.parseInt(request.getParameter("directoryId")));
            if (currentDirectory != null) {
                previousNote = null;
                previousVideo = null;
                previousDirectory = null;
                setAllFromDirectory(session, currentDirectory);
            }

        } else {
            logger.debug("else setAllFromUser");
            setAllFromUser(session, user);
            previousNote = null;
            previousVideo = null;
            previousDirectory = null;
        }

        if (previousNote != null) {
            logger.debug("Using previousNote");
            setAllFromNote(session, previousNote);

        } else if (previousVideo != null) {
            logger.debug("Using previousVideo");
            setAllFromVideo(session, previousVideo);

        } else if (previousDirectory != null) {
            logger.debug("Using previousDirectory");
            setAllFromDirectory(session, previousDirectory);

        }
        session.setAttribute("directories", user.getDirectories());

        logger.debug("end reliableContext");
    }

    /**
     * Given a note, sets all other current entities
     */
    public void setAllFromNote(HttpSession session, Note currentNote) {
        // new current entities
        logger.debug("In setAllFromNote");
        Video currentVideo = currentNote.getVideo();
        Directory currentDirectory = currentVideo.getDirectory();

        setAll(session, currentNote, currentVideo, currentDirectory);
    }

    /**
     * Given a video, sets all other current entities
     */
    public void setAllFromVideo(HttpSession session, Video currentVideo) {
        // new current entities
        logger.debug("In setAllFromVideo");
        Directory currentDirectory = currentVideo.getDirectory();

        Note currentNote = findLatestNoteFromVideo(currentVideo);

        setAll(session, currentNote, currentVideo, currentDirectory);
    }

    /**
     * Given a directory, sets all other current entities
     */
    public void setAllFromDirectory(HttpSession session, Directory currentDirectory) {
        // new current entities
        logger.debug("In setAllFromDirectory");
        Video currentVideo = findLatestVideoFromDirectory(currentDirectory);

        Note currentNote = null;
        if (currentVideo != null){
            currentNote = findLatestNoteFromVideo(currentVideo);
        }

        setAll(session, currentNote, currentVideo, currentDirectory);
    }

    /**
     * Given a user, sets all other current entities
     */
    public void setAllFromUser(HttpSession session, User user) {
        logger.debug("In setAllFromUser");
        if (user.getDirectories().size() == 0) {
            createDefaultDirectory(user);
        }

        // findLatestObject(), select from the return based on type?

        Directory currentDirectory = findLatestDirectoryFromUser(user);
        Video currentVideo = findLatestVideoFromDirectory(currentDirectory);
        Note currentNote = findLatestNoteFromVideo(currentVideo);
        setAll(session, currentNote, currentVideo, currentDirectory);
    }

    /**
     * Set all of the latest entities associated with the current user's session
     * @param session
     * @param currentNote the current note
     * @param currentVideo the current video
     * @param currentDirectory the current directory
     */
    public void setAll(HttpSession session, Note currentNote, Video currentVideo, Directory currentDirectory) {
        logger.debug("in setAll");
        session.setAttribute("currentNote", currentNote);
        session.setAttribute("currentVideo", currentVideo);
        session.setAttribute("currentDirectory", currentDirectory);
    }



    /**
     * get latest accessed note in a video
     * @param video the video object
     * @return the latest accessed note
     */
    private Note findLatestNoteFromVideo(Video video) {
        logger.debug("in findLatestNoteFromVideo");
        Note latestNote = null;
        if (video.getNotes().size() > 0) {
            latestNote = video.getNotes().get(0);
        }
        LocalDateTime latest = LocalDateTime.MIN;
        for (Note note : video.getNotes()) {
            if (note.getLastAccessDate().isAfter(latest)) {
                latest = note.getLastAccessDate();
                latestNote = note;
            }
        }
        return latestNote;
    }

    /**
     * get latest accessed video in a directory
     * @param directory the directory object
     * @return the latest accessed video
     */
    private Video findLatestVideoFromDirectory(Directory directory) {
        logger.debug("in findLatestVideoFromDirectory");
        Video latestVideo = null;
        if (directory.getVideos().size() > 0) {
            latestVideo = directory.getVideos().get(0);
        }
        LocalDateTime latest = LocalDateTime.MIN;
        for (Video video : directory.getVideos()) {
            if (video.getLastAccessDate().isAfter(latest)) {
                latest = video.getLastAccessDate();
                latestVideo = video;
            }
        }
        return latestVideo;
    }

    /**
     * get latest accessed directory for a user
     * @param user the user object
     * @return the latest accessed directory
     */
    private Directory findLatestDirectoryFromUser(User user) {
        logger.debug("in findLatestDirectoryFromUser");
        Directory latestDirectory = null;
        if (user.getDirectories().size() == 0) {
            latestDirectory = createDefaultDirectory(user);
        } else {
            latestDirectory = user.getDirectories().get(0);
        }

        LocalDateTime latest = LocalDateTime.MIN;
        for (Directory directory : user.getDirectories()) {
            if (directory.getLastAccessDate().isAfter(latest)) {
                latest = directory.getLastAccessDate();
                latestDirectory = directory;
            }
        }
        return latestDirectory;
    }
}