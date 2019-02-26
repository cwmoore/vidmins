package com.vidmins.entity;

import com.vidmins.entity.Directory;

import com.vidmins.util.LocalDateAttributeConverter;
import com.vidmins.util.TimestampAttributeConverter;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    private String youTubeId;
    private String title;
    private LocalDateTime addDate;
    private int duration;

    // adapted from: https://stackoverflow.com/a/29952572/6254147
    @ManyToOne
    @JoinColumn(name = "directoryId", nullable = false, insertable = false, updatable = false)
    private Directory directory;

    @OneToMany(mappedBy = "video")
    private List<Note> notes;

    /**
     * Instantiates a new Video.
     */
    public Video() {
    }

    /**
     * Instantiates a new Video.
     *
     * @param youTubeId the you tube id
     * @param title     the title
     * @param addDate   the add date
     * @param duration  the duration
     */
    public Video(String youTubeId, String title, LocalDateTime addDate, int duration, Directory directory) {
        this.youTubeId = youTubeId;
        this.title = title;
        this.addDate = addDate;
        this.duration = duration;
    }

    /**
     * Instantiates an existing Video.
     *
     * @param id        the id
     * @param youTubeId the you tube id
     * @param title     the title
     * @param addDate   the add date
     * @param duration  the duration
     */
    public Video(int id, String youTubeId, String title, LocalDateTime addDate, int duration, Directory directory) {
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
                ", youTubeId='" + youTubeId + '\'' +
                ", title='" + title + '\'' +
                ", addDate='" + addDate + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}