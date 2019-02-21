package com.vidmins.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.*;

/**
 * A class to represent a user.
 *
 * @author pwaite
 */
public class User implements java.io.Serializable {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String joinDate;
    private String dateOfBirth;
    private String organization;
    private String introduction;
    private String status;
    private List<Directory> directories;
    private int id;


    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param userName    the user name
     * @param password    the password
     * @param dateOfBirth the date of birth
     * @param id          the id
     */
    public User(String firstName, String lastName, String userName, String password, String dateOfBirth, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.id = id;
    }

    public User(ResultSet results) throws SQLException {
        this.setId(Integer.parseInt(results.getString("id")));
        this.setFirstName(results.getString("firstName"));
        this.setLastName(results.getString("lastName"));
        this.setUserName(results.getString("userName"));
        this.setEmail(results.getString("email"));
        this.setPassword(results.getString("enc_pass"));
        this.setJoinDate(results.getString("joinDate"));
        this.setDateOfBirth(results.getString("dateOfBirth"));
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
    public String getJoinDate() {
        return joinDate;
    }

    /**
     * Sets join date.
     *
     * @param joinDate the join date
     */
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        if (this.password == "") {
            return "*";
        } else {
            return "*****";
        }
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets user's age.
     *
     * @return the age
     */
    public int getAge() {
        return Period.between(LocalDate.parse(dateOfBirth), LocalDate.now()).getYears();
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
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}