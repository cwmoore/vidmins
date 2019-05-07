package com.vidmins.youtube_data_api;

import com.vidmins.entity.YouTubeVideo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
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
        YouTubeVideo ytVideo = new YouTubeVideo(youTubeId);
        return ytVideo;
    }

    /**
     * Extract the YouTube ID from the youtube URL
     */
    public String extractYouTubeId(String youTubeUrl) throws IOException {
        String youTubeId = null;
        URL url = new URL(youTubeUrl);

        logger.debug("getPath", url.getPath());


        if (url.getHost().equals("youtu.be")) {
            youTubeId = url.getPath();
            if (youTubeId.substring(0, 1).equals("/")) {
                youTubeId = youTubeId.substring(1);
            }
        } else {
            String[] params = url.getQuery().split("&");
            for (String paramPair : params) {
                if (paramPair.substring(0, 2).equals("v=")) {
                    youTubeId = paramPair.substring(2);
                }
            }
        }

        if (youTubeId != null) {
            return youTubeId;
        }
        throw new IOException("Couldn't extract YouTube ID from URL: " + youTubeUrl);
    }
}
