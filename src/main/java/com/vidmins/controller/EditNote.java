package com.vidmins.controller;

import com.vidmins.entity.Note;
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
 * Edit a note.
 * @author cwmoore
 */

@WebServlet(
        name = "editNote",
        urlPatterns = {"/edit-note"}
)

public class EditNote extends HttpServlet {

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

        Note note = null;
        if (request.getParameter("noteId") != null) {
            int noteId = Integer.parseInt(request.getParameter("noteId"));
            note = dao.note.getById(noteId);
            request.getSession().setAttribute("editNote", note);
            request.getSession().setAttribute("currentNote", note);
        } else {
            request.getSession().removeAttribute("editNote");
        }

        logger.debug("note from id: " + request.getParameter("noteId") + "\n" + note);

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