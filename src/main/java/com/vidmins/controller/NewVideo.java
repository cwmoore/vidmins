package com.vidmins.controller;

import com.vidmins.entity.Video;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to add video
 *
 * @author cwmoore
 */

@WebServlet(
        name = "newVideo",
        urlPatterns = {"/new-video"}
)

public class NewVideo extends HttpServlet {
    private Logger logger;

    /**
     * Initialize variables
     */
    public void init() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting servlet");
    }

    /**
     *  Handle a GET request
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // if user logged in
        // if new video parameter is set
        // if video parameter represents a viewable video
        // add the new video to `video` and `user_videos`
        // return to watcher to load new video 
        
        request.setAttribute("video", addNewVideo(request));
        
        String url = "/";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Add the video to the database, linked with the current user.
     * @param request
     * @return
     */
    private Video addNewVideo(HttpServletRequest request) {
        // user logged in?

        String youTubeId = request.getParameter("v");
        // get video information via YT Data API
        // title, description, duration, license, owner, channel

        return new Video();
    }

    
    /**
     * Handle a POST request
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/";
        response.sendRedirect(url);
    }
}