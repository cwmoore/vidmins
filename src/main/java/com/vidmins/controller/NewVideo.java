package com.vidmins.controller;

import com.vidmins.entity.Directory;
import com.vidmins.entity.Video;
import com.vidmins.entity.YouTubeVideo;
import com.vidmins.persistence.GenericDao;
import com.vidmins.youtube_data_api.YTDataApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
    private GenericDao<Video> videoDao;
    private GenericDao<YouTubeVideo> ytVideoDao;

    /**
     * Initialize variables
     */
    public void init() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting servlet");
        videoDao = new GenericDao<>(Video.class);
        ytVideoDao = new GenericDao<>(YouTubeVideo.class);
    }

    /**
     * Handle a GET request
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException indicates a servlet problem
     * @throws IOException      indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // if user logged in
        // if new video parameter is set
        // if video parameter represents a viewable video
        // add the new video to `video` and `user_videos`
        // return to watcher to load new video

        // TODO deploy https://youtu.be/csT0t8ka8sM?t=292
        // TODO https://github.com/MadJavaEntSpring2019/home/blob/master/Module2/Week6/AWS/AWSSetupPart6.md

        request.setAttribute("video", addNewVideo(request));

//        String url = "/loadClient";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//        dispatcher.forward(request, response);

        String url = "loadClient";
        response.sendRedirect(url);

    }

    /**
     * Add the video to the database, linked with the current user.
     *
     * @param request
     * @return
     */
    private Video addNewVideo(HttpServletRequest request) {
        // user logged in?
        Video video = null;

        String youTubeUrl = request.getParameter("youtube_url");

        // sanity check provided parameters
        String title = "test title";
        LocalDateTime currentLocalDateTime = LocalDateTime.now();

        try {
            YTDataApi ytDataApi = new YTDataApi();
            String youTubeId = ytDataApi.extractYouTubeId(youTubeUrl);

            // check for an existing row with this YouTubeId
            List ytVideos = ytVideoDao.findByPropertyEqual("youTubeId", youTubeId);

            YouTubeVideo youTubeVideo;
            if (ytVideos.size() == 0) {
                youTubeVideo = new YouTubeVideo(youTubeId, title);

                //youTubeVideo.retrieveInfo();
                // save new video link
                ytVideoDao.insert(youTubeVideo);
            } else {
                // use the existing entry
                youTubeVideo = (YouTubeVideo) ytVideos.get(0);
            }

            video = new Video(youTubeVideo
                    , title
                    , currentLocalDateTime
                    , (Directory) request.getSession().getAttribute("defaultDirectory")
            );
            videoDao.insert(video);

        } catch (IOException iox) {
            iox.printStackTrace();
        }

        return video;
    }
}