package com.vidmins.controller;

import com.vidmins.entity.Note;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
import com.vidmins.persistence.DaoHelper;
import com.vidmins.persistence.GenericDao;

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
 * Store a note.
 * @author cwmoore
 */

@WebServlet(
        name = "deleteVideo",
        urlPatterns = {"/delete-video"}
)

public class DeleteVideo extends HttpServlet {

    private Logger logger;
    private DaoHelper dao;

    /**
     * Initialize session
     */
    @Override
    public void init() throws ServletException {

        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting EditNote servlet");
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
        if (request.getParameter("id") != null) {
            int videoId = Integer.parseInt(request.getParameter("id"));
            video = dao.video.getById(videoId);
            dao.video.delete(video);
        } else {
            video = null;
        }

        logger.debug("delete video by id: " + request.getParameter("id") + "\n" + video);

        String url = "loadClient";
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