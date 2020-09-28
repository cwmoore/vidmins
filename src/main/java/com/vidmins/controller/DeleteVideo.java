package com.vidmins.controller;

import com.vidmins.entity.Directory;
import com.vidmins.entity.Note;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
import com.vidmins.persistence.DaoHelper;
import com.vidmins.persistence.GenericDao;

import com.vidmins.util.SessionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Delete a video.
 * @author cwmoore
 */

@WebServlet(
        name = "deleteVideo",
        urlPatterns = {"/delete-video"}
)

public class DeleteVideo extends HttpServlet {

    private Logger logger;
    private DaoHelper dao;
    private SessionHelper sessionHelper;

    /**
     * Initialize session
     */
    @Override
    public void init() throws ServletException {

        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting DeleteVideo servlet");
        dao = new DaoHelper();
        sessionHelper = new SessionHelper();
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

        dao.loadHelpers(request);

        String url = "loadClient";
        Video video;
        if (request.getParameter("videoId") != null) {
            try {
                int videoId = Integer.parseInt(request.getParameter("videoId"));
                video = dao.video.getById(videoId);
                Directory currentDirectory = video.getDirectory();

                dao.video.delete(video);
                logger.debug("delete video by id: " + request.getParameter("videoId") + "\n" + video);

                request.getSession().setAttribute("currentDirectory", currentDirectory);

            } catch (NumberFormatException nfe) {
                logger.debug(nfe.toString());
            }
        } else {
            video = null;
        }

        response.sendRedirect(url);
//        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//        dispatcher.forward(request, response);
    }

    /**
     * Handle a GET request
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