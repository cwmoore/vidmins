package com.vidmins.entity;

import com.vidmins.util.TimestampAttributeConverter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The user's Role.
 *
 * @author pwaite
 * @author cwmoore
 */
@Data
@Entity(name = "Role")
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userName", referencedColumnName = "userName", nullable = false)
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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return id == role1.id &&
                Objects.equals(role, role1.role) &&
                Objects.equals(user, role1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, user);
    }
}