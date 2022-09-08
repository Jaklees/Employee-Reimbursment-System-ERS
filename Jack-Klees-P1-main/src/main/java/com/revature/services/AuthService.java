package com.revature.services;

import com.revature.models.AbstractUser;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

import java.sql.SQLException;
import java.util.Optional;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {

    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     * </ul>
     */
    public User login(String username, String password) {
        User myUser = new User();
        UserDAO userDAO = new UserDAO();
        try{
            myUser = userDAO.read(username);
            if(myUser.equals(new User())){
                System.out.println("Error: Invalid Username");
                return null;
            }
            else if(!(password.equals(myUser.getPassword()))){
                System.out.println("Error: Invalid Password");
                return null;
            }
            else{
                System.out.println("User was authenticated... logging in...");
                System.out.println(myUser);
                return myUser;
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return myUser;
    }

    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li>
     *     <li>Must throw exception if the username/email is not unique.</li>
     *     <li>Should persist the user object upon successful registration.</li>
     *     <li>Must throw exception if registration is unsuccessful.</li>
     *     <li>Must return user object if the user registers successfully.</li>
     *     <li>Must throw exception if provided user has a non-zero ID</li>
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     */
    public User register(User userToBeRegistered) {
        User checkUser = new User();
        UserDAO userDAO = new UserDAO();
        String username = userToBeRegistered.getUsername();
        checkUser = userDAO.read(username);
        if(userToBeRegistered.getUsername().equals(checkUser)){
            return null;
        }
        else {
            userToBeRegistered = userDAO.create(userToBeRegistered);
            return userToBeRegistered;
        }
    }

    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public Optional<User> exampleRetrieveCurrentUser() {
        return Optional.empty();
    }
}
