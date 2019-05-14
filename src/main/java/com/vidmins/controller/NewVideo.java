package com.vidmins.controller;

import com.vidmins.entity.Directory;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
import com.vidmins.entity.YouTubeVideo;
import com.vidmins.persistence.DaoHelper;
import com.vidmins.persistence.GenericDao;
import com.vidmins.youtube_data_api.YTDataApi;
import com.vidmins.youtube_data_api.data.Search;
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
    private DaoHelper dao;

    /**
     * Initialize variables
     */
    public void init() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting NewVideo servlet");

        dao = new DaoHelper();
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
        dao.loadHelpers(request);

        Video newVideo = addNewVideo(request);
        String url = "loadClient";

        if (newVideo != null) {
            request.getSession().setAttribute("currentVideo", newVideo);
            request.getSession().setAttribute("currentDirectory", newVideo.getDirectory());
            url += "?directoryId=" + newVideo.getDirectory().getId();
        } else {
            logger.debug("New Video was null");
        }



//        String url = "/loadClient";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//        dispatcher.forward(request, response);

        response.sendRedirect(url);

    }

    /**
     * Add the video to the database, linked with the current user.
     *
     * @param request the HttpServletRequest
     * @return the new Video
     */
    private Video addNewVideo(HttpServletRequest request) {
        // user logged in?
        Video video = null;

        String youTubeUrl = request.getParameter("youtube_url");

        // sanity check provided parameters
        String title = request.getParameter("title");
        LocalDateTime currentLocalDateTime = LocalDateTime.now();

        try {
            YTDataApi ytDataApi = new YTDataApi();
            String youTubeId = ytDataApi.extractYouTubeId(youTubeUrl);
            logger.debug("Input youTubeUrl: " + youTubeUrl);
            logger.debug("Input youTubeId: " + youTubeId);

            YouTubeVideo youTubeVideo = ytDataApi.findVideoData(youTubeId);
            if (youTubeVideo == null) {
                logger.debug("youtube id did not return a video");
                throw new Exception("Problem locating video " + youTubeId);
            } else {
                logger.debug("youTubeVideo: " + youTubeVideo.toString());
            }

            Directory currentDirectory = (Directory) request.getSession().getAttribute("currentDirectory");
            if (currentDirectory == null) {
                User user = (User) request.getSession().getAttribute("user");
                List<Directory> directories = user.getDirectories();
                if (directories.size() > 0) {
                    currentDirectory = directories.get(0);
                } else {
                    currentDirectory = createDefaultDirectory(user);
                }
            }

            video = new Video(
                    youTubeVideo
                    , title
                    , currentLocalDateTime
                    , currentDirectory
            );
            logger.debug("video: " + video.toString());
            int insertId = dao.video.insert(video);
            video = dao.video.getById(insertId);

        } catch (IOException iox) {
            logger.debug(iox);
        } catch (Exception e) {
            logger.debug(e);
        }

        return video;
    }

    /**
     * Creates a default directory for a new user account
     * @param user the user
     * @return the new directory
     */
    public Directory createDefaultDirectory(User user) {

        Directory defaultDirectory = new Directory(
                "First Directory"
                , "Directories organize sets of videos."
                , user
        );

        // TODO add beginner video for first time user);
        Video newUserVideo = new Video(dao.youTubeVideo.getById(1), "To Begin");
        defaultDirectory.addVideo(newUserVideo);

        // create the directory
        int dirInsertId = dao.directory.insert(defaultDirectory);

        // add the new directory to the user object
        user.addDirectory(dao.directory.getById(dirInsertId));

        // save
        dao.user.saveOrUpdate(user);

        return user.getDirectories().get(0);
    }
}