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

    @UpdateTimestamp
    @Convert(converter = TimestampAttributeConverter.class)
    @EqualsAndHashCode.Exclude private LocalDateTime updateDate;

    @CreationTimestamp
    @Convert(converter = TimestampAttributeConverter.class)
    @EqualsAndHashCode.Exclude private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name="userName", referencedColumnName = "userName", nullable = false)    // referenceColumnName if not primary key
    private User user;

}