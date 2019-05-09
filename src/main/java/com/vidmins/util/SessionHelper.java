package com.vidmins.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHelper {

    Logger logger = LogManager.getLogger(this.getClass());

    public void resetAll(HttpServletRequest request) {

        HttpSession session = request.getSession();

        String[] attributes = {
                "currentDirectory"
                , "directory"
                , "currentVideo"
                , "video"
                , "currentNote"
                , "note"
        };

        for (String attribute : attributes) {
            logger.debug("Removing session attribute '" + attribute + "'");
            session.removeAttribute(attribute);
        }
    }
}