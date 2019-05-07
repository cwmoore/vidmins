package com.vidmins.entity;

// TODO app needs access to youtube data to retrieve duration, title
// TODO Video needs to trigger request to YouTubeDataAPI to instantiate req'd YouTubeVideo


import com.vidmins.youtube_data_api.YTDataApi;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A class to represent a YouTube video.
 *
 * @author cwmoore
 */
@Entity(name = "YouTubeVideo")
@Table(name = "youTubeVideo")
public class YouTubeVideo implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String youTubeId;

    private String title;
    private int duration;

    private String metadata;
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "youTubeVideo")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Video> videos;

    /**
     * Instantiates a new YouTubeVideo.
     */
    public YouTubeVideo() {
        super();
        id = 0;
        youTubeId = "";
        title = "";
        duration = 0;
        videos = new ArrayList<>();
        metadata = "";
    }

    /**
     * Instantiates a YouTubeVideo from youTubeId.
     *
     * @param youTubeId the youTubeId
     */
    public YouTubeVideo(String youTubeId) {
        this();
        this.youTubeId = youTubeId;
    }

    /**
     * Instantiates a new YouTubeVideo really easily.
     *
     * @param title the title
     */
    public YouTubeVideo(String title, int duration) {
        this();
        this.title = title;
        this.duration = duration;
    }

    /**
     * Instantiates a new YouTubeVideo.
     *
     * @param youTubeId the you tube id
     * @param title     the title
     */
    public YouTubeVideo(String youTubeId, String title) {
        this();
        this.youTubeId = youTubeId;
        this.title = title;
    }

    /**
     * Instantiates a new YouTubeVideo.
     *
     * @param youTubeId the you tube id
     * @param title     the title
     * @param duration  the duration
     */
    public YouTubeVideo(String youTubeId, String title, int duration) {
        this();
        this.youTubeId = youTubeId;
        this.title = title;
        this.duration = duration;
    }

    /**
     * Retrieves and sets or updates video info from YouTube Data API
     */
    public void retrieveData() {
        YTDataApi ytDataApi = new YTDataApi();

        // get video information via YT Data API
        YouTubeVideo ytVideo = ytDataApi.findVideoData(this.youTubeId);

        // title, description, duration, license, owner, channel
        // build a youtube data api object
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets youTubeId.
     *
     * @return the youTubeId
     */
    public String getYouTubeId() {
        return youTubeId;
    }

    /**
     * Sets youTubeId.
     *
     * @param youTubeId the youTubeId
     */
    public void setYouTubeId(String youTubeId) {
        this.youTubeId = youTubeId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets metadata
     * @return the metadata
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * Sets metadata
     * @param metadata
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Get the update date.
     * @return the update date
     */
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    /**
     * set the update date.
     * @param updateDate the update date
     */
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * Gets videos.
     *
     * @return the videos
     */
    public List<Video> getVideos() {
        return videos;
    }

    /**
     * Sets videos.
     *
     * @param videos the videos
     */
    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    /**
     * To string java . lang . string.
     *
     * @return the java . lang . string
     */
    @java.lang.Override
    public java.lang.String toString() {
        return "YouTubeVideo{" +
                ", youTubeId='" + youTubeId + '\'' +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}