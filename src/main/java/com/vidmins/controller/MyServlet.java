package com.vidmins.controller;

import com.google.api.services.youtube.YouTube;
import com.vidmins.entity.YouTubeVideo;
import com.vidmins.persistence.GenericDao;
import com.vidmins.youtube_data_api.*;
import com.vidmins.youtube_data_api.data.*;

import org.apache.catalina.Realm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "myServlet", urlPatterns = {"/my-servlet"})
public class MyServlet extends HttpServlet {

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.debug("in MyServlet.doGet()");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String youTubeId = req.getParameter("v");
        YTDataApi ytDataApi = new YTDataApi();
        YouTubeVideo youTubeVideo = ytDataApi.findVideoData(youTubeId);

        if (youTubeVideo != null) {
            logger.debug("Found video: " + youTubeVideo.toString());
            writer.println("Found video: " + youTubeVideo.toString());
        } else {
            logger.debug("Could not find video: " + youTubeId);
            writer.println("Could not find video: " + youTubeId);
        }

        //        Search search = new Search();
//        //search.doSearch("3qK82JvRY5s");
//
//        GenericDao<YouTubeVideo> youTubeVideoDao = new GenericDao<>(YouTubeVideo.class);
//
//        YouTubeVideo youTubeVideo;
//        List<YouTubeVideo> youTubeVideoList = youTubeVideoDao.findByPropertyEqual("youTubeId", youTubeId);
//        if (youTubeVideoList.size() > 0) {
//            youTubeVideo = youTubeVideoList.get(0);
//        } else {
//            youTubeVideo = search.doSearch(youTubeId);
//            try {
//                youTubeVideoDao.insert(youTubeVideo);
//            } catch (Exception e) {
//                logger.debug("Inserting youTubeVideo", e);
//            }
//        }


//
//        Quickstart quickStart = new Quickstart();
//        YouTube youTube = quickStart.getYouTubeService();
//        logger.debug(youTube.toString());



//
//        try {
//            Realm realm = this.getServletContext().getContainer().getRealm();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (shouldAuthenticate(req)) {
//            boolean authenticated = true;//req.authenticate(resp);
//            if (authenticated) {
//                if (req.getUserPrincipal() != null) {
//                    writer.println("user authenticated " + req.getUserPrincipal().getName());
//                }
//            } else {
//                return;
//            }
//        }



        writer.println("<p>some data</p>");
    }

    private boolean shouldAuthenticate(HttpServletRequest req) {
        //todo: apply some real condition
        return true;
    }
}