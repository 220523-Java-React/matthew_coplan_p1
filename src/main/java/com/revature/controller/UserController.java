package com.revature.controller;

import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.service.UserService;
import io.javalin.http.Handler;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class UserController {

    UserService userService = new UserService();

    public Handler getAllUsers = ctx -> {
        List<User> users;

        String roleParam = ctx.queryParam("role");

        if(roleParam == null){
            users = userService.getUsers();
        }

        else {
            try {
                UserRoles role = UserRoles.valueOf(roleParam.toUpperCase(Locale.ROOT));
                users = userService.getAllUsersByRole(role);
            } catch (IllegalArgumentException e){

                String failureMessage = "{\"success\":false, \"message\":\"" +
                        "Please only use the following role values: " + Arrays.toString(UserRoles.values())
                        + "\"}";

                ctx.status(400).json(failureMessage);
                return;
            }
        }
        ctx.json(users);
    };

    public Handler getAllCustomers = ctx -> {
        List<User> customers = userService.getAllUsersByRole(UserRoles.CUSTOMER);
        ctx.json(customers);
    };

    public Handler getAllEmployees = ctx -> {
        List<User> employees = userService.getAllUsersByRole(UserRoles.EMPLOYEE);
        ctx.json(employees);
    };

    public Handler getUserById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(userService.getUserById(id));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };

    public Handler getEmployeeById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(userService.getUserIdAndCheckRole(id, UserRoles.EMPLOYEE));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };

    public Handler getCustomerById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(userService.getUserIdAndCheckRole(id, UserRoles.CUSTOMER));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };

    public Handler createNewUser = ctx -> {
        User user = ctx.bodyAsClass(User.class);
        userService.createNewUser(user);
    };

    public Handler updateUserById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        User user = ctx.bodyAsClass(User.class);

        userService.updateUserById(user);
    };

}
