package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.util.ConnectionFactory;

import java.sql.*;
import java.util.*;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<Reimbursement> getById(int id) {
        return Optional.empty();
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        Reimbursement reim = new Reimbursement();
        List<Reimbursement> myList = new ArrayList<Reimbursement>();
        UserDAO tempA = new UserDAO();
        UserDAO tempR = new UserDAO();
        try {
            String sql = "SELECT * FROM reimburstments WHERE status = ?";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,status.toString());

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                reim.setId(rs.getInt("item_id"));
                reim.setAuthor(tempA.read(rs.getString("author")));
                reim.setStatus(Status.valueOf(rs.getString("status")));
                reim.setResolver(tempR.read(rs.getString("resolver")));
                reim.setAmount(rs.getDouble("amount"));
                reim.setId(rs.getInt("user_id"));
                myList.add(reim);
            }
        } catch (SQLException e) {e.printStackTrace();}
        return myList;
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
        String sql = "UPDATE reimbursements SET author = ?, status = ?, resolver = ?, amount = ?, user_id = ? WHERE item_id = ?";
        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, unprocessedReimbursement.getAuthor().getUsername());
            pstmt.setString(2, unprocessedReimbursement.getStatus().toString());
            pstmt.setString(3, unprocessedReimbursement.getResolver().getUsername());
            pstmt.setDouble(4, unprocessedReimbursement.getAmount());
            pstmt.setInt(5, unprocessedReimbursement.getAuthor().getId());
            pstmt.setInt(6, unprocessedReimbursement.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
        return unprocessedReimbursement;
    }
    public Reimbursement create(Reimbursement newReimb) {
        String sql = "INSERT INTO reimbursements (author,status,resolver,amount,user_id) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, newReimb.getAuthor().getUsername());
            pstmt.setString(2, newReimb.getStatus().toString());
            pstmt.setString(3, null);
            pstmt.setDouble(4, newReimb.getAmount());
            pstmt.setInt(5, newReimb.getAuthor().getId());
            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                int key = keys.getInt(1);
                newReimb.setId(key);
            }
        } catch(SQLException e) {e.printStackTrace();}
        return newReimb;
    }

    public Reimbursement read(int id){
        Reimbursement reimbursement = new Reimbursement();
        UserDAO myUserDAO = new UserDAO();
        UserDAO myResolverDAO = new UserDAO();

        try {
            String sql = "SELECT * FROM reimbursements WHERE item_id = ?";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                reimbursement.setId(rs.getInt("item_id"));
                reimbursement.setAuthor(myUserDAO.read(rs.getString("author")));
                reimbursement.setStatus(stringToStatus(rs.getString("status")));
                reimbursement.setResolver(myResolverDAO.read(rs.getString("resolver")));
                reimbursement.setAmount(rs.getDouble("amount"));
            }
        } catch (SQLException e) {e.printStackTrace();}
        return reimbursement;
    }

    public List<Reimbursement> read(String username){
        UserDAO myUserDAO = new UserDAO();
        UserDAO myResolverDAO = new UserDAO();
        List<Reimbursement> ll = new LinkedList<>();

        try {
            String sql = "SELECT * FROM reimbursements WHERE author = ?";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setId(rs.getInt("item_id"));
                reimbursement.setAuthor(myUserDAO.read(rs.getString("author")));
                reimbursement.setStatus(stringToStatus(rs.getString("status")));
                reimbursement.setResolver(myResolverDAO.read(rs.getString("resolver")));
                reimbursement.setAmount(rs.getDouble("amount"));
                ll.add(reimbursement);
            }
        } catch (SQLException e) {e.printStackTrace();}
        return ll;
    }

    public List<Reimbursement> read(Status status){
        UserDAO myUserDAO = new UserDAO();
        UserDAO myResolverDAO = new UserDAO();
        List<Reimbursement> ll = new LinkedList<>();

        try {
            String sql = "SELECT * FROM reimbursements WHERE status = ?";
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,status.toString());

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setId(rs.getInt("item_id"));
                reimbursement.setAuthor(myUserDAO.read(rs.getString("author")));
                reimbursement.setStatus(stringToStatus(rs.getString("status")));
                reimbursement.setResolver(myResolverDAO.read(rs.getString("resolver")));
                reimbursement.setAmount(rs.getDouble("amount"));
                ll.add(reimbursement);
            }
        } catch (SQLException e) {e.printStackTrace();}
        return ll;
    }

    public void delete(Reimbursement toBeRemoved){
        String sql = "";
    }

    public Status stringToStatus(String status){
        if(status.equalsIgnoreCase("Denied")){return Status.DENIED;}
        else if(status.equalsIgnoreCase("Pending")){return Status.PENDING;}
        else {return Status.APPROVED;}
    }

}
