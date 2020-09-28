package com.vidmins.entity;

/**
 * The type Input error.
 *
 * @author cwmoore
 */
public class InputError implements java.io.Serializable {
    private int timestamp;
    private int userId;
    private String message;
    private String url;

    /**
     * Instantiates a new Input error.
     */
    public InputError() {
    }

    /**
     * Instantiates a new Input error.
     *
     * @param timestamp the timestamp
     * @param userId    the user id
     * @param message   the message
     * @param url       the url
     */
    public InputError(int timestamp, int userId, String message, String url) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.message = message;
        this.url = url;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
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
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
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
     * Outputs object data as string
     * @return the field values of this object
     */
    @Override
    public String toString() {
        return "InputError{" +
                "timestamp=" + timestamp +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
