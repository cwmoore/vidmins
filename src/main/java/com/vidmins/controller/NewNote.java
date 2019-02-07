package com.vidmins.controller;

import com.vidmins.entity.Note;
import com.vidmins.persistence.NoteData;

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
        urlPatterns = {"/new-note"}
)

public class NewNote extends HttpServlet {

    /**
     * Handle a POST request (new note)
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NoteData noteData = new NoteData();

        if (req.getParameter("noteId") != null &&
                req.getParameter("label") != null &&
                req.getParameter("note_text") != null &&
                req.getParameter("time_stamp_start") != null &&
                req.getParameter("time_stamp_end") != null &&
                req.getParameter("videoId") != null &&
                req.getParameter("userId") != null) {

            Map<String, String[]> noteFields = new TreeMap<>(req.getParameterMap());
            int insertId = noteData.setNewNote(noteFields);

            req.getSession().setAttribute("noteId", insertId);
        } else {
            // error messages
        }

        resp.sendRedirect("/loadClient");
    }
}