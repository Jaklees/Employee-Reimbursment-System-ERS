package com.revature.repositories;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.*;
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"username");

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {e.printStackTrace();}
        Optional<User> userOptional = Optional.of(user);

        if (userOptional.isPresent()) { return userOptional;}
        else {return Optional.empty();}
    }
    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     */
    /**public User create(User userToBeRegistered) {
        String sql = "INSERT INTO users (user_id,username,password,role) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,userToBeRegistered.getId());
            pstmt.setString(2,userToBeRegistered.getUsername());
            pstmt.setString(3,userToBeRegistered.getPassword());
            pstmt.setString(4, userToBeRegistered.getRole().toString());
            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()){
                int key = keys.getInt(1);
                userToBeRegistered.setId(key);
            }
        } catch (SQLException e) { e.printStackTrace();}
        return userToBeRegistered;
    }*/

    public User create(User userToBeRegistered) {
        String sql = "INSERT INTO users (username,password,role) VALUES (?,?,?)";
        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //pstmt.setInt(1,userToBeRegistered.getId());
            pstmt.setString(1,userToBeRegistered.getUsername());
            pstmt.setString(2,userToBeRegistered.getPassword());
            pstmt.setString(3, userToBeRegistered.getRole().toString());
            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()){
                int key = keys.getInt(1);
                userToBeRegistered.setId(key);
            }
        } catch (SQLException e) { e.printStackTrace();}
        return userToBeRegistered;
    }

    public User read(int id) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(stringToRole(rs.getString("role")));
            }
        } catch (SQLException e) {e.printStackTrace();}
        return user;
    }

    public User read(String username) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(stringToRole(rs.getString("role")));
            }
        } catch (SQLException e) {e.printStackTrace();}
        return user;
    }

    private Role stringToRole(String roll){
        if(roll.equalsIgnoreCase("employee")){ return Role.EMPLOYEE; }
        else { return Role.FINANCE_MANAGER; }
    }


}
