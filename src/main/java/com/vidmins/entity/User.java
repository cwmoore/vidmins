package com.vidmins.entity;

import com.vidmins.auth.Auth;
import com.vidmins.util.LocalDateAttributeConverter;
import com.vidmins.util.TimestampAttributeConverter;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.*;
import java.util.*;

/**
 * A class to represent a user.
 *
 * @author pwaite
 * @author cwmoore
 */
@Entity(name="User")
@Table(name = "user")
public class User implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String userName;

    @Column(name = "enc_pass")
    private String password; // TODO switch 'password' to 'enc_pass'

    @CreationTimestamp
    @Convert(converter = TimestampAttributeConverter.class)
    @EqualsAndHashCode.Exclude
    private LocalDateTime joinDate;

    @CreationTimestamp
    @Convert(converter = LocalDateAttributeConverter.class)
    @EqualsAndHashCode.Exclude
    private LocalDate dateOfBirth;

    private String organization;
    private String introduction;
    private String status;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER)
    private List<Directory> directories;

    @OneToMany(mappedBy = "newUser")
    private List<AuthToken> authTokens;


    /**
     * Instantiates a new User.
     */
    public User() {
        super();
        id = 0;
        firstName = "";
        lastName = "";
        email = "";
        userName = "";
        password = "";
        joinDate = LocalDateTime.now();
        dateOfBirth = LocalDate.now();
        organization = "";
        introduction = "";
        status = "";
        authTokens = null;
    }


    /**
     * Instantiates a new User really easily.
     *
     * @param userName the user name
     */
    public User(String userName) {
        this();
        this.userName = userName;
    }

    /**
     * Instantiates a new User.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param userName    the user name
     * @param dateOfBirth the date of birth
     */
    public User(String firstName, String lastName, String userName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * Instantiates a new User.
     *
     * @param firstName    the first name
     * @param lastName     the last name
     * @param userName     the user name
     * @param organization the organization
     * @param introduction the introduction
     * @param dateOfBirth  the date of birth
     */
    public User(String firstName, String lastName, String userName, String organization, String introduction, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.organization = organization;
        this.introduction = introduction;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets email
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Gets join date
     *
     * @return the join date
     */
    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    /**
     * Sets join date.
     *
     * @param joinDate the join date
     */
    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
//        Auth auth = new Auth();
//        this.password = auth.encryptPassword(password);
        this.password = password;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets user's age.
     *
     * @return the age
     */
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Gets organization.
     *
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Sets organization.
     *
     * @param organization the organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * Gets introduction.
     *
     * @return the introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * Sets introduction.
     *
     * @param introduction the introduction
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets directories.
     *
     * @return the directories
     */
    public List<Directory> getDirectories() {
        return directories;
    }

    /**
     * Sets the directories.
     *
     * @param directories the directories
     */
    public void setDirectories(List<Directory> directories) {
        this.directories = directories;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", joinDate=" + joinDate +
                ", dateOfBirth=" + dateOfBirth +
                ", organization='" + organization + '\'' +
                ", introduction='" + introduction + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                email.equals(user.email) &&
                userName.equals(user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(joinDate, user.joinDate) &&
                dateOfBirth.equals(user.dateOfBirth) &&
                organization.equals(user.organization) &&
                introduction.equals(user.introduction) &&
                status.equals(user.status) &&
                Objects.equals(authTokens, user.authTokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, userName, password, joinDate, dateOfBirth, organization, introduction, status, authTokens);
    }
}