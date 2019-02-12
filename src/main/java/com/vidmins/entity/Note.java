package com.vidmins.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Note.
 */
public class Note implements java.io.Serializable {
    private int id;
    private String label;
    private String text;
    private int start;
    private int end;
    private String createDatetime;
    private int userId;
    private int videoId;

    /**
     * Instantiates a new Note.
     */
    public Note() {
        this.id = -1;
        this.label = "";
        this.text = "";
        this.start = -1;
        this.end = -1;
        this.createDatetime = "2000-01-01 00:00:00.000";
        this.userId = -1;
        this.videoId = -1;
    }

    /**
     * Instantiates a new Note.
     *
     * @param id             the id
     * @param label          the label
     * @param text           the text
     * @param start          the start
     * @param end            the end
     * @param createDatetime the create datetime
     * @param userId         the user id
     * @param videoId        the video id
     */
    public Note(int id, String label, String text, int start, int end, String createDatetime, int userId, int videoId) {
        this();
        this.id = id;
        this.label = label;
        this.text = text;
        this.start = start;
        this.end = end;
        this.createDatetime = createDatetime;
        this.userId = userId;
        this.videoId = videoId;
    }

    /**
     * Instantiates a new Note.
     *
     * @param label          the label
     * @param text           the text
     * @param start          the start
     * @param userId         the user id
     * @param videoId        the video id
     */
    public Note(String label, String text, int start, int userId, int videoId) {
        this();
        this.label = label;
        this.text = text;
        this.start = start;
        this.end = end;
        this.userId = userId;
        this.videoId = videoId;
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
     * Gets label.
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets label.
     *
     * @param label the label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets start.
     *
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets start.
     *
     * @param start the start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Gets end.
     *
     * @return the end
     */
    public int getEnd() {
        return end;
    }

    /**
     * Sets end.
     *
     * @param end the end
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * Gets create datetime.
     *
     * @return the create datetime
     */
    public String getCreateDatetime() {
        return createDatetime;
    }

    /**
     * Sets create datetime.
     *
     * @param createDatetime the create datetime
     */
    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets video id.
     *
     * @return the video id
     */
    public int getVideoId() {
        return videoId;
    }

    /**
     * Sets video id.
     *
     * @param videoId the video id
     */
    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", text='" + text + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", createDatetime='" + createDatetime + '\'' +
                ", userId=" + userId +
                ", videoId=" + videoId +
                '}';
    }
}
