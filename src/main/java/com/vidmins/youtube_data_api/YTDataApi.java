package com.vidmins.youtube_data_api;

import java.io.IOException;
import java.net.URL;

public class YTDataApi {

    // https://developers.google.com/youtube/v3/docs/captions
    // get captions from some videos
    private String authToken;

    public YTDataApi() {
        super();
        authToken = "";
    }

    public String findVideoData(String youTubeId) {
        return "";
    }

    /**
     * Extract the YouTube ID from the youtube URL
     */
    public static String extractYouTubeId(String youTubeUrl) throws IOException {
        URL url = new URL(youTubeUrl);
        String[] params = url.getQuery().split("&");
        for (String paramPair : params) {
            if (paramPair.substring(0, 2).equals("v=")) {
                return paramPair.substring(2);
            }
        }
        throw new IOException("Couldn't extract YouTube ID from URL: " + youTubeUrl);
    }
}
