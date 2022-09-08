package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.services.ReimbursementService;
import com.revature.models.Status;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReimbursementService reimbursementService = new ReimbursementService();
        String statusString = req.getHeader("status");
        ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
        Status status = reimbursementDAO.stringToStatus(statusString);
        List<Reimbursement> list = new LinkedList<>();
        list = reimbursementService.getReimbursementsByStatus(status);
        if(list.isEmpty()){
            resp.setStatus(401);
        }
        else {
            resp.setStatus(200);
            resp.getWriter().print(new ObjectMapper().writeValueAsString(list));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getHeader("username");
        UserService userService = new UserService();
        User user = userService.getByUsername(username);
        String stringAmount = req.getHeader("amount")+"d";
        double amount = Double.parseDouble(stringAmount);
        ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
        Reimbursement newReimbursement = new Reimbursement(0,Status.PENDING,user,null,amount);
        newReimbursement = reimbursementDAO.create(newReimbursement);
        if(newReimbursement.equals(new Reimbursement())){
            resp.setStatus(401);
        }
        else{
            resp.setStatus(200);
            resp.getWriter().print(new ObjectMapper().writeValueAsString(newReimbursement));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: PUT
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: DELETE
    }
}
