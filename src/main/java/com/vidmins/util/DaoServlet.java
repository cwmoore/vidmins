package com.vidmins.util;

import com.vidmins.entity.Note;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
import com.vidmins.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DaoServlet {

    private Logger logger;
    private GenericDao<User> userDao;
    private GenericDao<Video> videoDao;
    private GenericDao<Note> noteDao;

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

}
