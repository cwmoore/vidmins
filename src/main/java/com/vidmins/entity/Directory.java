package com.vidmins.entity;

import java.util.*;
import java.sql.*;

/**
 * The type Directory.
 */
public class Directory {
    private int id;
    private String name;
    private String description;
    private List<Integer> videoIds;

    /**
     * Instantiates a new Directory.
     */
    public Directory() {
    }

    /**
     * Instantiates a new Directory.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param videoIds    the video ids
     */
    public Directory(int id, String name, String description, List<Integer> videoIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.videoIds = videoIds;
    }

    /**
     * Instantiates a new Directory.
     *
     * @param results the results
     */
    public Directory(ResultSet results)
            throws SQLException {
        this.id = results.getInt("id");
        this.name = results.getString("name");
        this.description = results.getString("description");
        // this.videoIds = results.getString("videoIds");
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets video ids.
     *
     * @return the video ids
     */
    public List<Integer> getVideoIds() {
        return videoIds;
    }

    /**
     * Sets video ids.
     *
     * @param videoIds the video ids
     */
    public void setVideoIds(List<Integer> videoIds) {
        this.videoIds = videoIds;
    }
}
