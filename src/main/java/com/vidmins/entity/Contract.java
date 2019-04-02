package com.vidmins.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Contract.
 */
@Entity(name = "Contract")
@Table(name = "contract")
public class Contract implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String name;
    private String description;
    private double priceForTimePeriod;
    private int timePeriodHours;
    private boolean canShare = false;
    private boolean canCopy = false;
    private boolean canEdit = false;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private User author;

    @OneToMany(mappedBy = "contract")
    private List<Subscription> subscriptions = new ArrayList<>();

    /**
     * Instantiates a new Contract.
     */
    public Contract() {
    }

    /**
     * Instantiates a new Contract.
     *
     * @param name               the name
     * @param description        the description
     * @param priceForTimePeriod the price for time period
     * @param timePeriodHours    the time period hours
     * @param canShare           the can share
     * @param canCopy            the can copy
     * @param canEdit            the can edit
     * @param author             the author
     */
    public Contract(String name, String description, double priceForTimePeriod, int timePeriodHours
            , boolean canShare, boolean canCopy, boolean canEdit, User author) {
        this();
        this.name = name;
        this.description = description;
        this.priceForTimePeriod = priceForTimePeriod;
        this.timePeriodHours = timePeriodHours;
        this.canShare = canShare;
        this.canCopy = canCopy;
        this.canEdit = canEdit;
        this.author = author;
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
     * Gets price for time.
     *
     * @return the price for time
     */
    public double getPriceForTimePeriod() {
        return priceForTimePeriod;
    }

    /**
     * Sets price for time.
     *
     * @param priceForTimePeriod the price for time
     */
    public void setPriceForTimePeriod(double priceForTimePeriod) {
        this.priceForTimePeriod = priceForTimePeriod;
    }

    /**
     * Gets time period hours.
     *
     * @return the time period hours
     */
    public int getTimePeriodHours() {
        return timePeriodHours;
    }

    /**
     * Sets time period hours.
     *
     * @param timePeriodHours the time period hours
     */
    public void setTimePeriodHours(int timePeriodHours) {
        this.timePeriodHours = timePeriodHours;
    }

    /**
     * Is can share boolean.
     *
     * @return the boolean
     */
    public boolean isCanShare() {
        return canShare;
    }

    /**
     * Sets can share.
     *
     * @param canShare the can share
     */
    public void setCanShare(boolean canShare) {
        this.canShare = canShare;
    }

    /**
     * Is can copy boolean.
     *
     * @return the boolean
     */
    public boolean isCanCopy() {
        return canCopy;
    }

    /**
     * Sets can copy.
     *
     * @param canCopy the can copy
     */
    public void setCanCopy(boolean canCopy) {
        this.canCopy = canCopy;
    }

    /**
     * Is can edit boolean.
     *
     * @return the boolean
     */
    public boolean isCanEdit() {
        return canEdit;
    }

    /**
     * Sets can edit.
     *
     * @param canEdit the can edit
     */
    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    /**
     * Gets offering party.
     *
     * @return the offering party
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets offering party.
     *
     * @param author the offering party
     */
    public void setAuthor(User author) {
        this.author = author;
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
