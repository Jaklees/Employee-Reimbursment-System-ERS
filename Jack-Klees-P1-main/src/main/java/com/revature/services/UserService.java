package com.revature.services;

import java.util.Optional;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	public User getByUsername(String username) {
		UserDAO myUserDAO = new UserDAO();
		User checkUser = myUserDAO.read(username);
		if(checkUser.equals(new User())){
			return null;
		}
		else {

			return checkUser;
		}
	}
}
