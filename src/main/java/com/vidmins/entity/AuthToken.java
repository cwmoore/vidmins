//package com.vidmins.entity;
//
//import com.vidmins.persistence.*;
//import com.vidmins.util.TimestampAttributeConverter;
//import lombok.EqualsAndHashCode;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * The type Auth token.
// */
//@Entity(name = "AuthToken")
//@Table(name = "auth_token")
//public class AuthToken implements java.io.Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User newUser;
//
//    @Column(name = "user_hash")
//    private String userHash;
//
//    @Column(name = "token")
//    private String verificationToken;
//
//    @CreationTimestamp
//    @Convert(converter = TimestampAttributeConverter.class)
//    @EqualsAndHashCode.Exclude
//    private LocalDateTime expiration;
//
//    private int status;
//
//
//    /**
//     * Instantiates a new Auth token.
//     */
//    public AuthToken() {}
//
//    /**
//     * Instantiates a new Auth token.
//     *
//     * @param newUser           the new user
//     * @param verificationToken the verification token
//     */
//    public AuthToken(User newUser, String verificationToken) {
//        this();
//        this.newUser = newUser;
//        this.userHash = Integer.toString(newUser.getId()); // TODO make this a CSPRN
//        this.verificationToken = verificationToken;
//    }
//
//    /**
//     * Instantiates a new Auth token.
//     *
//     * @param newUser           the new user
//     * @param verificationToken the verification token
//     * @param expiration        the expiration
//     */
//    public AuthToken(User newUser, String verificationToken, LocalDateTime expiration) {
//        this();
//        this.newUser = newUser;
//        this.userHash = Integer.toString(newUser.getId());
//        this.verificationToken = verificationToken;
//        this.expiration = expiration;
//    }
//
//    /**
//     * Generate auth token string.
//     *
//     * @param newUser the new user
//     * @return the string
//     */
//    public static String generateAuthToken(User newUser) {
//        // secure random
//        // store in auth_token table
//        return "aktualgoblldgook";
//    }
//
//    /**
//     * Verify auth token boolean.
//     *
//     * @param newUser           the new user
//     * @param verificationToken the verification token
//     * @return the boolean
//     */
//    public static boolean verifyAuthToken(User newUser, String verificationToken) {
//        // secure random
//        // store in auth_token table
//        GenericDao<AuthToken> authTokenDao = new GenericDao<>(AuthToken.class);
//        Map<String, Object> authMap = new HashMap<>();
//        authMap.put("user_id", newUser.getId());
//        authMap.put("token", verificationToken);
//        List<AuthToken> authTokens = authTokenDao.findByPropertyEqual(authMap);
//
//        return authTokens.size() == 1;
//    }
//
//    /**
//     * Gets id.
//     *
//     * @return the id
//     */
//    public int getId() {
//        return id;
//    }
//
//    /**
//     * Sets id.
//     *
//     * @param id the id
//     */
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    /**
//     * Gets new user.
//     *
//     * @return the new user
//     */
//    public User getNewUser() {
//        return newUser;
//    }
//
//    /**
//     * Sets new user.
//     *
//     * @param newUser the new user
//     */
//    public void setNewUser(User newUser) {
//        this.newUser = newUser;
//    }
//
//    /**
//     * Gets verification token.
//     *
//     * @return the verification token
//     */
//    public String getVerificationToken() {
//        return verificationToken;
//    }
//
//    /**
//     * Sets verification token.
//     *
//     * @param verificationToken the verification token
//     */
//    public void setVerificationToken(String verificationToken) {
//        this.verificationToken = verificationToken;
//    }
//
//    /**
//     * Gets expiration.
//     *
//     * @return the expiration
//     */
//    public LocalDateTime getExpiration() {
//        return expiration;
//    }
//
//    /**
//     * Sets expiration.
//     *
//     * @param expiration the expiration
//     */
//    public void setExpiration(LocalDateTime expiration) {
//        this.expiration = expiration;
//    }
//
//    /**
//     * Check expiration against current time.
//     *
//     * @return true if expired, false if still valid
//     */
//    public boolean isExpired() {
//        return ( this.getExpiration().compareTo(LocalDateTime.now()) > 0 );
//    }
//
//
//    /**
//     * Gets user hash.
//     *
//     * @return the user hash
//     */
//    public String getUserHash() {
//        return userHash;
//    }
//
//    /**
//     * Sets user hash.
//     *
//     * @param userHash the user hash
//     */
//    public void setUserHash(String userHash) {
//        this.userHash = userHash;
//    }
//
//    /**
//     * Gets status.
//     *
//     * @return the status
//     */
//    public int getStatus() {
//        return status;
//    }
//
//    /**
//     * Sets status.
//     *
//     * @param status the status
//     */
//    public void setStatus(int status) {
//        this.status = status;
//    }
//}
