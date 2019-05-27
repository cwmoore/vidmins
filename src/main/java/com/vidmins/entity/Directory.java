package com.vidmins.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The type Directory.
 */
@Entity(name = "Directory")
@Table(name = "directory")
public class Directory extends HashIdAble implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String name;
    private String description;

    @Column
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column
    @UpdateTimestamp
    private LocalDateTime lastAccessDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            /*fetch = FetchType.EAGER,*/
            mappedBy = "directory")
    @Fetch(value = FetchMode.SUBSELECT)
    //@LazyCollection(LazyCollectionOption.FALSE)
    private List<Video> videos;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "directory")
    @Fetch(value = FetchMode.SUBSELECT)
    //@LazyCollection(LazyCollectionOption.FALSE)
    private List<Subscription> subscriptions;

    private static Logger logger;

    /**
     * Instantiates a new Directory.
     */
    public Directory() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("New Directory...");
    }

    /**
     * Instantiates a new Directory.
     *
     * @param name        the name
     * @param description the description
     * @param user        the user
     */
    public Directory(String name, String description, User user) {
        this();
        this.name = name;
        this.description = description;
        this.user = user;
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
        this();
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
     * Get create date.
     * @return the create date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Set create  date.
     * @param createDate the created date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }


    /**
     * Get last access date.
     * @return the last access date
     */
    public LocalDateTime getLastAccessDate() {
        return lastAccessDate;
    }

    /**
     * Set last access date.
     * @param lastAccessDate the last access date
     */
    public void setLastAccessDate(LocalDateTime lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
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
     * Add a Video
     * @param video the Video
     */
    public void addVideo(Video video) {
        this.videos.add(video);
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
