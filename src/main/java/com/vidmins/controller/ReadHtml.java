package com.vidmins.controller;

import java.io.IOException;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * The type Read html.
 */
public class ReadHtml {
    private String url = null;
    private Document doc = null;
    private Logger logger;

    private String videoRegex = "youtube\\.com/watch\\?v=|youtu.be/";


    /**
     * Instantiates a new Read html.
     *
     * @param url the url
     */
    public ReadHtml(String url) {
        logger = LogManager.getLogger(this.getClass());

        this.url = url;
        loadFromUrl();
    }


    /**
     * Find you tube links map.
     *
     * @return the map
     */
    public Map<String, String> findYouTubeLinks() {

        Map<String, String> youTubeLinks = new TreeMap<>();

        // find links to youtube.com, youtu.be, etc.
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();

            if (linkHref.matches(videoRegex)) {
                youTubeLinks.put(linkHref, linkText);
            }
        }

        return youTubeLinks;
    }


    /**
     * Load the Document object from a URL
     */
    private void loadFromUrl() {

        try {
            // get html from URL
            doc = Jsoup.connect(url).get();

        } catch (IOException ioException) {
            logger.error("Jsoup.connect(\"" + url + "\").get() threw exception " + ioException);
        }
    }


    /**
     * load the Document object from an HTML string
     * @param html
     */
    private void loadFromString(String html) {

        doc = Jsoup.parse(html);
    }


    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }


    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }


    /**
     * Gets doc.
     *
     * @return the doc
     */
    public Document getDoc() {
        return doc;
    }


    /**
     * Sets doc.
     *
     * @param doc the doc
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }


    /**
     * Gets video regex.
     *
     * @return the video regex
     */
    public String getVideoRegex() {
        return videoRegex;
    }


    /**
     * Sets video regex.
     *
     * @param videoRegex the video regex
     */
    public void setVideoRegex(String videoRegex) {
        this.videoRegex = videoRegex;
    }
}
