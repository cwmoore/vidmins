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
                , "directories"
                , "currentVideo"
                , "video"
                , "videos"
                , "currentNote"
                , "note"
                , "notes"
        };

        // TODO see if simplifying the sessionScope obviates this class,
        // TODO ie. use user.directories(n).videos(n).notes(n) ???

        // TODO again, reconsider the wisdom of unsetting everything without re-setting some values

        for (String attribute : attributes) {
            logger.debug("Removing session attribute '" + attribute + "'");
            session.removeAttribute(attribute);
        }
    }
}