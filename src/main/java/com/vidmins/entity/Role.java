package com.vidmins.entity;

import com.vidmins.util.TimestampAttributeConverter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The user's Role.
 *
 * @author pwaite
 * @author cwmoore
 */
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String role;

    @CreationTimestamp
    @Convert(converter = TimestampAttributeConverter.class)
    @EqualsAndHashCode.Exclude
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name="userName", referencedColumnName = "userName", nullable = false) // referenceColumnName if not primary key
    private User user;

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
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
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
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets date created.
     *
     * @return the date created
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets date created.
     *
     * @param dateCreated the date created
     */
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}