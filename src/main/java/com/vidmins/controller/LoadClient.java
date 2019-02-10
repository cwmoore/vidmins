package com.vidmins.controller;

import com.vidmins.persistence.UserData;
import com.vidmins.entity.*;
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
        urlPatterns = {"/loadClient"}
)

public class LoadClient extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Initialize logging
     */
    public void init(HttpServletRequest request, HttpServletResponse response) {
    }

    /**
     * Handle a GET request
     * @param req the request object
     * @param resp the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserData userData = new UserData();

        if (req.getSession().getAttribute("user") != null) {
            User user = (User) req.getSession().getAttribute("user");

            List<Video> videos = userData.getUserVideos(user);
            req.getSession().setAttribute("videos", videos);

            if (req.getParameter("videoId") != null) {
                if (req.getParameter("videoId").matches("\\d+")) {
                    req.getSession().setAttribute("currentVideo", userData.getVideo(Integer.parseInt(req.getParameter("videoId"))));
                    req.getSession().setAttribute("notes",
                            userData.getVideoNotes(Integer.parseInt(req.getParameter("videoId"))));
                }
            }

            //if (req.getSession().getAttribute("currentVideo") != null) {
                // req.getSession().setAttribute("notes", userData.getVideoNotes(Integer.toString(videos.get(1).getId())));
                // req.getSession().setAttribute("currentVideo", videos.get(1));
            //}


            req.getSession().setAttribute("title", "The Video Minutes App");
        } // else { // user is not logged in }

        HttpSession session = req.getSession();
        Enumeration keys = session.getAttributeNames();

        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String msg = key + ": " + session.getAttribute(key) + '\n';
            logger.debug(msg);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}