package com.vidmins.controller;

import com.vidmins.auth.Auth;
//import com.vidmins.entity.AuthToken;
import com.vidmins.entity.Role;
import com.vidmins.entity.User;
import com.vidmins.persistence.DaoHelper;
import com.vidmins.persistence.GenericDao;

import org.apache.catalina.realm.SecretKeyCredentialHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet template
 * @author cwmoore
 */

@WebServlet(
        name = "newUser",
        urlPatterns = {"/new-user"}
)
public class NewUser extends HttpServlet {
    private Logger logger;
    private DaoHelper dao;

    /**
     * Initialize variables
     */
    public void init() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting servlet");

        dao = new DaoHelper();
    }

    /**
     * Handle a verification link of the form:
     *      https://vidmins.com/new-user?user=272x42asdf&token=yb478b23c04b
     *      Match link parameters to stored hashes in vidmins.auth_token
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao.loadHelpers(request);
        String userHash = request.getParameter("user");
        String verificationToken = request.getParameter("token");
//
//        Map<String, Object> userProperties = new HashMap<>();
//        userProperties.put("userHash", userHash);
//        userProperties.put("token", verificationToken);

//        GenericDao<AuthToken> authTokenDao = new GenericDao<>(AuthToken.class);
//        List<AuthToken> authTokens = authTokenDao.findByPropertyEqual(userProperties);
//
//        if (authTokens.size() == 0) {
//            // not a match
//            // bad, log as possible hack attempt
//        } else if (authTokens.size() == 1) {
//            // exactly one match
//            if (authTokens.get(0).getExpiration().compareTo(LocalDateTime.now()) > 0) {
//                // still good, redirect to login
//            } else {
//                // expired, offer to generate new pair and resend email
//            }
//        } else {
//
//            // this should not happen in a million years
//            // with good hash function and length
//            // bad, log as possible hack attempt
//
//            for (AuthToken authToken : authTokens) {
//                // may want delete or somehow mark as exposed
//            }
//        }

        Map<String, String> errors = new HashMap<>();
    }

    /**
     * Handle a POST request
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao.loadHelpers(request);

        Map<String, String> errors = new HashMap<>();

        // extract field data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String organization = request.getParameter("organization");
        String introduction = request.getParameter("introduction");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password0 = request.getParameter("password0");
        String password1 = request.getParameter("password1");
        String dateOfBirth = request.getParameter("dateOfBirth");

        for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
            logger.debug(param.getKey() + ":" + String.join(", ", param.getValue()));
        }

        // TODO use Hibernate Validator + catch ConstraintViolationException
        // verify complete and correct
        if (firstName != null
                && lastName != null
                && username != null
                && email != null
                && password0 != null
                && password1 != null
        ) {
            if (password0.length() < 8) {
                // password too short
                errors.put("password", "Password is too short, use 8 or more characters.");
            }
            if (!password0.equals(password1)) {
                // password mismatch
                errors.put("password", "Password mismatch.");
            }
            if (!isEmail(email)) {
                // malformed address
                errors.put("email", "Invalid email address.");
            }
            if (dao.user.findByPropertyEqual("userName", username).size() > 0) {
                // username is taken already
                errors.put("username", "Username is taken.");
            }

            if (errors.size() == 0) {
                // add new user
                User user = new User(firstName, lastName, username, organization, introduction, LocalDate.parse(dateOfBirth));
                try {

                    Auth auth = new Auth();

                    user = auth.setUserHashPass(user, password0);

                    if (user != null) {
                        try {
                            //logger.debug(user.getUserName() + " pass: " + password0 + ".");
                            //request.login(user.getUserName(), password0);
                            request.getSession().setAttribute("user", user);
                            // TODO should be setting a default directory/video/note here
                            logger.debug("Got THIS far");
                            // but cannot log in with the new credentials
                        } catch (Exception e) {
                            logger.debug("Login erroneous");
                            e.printStackTrace();
                            dao.user.delete(user);
                            logger.debug("User could not be saved");
                        }
                    } else {
                        logger.debug("something went wrong in setUserHashPass");
                    }

                } catch (Exception e) {
                    logger.debug("Problem setting the user's hash pass");
                    e.printStackTrace();
                    //errors.put("", "");
                }

                // TODO store confirmation token
                // TODO create and send confirmation email
                // TODO in doGet, receive new user verification, direct to login
                // TODO redirect to new user profile

            }
        } else {
            // loop through all request parameters, make error messages for missing fields
            for (Map.Entry<String, String[]> entry: request.getParameterMap().entrySet()) {
                if (entry.getValue() == null || entry.getValue().length == 0 || entry.getValue()[0] == null) {
                    errors.put(entry.getKey(), entry.getKey() + " must be set.");
                }
            }
        }

        if (errors.size() > 0) {
            // return error messages to user
            request.getSession().setAttribute("errors", errors);

        }

        // return to signup and report errors
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            logger.debug(entry.getKey() + ":" + entry.getValue());
        }


        // go to start page
        //String url = "loadClient";
        String url = "index.jsp";
        response.sendRedirect(url);
    }

    private boolean isEmail(String email) {
        return email.matches("^\\w+@\\w+\\.\\w+$");
    }
}