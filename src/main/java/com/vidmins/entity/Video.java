package com.vidmins.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * A class to represent a video.
 *
 * @author cwmoore
 */
public class Video implements java.io.Serializable {
    private int id;
    private String youTubeId;
    private String title;
    private String addDate;
    private int duration;

    /**
     * Instantiates a new Video.
     */
    public Video() {
    }

    /**
     * Instantiates a new Video.
     *
     * @param id        the id
     * @param youTubeId the you tube id
     * @param title     the title
     * @param addDate   the add date
     * @param duration  the duration
     */
    public Video(int id, String youTubeId, String title, String addDate, int duration) {
        this.id = id;
        this.youTubeId = youTubeId;
        this.title = title;
        this.addDate = addDate;
        this.duration = duration;
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
     * Gets you tube id.
     *
     * @return the you tube id
     */
    public String getYouTubeId() {
        return youTubeId;
    }

    /**
     * Sets you tube id.
     *
     * @param youTubeId the you tube id
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
     * Gets add date.
     *
     * @return the add date
     */
    public String getAddDate() {
        return addDate;
    }

    /**
     * Sets add date.
     *
     * @param addDate the add date
     */
    public void setAddDate(String addDate) {
        this.addDate = addDate;
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
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * To string java . lang . string.
     *
     * @return the java . lang . string
     */
    @java.lang.Override
    public java.lang.String toString() {
        return "Video{" +
                "id=" + id +
                ", youTubeId='" + youTubeId + '\'' +
                ", title='" + title + '\'' +
                ", addDate='" + addDate + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}