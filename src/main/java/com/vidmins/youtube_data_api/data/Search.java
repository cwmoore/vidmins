/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vidmins.youtube_data_api.data;
//package com.google.api.services.samples.youtube.cmdline.data;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.model.*;
import com.vidmins.entity.YouTubeVideo;
import com.vidmins.persistence.GenericDao;
import com.vidmins.youtube_data_api.data.Auth;
import com.google.api.services.youtube.YouTube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Print a list of videos matching a search term.
 *
 * @author Jeremy Walker
 * @author cwmoore
 */
public class Search {

    /**
     * Define a global variable that identifies the name of a file that
     * contains the developer's API key.
     */
    private static final String PROPERTIES_FILENAME = "youtube.properties";

    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;

    /**
     * Initialize a YouTube object to search for videos on YouTube. Then
     * display the name and thumbnail image of each video in the result set.
     *
     * @param youTubeId the YouTube ID
     * @return the youTubeVideo
     */
    public YouTubeVideo doSearch(String youTubeId) {
        // Read the developer key from the properties file.
        Properties properties = new Properties();
        try {
            InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }

        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // short circuit API search if local entry found
//            GenericDao<YouTubeVideo> youTubeVideoDao = new GenericDao<>(YouTubeVideo.class);
//            List<YouTubeVideo> youTubeVideos = youTubeVideoDao.findByPropertyEqual("youTubeId", youTubeId);
//            if (youTubeVideos.size() > 0) {
//                YouTubeVideo youTubeVideo = youTubeVideos.get(0);
//                return youTubeVideo;
//            }

            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            // Set your developer key from the {{ Google Cloud Console }} for
            // non-authenticated requests. See:
            // {{ https://cloud.google.com/console }}
            String apiKey = properties.getProperty("youtube.apikey");
            System.out.println("youtube.apikey: " + apiKey);
            search.setKey(apiKey);

            search.setQ(youTubeId);

            // Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            //search.setFields("items(id/kind,id/videoId,id/playlistId,snippet/title,snippet/thumbnails/default/url)");
            //search.setFields("items(id,snippet)");
            search.setPart("id,snippet");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            // search.setOauthToken(); // TODO ?
            // search.setVideoEmbeddable(); // TODO ?

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                //prettyPrint(searchResultList.iterator(), youTubeId);
                if (searchResultList.iterator().hasNext()) {
                    YouTubeVideo youTubeVideo = getResultVideo(searchResultList.iterator().next());
                    System.out.println("Found youtubevideo: " + youTubeVideo.toString());
                    return youTubeVideo;
                } else {
                    System.out.println("Could not find youtubevideo: iterator empty");
                }
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return null;
    }

    /**
     * Prints out all results in the Iterator. For each result, print the
     * title, video ID, and thumbnail.
     *
     * @param iteratorSearchResults Iterator of SearchResults to print
     *
     * @param query Search query (String)
     */
    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                SearchResultSnippet snippet = singleVideo.getSnippet();
                ThumbnailDetails thumbNailDetails = snippet.getThumbnails();
                Thumbnail thumbnail = thumbNailDetails.getDefault();

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }

    /**
     * Gets YouTubeVideo from search result.
     *
     * @param singleVideo a search result
     * @return the youtube video
     */
    private static YouTubeVideo getResultVideo(SearchResult singleVideo) {

        ResourceId rId = singleVideo.getId();

        Set<String> keys = singleVideo.keySet();

        for (String key : keys) {
            System.out.println(key + " : " + singleVideo.get(key));
        }

        keys = singleVideo.getSnippet().keySet();

        for (String key : keys) {
            System.out.println(key + " : " + singleVideo.getSnippet().get(key));
        }

        if (rId.getKind().equals("youtube#video")) {

            YouTubeVideo youTubeVideo = new YouTubeVideo(
                    singleVideo.getId().getVideoId()
                    , singleVideo.getSnippet().getTitle()
            );

            return youTubeVideo;
        }
        return null;

// TODO another version
//            Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
//            thumbnail.getUrl();
//            singleVideo.getSnippet().getPublishedAt();
//            singleVideo.getSnippet().getChannelId();
//            singleVideo.getSnippet().getDescription();

    }
}
