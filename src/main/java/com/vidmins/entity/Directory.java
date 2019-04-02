package com.vidmins.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

/**
 * The type Directory.
 */
@Entity(name = "Directory")
@Table(name = "directory")
public class Directory implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            /*fetch = FetchType.EAGER,*/
            mappedBy = "directory")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Video> videos;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "directory")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Subscription> subscriptions;

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
     * @param user        the user
     */
    public Directory(int id, String name, String description, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
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
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets subscriptions.
     *
     * @return the subscriptions
     */
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    /**
     * Sets subscriptions.
     *
     * @param subscriptions the subscriptions
     */
    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
