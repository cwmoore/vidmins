package com.vidmins.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.sql.*;

/**
 * The type Directory.
 */
@Entity
@Table(name = "directory")
public class Directory implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String name;
    private String description;

    private Map<Integer, Video> videos;

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
     */
    public Directory(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
    public Set<Integer> getVideoIds() {
        return videos.keySet();
    }

    /**
     * Gets videos.
     *
     * @return the videos
     */
    public Map<Integer, Video> getVideos() {
        return videos;
    }

    /**
     * Sets videos.
     *
     * @param videos the videos
     */
    public void setVideos(Map<Integer, Video> videos) {
        this.videos = videos;
    }
}
