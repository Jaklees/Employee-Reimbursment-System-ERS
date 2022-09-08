package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.GlobalObjectStore;
import com.revature.dtos.AuthDto;
import com.revature.dtos.UserDto;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        String username = req.getHeader("username");
        User myUser = userService.getByUsername(username);
        if(myUser.equals(null)) {
            resp.setStatus(401);
        }
        else {
            resp.setStatus(200);
            resp.getWriter().print(new ObjectMapper().writeValueAsString(myUser));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthService authService = new AuthService();
        switch(req.getHeader("mode")) {
            case "register":
                AuthDto authDtoReg = new ObjectMapper().readValue(req.getInputStream(), AuthDto.class);
                User newUser  = new User(0, authDtoReg.getUsername(), authDtoReg.getPassword(), Role.EMPLOYEE);
                newUser = authService.register(newUser);
                resp.setStatus(201);
                resp.getWriter().print(new ObjectMapper().writeValueAsString(newUser));
                resp.setHeader("access-control-expose-headers", "authToken");
                resp.setHeader("authToken", newUser.getUsername());
                break;
            case "login":
                AuthDto authDtoLog = new ObjectMapper().readValue(req.getInputStream(),AuthDto.class);
                String username = authDtoLog.getUsername();
                String password = authDtoLog.getPassword();
                User checkUser = authService.login(username,password);
                if(checkUser != null) {
                    resp.setStatus(200);
                    resp.getWriter().print(new ObjectMapper().writeValueAsString(checkUser));
                    resp.setHeader("access-control-expose-headers", "authToken");
                    resp.setHeader("authToken", checkUser.getUsername());
                } else {
                    resp.setStatus(401);
                }
                break;
            default:
                resp.setStatus(400);
                break;
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto updateUserDto = new ObjectMapper().readValue(req.getInputStream(), UserDto.class);
        GlobalObjectStore.removeObject(updateUserDto.getUsername());
        GlobalObjectStore.addObject(updateUserDto.getUsername(), updateUserDto);
        resp.setStatus(200);
        resp.getWriter().print(new ObjectMapper().writeValueAsString(updateUserDto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GlobalObjectStore.removeObject(req.getHeader("username"));
        resp.setStatus(200);
    }
}
