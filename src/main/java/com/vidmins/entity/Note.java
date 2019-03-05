package com.vidmins.entity;

import com.vidmins.util.TimestampAttributeConverter;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The type Note.
 */
@Entity(name = "Note")
@Table(name = "note")
public class Note implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    
    private String label;
    private String text;
    
    private int start;

    @CreationTimestamp
    @Convert(converter = TimestampAttributeConverter.class)
    @EqualsAndHashCode.Exclude
    private LocalDateTime createDatetime;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "videoId", nullable = false)
    private Video video;

    /**
     * Instantiates a new Note.
     */
    public Note() {

    }

    /**
     * Instantiates a new Note.
     *
     * @param id             the id
     * @param label          the label
     * @param text           the text
     * @param start          the start
     * @param createDatetime the create datetime
     * @param video          the video
     */
    public Note(int id, String label, String text, int start, LocalDateTime createDatetime
            , User author
            , Video video) {
        this();
        this.id = id;
        this.label = label;
        this.text = text;
        this.start = start;
        this.createDatetime = createDatetime;
        this.author = author;
        this.video = video;
    }

    /**
     * Instantiates a new Note.
     *
     * @param label          the label
     * @param text           the text
     * @param start          the start
     * @param createDatetime the create datetime
     * @param video          the video
     */
    public Note(String label, String text, int start, LocalDateTime createDatetime
            , User author
            , Video video) {
        this();
        this.label = label;
        this.text = text;
        this.start = start;
        this.createDatetime = createDatetime;
        this.author = author;
        this.video = video;
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
     * Gets create datetime.
     *
     * @return the create datetime
     */
    public LocalDateTime getCreateDatetime() {
        return createDatetime;
    }

    /**
     * Sets create datetime.
     *
     * @param createDatetime the create datetime
     */
    public void setCreateDatetime(LocalDateTime createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * Gets video.
     *
     * @return the video
     */
    public Video getVideo() {
        return video;
    }

    /**
     * Sets video.
     *
     * @param video the video
     */
    public void setVideo(Video video) {
        this.video = video;
    }


    /**
     * Gets author.
     *
     * @return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", text='" + text + '\'' +
                ", start=" + start +
                ", createDatetime='" + createDatetime + '\'' +
              //  ", author=" + author +
                ", video=" + video +
                '}';
    }
}
