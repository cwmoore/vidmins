package com.vidmins.controller;

import com.vidmins.entity.Video;
import com.vidmins.persistence.DaoHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Edit a video.
 * @author cwmoore
 */

@WebServlet(
        name = "editVideo",
        urlPatterns = {"/edit-video"}
)

public class EditVideo extends HttpServlet {

    private Logger logger;
    private DaoHelper dao;

    /**
     * Initialize session
     */
    @Override
    public void init() throws ServletException {

        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting EditVideo servlet");
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

        dao.loadHelpers(request);

        Video video;
        if (request.getParameter("videoId") != null) {
            int videoId = Integer.parseInt(request.getParameter("videoId"));
            video = dao.video.getById(videoId);
            request.getSession().setAttribute("currentVideo", video);
        } else {
            video = null;
        }

        logger.debug("video from id: " + request.getParameter("videoId") + "\n" + video);

        String url = "/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
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