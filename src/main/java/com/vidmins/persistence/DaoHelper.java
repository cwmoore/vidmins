package com.vidmins.persistence;

import com.vidmins.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DaoHelper {
    
    private Logger logger;

    public GenericDao<User> user;
    public GenericDao<Directory> directory;
    public GenericDao<Video> video;
    public GenericDao<YouTubeVideo> youTubeVideo;
    public GenericDao<Note> note;
    
    public DaoHelper() {
        logger = LogManager.getLogger(this.getClass());
    }

    /**
     * Initialize database helpers
     */
    public void loadHelpers() {
        logger = LogManager.getLogger(this.getClass());
        logger.debug("loadHelpers()");


        user = new GenericDao<>(User.class);
        directory = new GenericDao<>(Directory.class);
        video = new GenericDao<>(Video.class);
        note = new GenericDao<>(Note.class);
        youTubeVideo = new GenericDao<>(YouTubeVideo.class);
    }

    /**
     * Initialize database helpers
     */
    public void loadHelpers(HttpServletRequest request) {
        logger = LogManager.getLogger(this.getClass());
        logger.debug("loadHelpers(request)");

        //loadHelpers();

        if (user == null) {
            if (request.getSession().getAttribute("userDao") == null) {
                user = new GenericDao<>(User.class);
                request.getSession().setAttribute("userDao", user);
            } else {
                user = (GenericDao<User>) request.getSession().getAttribute("userDao");
            }
        }

        if (directory == null) {
            if (request.getSession().getAttribute("directoryDao") == null) {
                directory = new GenericDao<>(Directory.class);
                request.getSession().setAttribute("directoryDao", directory);
            } else {
                directory = (GenericDao<Directory>) request.getSession().getAttribute("directoryDao");
            }
        }

        if (video == null) {
            if (request.getSession().getAttribute("videoDao") == null) {
                video = new GenericDao<>(Video.class);
                request.getSession().setAttribute("videoDao", video);
            } else {
                video = (GenericDao<Video>) request.getSession().getAttribute("videoDao");
            }
        }

        if (youTubeVideo == null) {
            if (request.getSession().getAttribute("youTubeVideoDao") == null) {
                youTubeVideo = new GenericDao<>(YouTubeVideo.class);
                request.getSession().setAttribute("youTubeVideoDao", youTubeVideo);
            } else {
                youTubeVideo = (GenericDao<YouTubeVideo>) request.getSession().getAttribute("youTubeVideoDao");
            }
        }

        if (note == null) {
            if (request.getSession().getAttribute("noteDao") == null) {
                note = new GenericDao<>(Note.class);
                request.getSession().setAttribute("noteDao", note);
            } else {
                note = (GenericDao<Note>) request.getSession().getAttribute("noteDao");
            }
        }
    }
}
