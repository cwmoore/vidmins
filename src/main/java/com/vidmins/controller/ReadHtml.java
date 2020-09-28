package com.vidmins.controller;

import com.vidmins.data.DataParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The type Read html.
 */
@WebServlet (
        name = "readHtml",
        urlPatterns = {"/read-html"}
)
public class ReadHtml extends HttpServlet {
    private String url = null;
    private Document doc = null;
    private Logger logger;
    private DataParser dataParser;
    private Map<String, String> videoLinks;
    private String videoRegex = "youtube\\.com/watch\\?v=|youtu.be/";

    public ReadHtml() {
        super();
    }

    /**
     * Instantiates a new Read html.
     *
     * @param url the url
     */
    public ReadHtml(String url) {
        this();
        this.url = url;
        loadFromUrl();
    }

    @Override
    public void init() throws ServletException {
        logger = LogManager.getLogger(this.getClass());
        this.dataParser = new DataParser();
        videoLinks = new TreeMap<>();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestUrl = request.getParameter("url");
        if (requestUrl == null) {
            this.url = "https://github.com/MadJavaEntSpring2019/home/tree/master/References";
        } else if (isUrl(requestUrl)) {
            this.url = requestUrl;
        }

        loadFromUrl();

        // do something with the jsoup document this.doc
        Elements links = doc.select("a[href*=youtu]");//.not("a[href^=/wiki/List], a[href^=/wiki/Portal], a[href^=https], a[href^=/wiki/Category]");

        for (Element l : links) {
            //logger.debug("maxResults: " + maxResults);
            //if (!l.text().matches("all")) { continue; }

            Attributes attr = l.attributes();
            if (attr.get("href").matches("youtu\\.?be")) {
                videoLinks.put(attr.get("href"), l.text());
            }
        }

        StringBuffer entries = new StringBuffer();
        for (Map.Entry<String, String> entry : videoLinks.entrySet()) {
            entries.append("\n");
            entries.append(entry.getKey());
            entries.append(" : ");
            entries.append(entry.getValue());
            entries.append("\n");
        }

        PrintWriter out = response.getWriter();
        out.print(entries);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public boolean isUrl(String url) {
        return url.matches("https?.+"); // TODO use a real url matching regex, and maybe a ping too
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
