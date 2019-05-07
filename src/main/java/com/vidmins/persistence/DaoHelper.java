package com.vidmins.persistence;

import com.vidmins.entity.Directory;
import com.vidmins.entity.Note;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DaoHelper {
    
    private Logger logger;

    public GenericDao<User> user;
    public GenericDao<Directory> directory;
    public GenericDao<Video> video;
    public GenericDao<Note> note;
    
    public DaoHelper() {
        logger = LogManager.getLogger(this.getClass());
    }
    
    /**
     * Initialize database helpers
     */
    public void loadHelpers(HttpServletRequest request) {
        logger = LogManager.getLogger(this.getClass());
        logger.debug("loadHelpers()");

        if (user == null) {
            if (request.getSession().getAttribute("userDao") == null) {
                user = new GenericDao<>(User.class);
                request.getSession().setAttribute("userDao", user);
            } else {
                user = (GenericDao<User>) request.getSession().getAttribute("user");
            }
        }

        if (directory == null) {
            if (request.getSession().getAttribute("directoryDao") == null) {
                directory = new GenericDao<>(Directory.class);
                request.getSession().setAttribute("directoryDao", directory);
            } else {
                directory = (GenericDao<Directory>) request.getSession().getAttribute("directory");
            }
        }

        if (video == null) {
            if (request.getSession().getAttribute("videoDao") == null) {
                video = new GenericDao<>(Video.class);
                request.getSession().setAttribute("videoDao", video);
            } else {
                video = (GenericDao<Video>) request.getSession().getAttribute("video");
            }
        }

        if (note == null) {
            if (request.getSession().getAttribute("noteDao") == null) {
                note = new GenericDao<>(Note.class);
                request.getSession().setAttribute("noteDao", note);
            } else {
                note = (GenericDao<Note>) request.getSession().getAttribute("note");
            }
        }
    }
}
