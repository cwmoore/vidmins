package com.vidmins.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The type Subscription.
 */
@Entity(name = "Subscription")
@Table(name = "subscription")
public class Subscription implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String name;
    private String description;

    private LocalDateTime start;
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "subscriberId", nullable = false)
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "directoryId")
    private Directory directory;

    @ManyToOne
    @JoinColumn(name = "contractId", nullable = false)
    private Contract contract;

    /**
     * Instantiates a new Subscription.
     */
    public Subscription() {
    }

    /**
     * Instantiates a new Subscription.
     *
     * @param name        the name
     * @param description the description
     * @param start       the start
     * @param end         the end
     * @param subscriber        the subscriber
     * @param directory   the directory
     * @param contract    the contract
     */
    public Subscription(String name, String description, LocalDateTime start, LocalDateTime end, User subscriber, Directory directory, Contract contract) {
        this();
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
        this.subscriber = subscriber;
        this.directory = directory;
        this.contract = contract;
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
     * Gets start.
     *
     * @return the start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Sets start.
     *
     * @param start the start
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Gets end.
     *
     * @return the end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Sets end.
     *
     * @param end the end
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Sets subscriber.
     *
     * @param subscriber the subscriber
     */
    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * Gets subscriber.
     *
     * @return the subscriber
     */
    public User getSubscriber() {
        return subscriber;
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
     * Gets contract.
     *
     * @return the contract
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * Sets contract.
     *
     * @param contract the contract
     */
    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", subscriber=" + subscriber +
                ", directory=" + directory +
                ", contract=" + contract +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return id == that.id &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                start.equals(that.start) &&
                end.equals(that.end) &&
                subscriber.equals(that.subscriber) &&
                directory.equals(that.directory) &&
                contract.equals(that.contract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, start, end, subscriber, directory, contract);
    }
}
