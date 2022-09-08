package com.revature;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;
import java.util.UUID;
import com.revature.util.ConnectionFactory;

public class Driver {
    public static void main(String[] args) {
        UserDAO myDao = new UserDAO();
        ReimbursementDAO rDAO = new ReimbursementDAO();
        //System.out.println(rDAO.read("Jack"));
        System.out.println(rDAO.read(Status.PENDING));



       UUID myUuid = UUID.randomUUID();
       ConnectionFactory.close();
    }
}
