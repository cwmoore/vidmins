package com.vidmins.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class to represent a video.
 *
 * @author cwmoore
 */
@Entity(name = "Video")
@Table(name = "video")
public class Video implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "youTubeVideoId", nullable = false)
    private YouTubeVideo youTubeVideo;

    private String title;
    private LocalDateTime addDate;
    private LocalDateTime lastAccessDate;
    private LocalDateTime lastViewedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "directoryId", nullable = false)
    private Directory directory;

    @OneToMany(cascade = CascadeType.ALL,
            /*fetch = FetchType.EAGER,*/
            mappedBy = "video")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Note> notes = new ArrayList<>();

    /**
     * Instantiates a new Video.
     */
    public Video() {
        super();
    }

    /**
     * Instantiates a new Video really easily.
     */
    public Video(String title) {
        this();
        this.title = title;
    }

    /**
     * Instantiates a new Video.
     *
     * @param youTubeVideo the you tube id
     * @param title     the title
     */
    public Video(YouTubeVideo youTubeVideo, String title) {
        this();
        this.youTubeVideo = youTubeVideo;
        this.title = title;
    }

    /**
     * Instantiates a new Video.
     *
     * @param youTubeVideo the you tube id
     * @param title     the title
     * @param addDate   the add date
     * @param directory the directory
     */
    public Video(YouTubeVideo youTubeVideo, String title, LocalDateTime addDate, Directory directory) {
        this();
        this.youTubeVideo = youTubeVideo;
        this.title = title;
        this.addDate = addDate;
        this.directory = directory;
    }

    /**
     * Instantiates an existing Video.
     *
     * @param id        the id
     * @param youTubeVideo the you tube id
     * @param title     the title
     * @param addDate   the add date
     */
    public Video(int id, YouTubeVideo youTubeVideo, String title, LocalDateTime addDate, Directory directory) {
        this();
        this.id = id;
        this.youTubeVideo = youTubeVideo;
        this.title = title;
        this.addDate = addDate;
        this.directory = directory;
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
     * Gets you tube video.
     *
     * @return the you tube video
     */
    public YouTubeVideo getYouTubeVideo() {
        return youTubeVideo;
    }

    /**
     * Sets you tube video.
     *
     * @param youTubeVideo the you tube video
     */
    public void setYouTubeVideo(YouTubeVideo youTubeVideo) {
        this.youTubeVideo = youTubeVideo;
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
    public LocalDateTime getAddDate() {
        return addDate;
    }

    /**
     * Sets add date.
     *
     * @param addDate the add date
     */
    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    /**
     * Gets last viewed date.
     *
     * @return the last viewed date
     */
    public LocalDateTime getLastViewedDate() {
        return lastViewedDate;
    }

    /**
     * Sets last viewed date.
     *
     * @param lastViewedDate the last viewed date
     */
    public void setLastViewedDate(LocalDateTime lastViewedDate) {
        this.lastViewedDate = lastViewedDate;
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
     * Gets directory.
     *
     * @return the directory
     */
    public Directory getDirectory() {
        return directory;
    }

    /**
     * Sets directory.
     *
     * @param directory the directory
     */
    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    /**
     * Gets notes.
     *
     * @return the notes
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * Sets notes.
     *
     * @param notes the notes
     */
    public void setNotes(List<Note> notes) {
        this.notes = notes;
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
                ", youTubeVideo='" + youTubeVideo + '\'' +
                ", title='" + title + '\'' +
                ", addDate='" + addDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return id == video.id &&
                Objects.equals(youTubeVideo, video.youTubeVideo) &&
                Objects.equals(title, video.title) &&
                Objects.equals(addDate, video.addDate) &&
                Objects.equals(directory, video.directory) &&
                Objects.equals(notes, video.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, youTubeVideo, title, addDate, directory, notes);
    }
}