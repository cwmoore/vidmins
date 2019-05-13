package com.vidmins.youtube_data_api;

import com.vidmins.entity.YouTubeVideo;
import com.vidmins.persistence.GenericDao;
import com.vidmins.youtube_data_api.data.Search;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
//import com.google.api.services.youtube.*;

public class YTDataApi {

    // https://developers.google.com/youtube/v3/docs/captions
    // get captions from some videos
    private String authToken;
    private final Logger logger;

    public YTDataApi() {
        super();
        logger = LogManager.getLogger(this.getClass());
        authToken = "";
    }

    public YouTubeVideo findVideoData(String youTubeId) {

        GenericDao<YouTubeVideo> youTubeVideoDao = new GenericDao<>(YouTubeVideo.class);

        List<YouTubeVideo> youTubeVideoList = youTubeVideoDao.findByPropertyEqual("youTubeId", youTubeId);

        YouTubeVideo youTubeVideo;
        if (youTubeVideoList.size() > 0) {
            youTubeVideo = youTubeVideoList.get(0);
        } else {
            Search search = new Search();
            youTubeVideo = search.doSearch(youTubeId);
            try {
                int insertId = youTubeVideoDao.insert(youTubeVideo);
                youTubeVideo = youTubeVideoDao.getById(insertId);
            } catch (Exception e) {
                logger.debug("Inserting youTubeVideo", e);
                youTubeVideo = null;
            }
        }

        return youTubeVideo;
    }

    /**
     * Extract the YouTube ID from the youtube URL
     */
    public String extractYouTubeId(String youTubeUrl) throws IOException {
        String youTubeId = null;
        try {
            URL url = new URL(youTubeUrl);

            logger.debug("getPath", url.getPath());


            if (url.getHost().equals("youtu.be")) {
                youTubeId = url.getPath();
                if (youTubeId.substring(0, 1).equals("/")) {
                    youTubeId = youTubeId.substring(1);
                }
            } else if (url.getHost().contains("youtube")) {
                String[] params = url.getQuery().split("&");
                for (String paramPair : params) {
                    if (paramPair.substring(0, 2).equals("v=")) {
                        youTubeId = paramPair.substring(2);
                    }
                }
            }
        } catch (MalformedURLException mue) {
            youTubeId = youTubeUrl;
            logger.debug(mue.toString() + " From: " + youTubeId);
        }

        if (youTubeId != null) {
            return youTubeId;
        }
        throw new IOException("Couldn't extract YouTube ID from URL: " + youTubeUrl);
    }
}
