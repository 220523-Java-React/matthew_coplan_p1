package com.revature.controller;

import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.http.Handler;


import java.util.List;

public class UserController {

    UserService userService = new UserService();

    public Handler getAllUsers = ctx -> {
        List<User> users = userService.getUsers();
        ctx.json(users);
    };

    public Handler getAllCustomers = ctx -> {
        List<User> customers = userService.getCustomers();
        ctx.json(customers);
    };

    public Handler getAllEmployees = ctx -> {
        List<User> employees = userService.getEmployees();
        ctx.json(employees);
    };
    /*
    public Handler getAllCustomers = ctx -> ctx.json(userService.getAllCustomersAsString());
*/
    /*
    public Handler getAllEmployees = ctx -> ctx.json(userService.getAllEmployeesAsString());
*/

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
            ctx.json(userService.getEmployeeById(id));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };

    public Handler getCustomerById = ctx -> {
        String param = ctx.pathParam("id");
        int id = Integer.parseInt(param);
        try {
            ctx.json(userService.getCustomerById(id));
        } catch (NullPointerException e){
            ctx.result("BROKEN");
        }
    };


    public Handler createNewUser = ctx -> {
        User user = ctx.bodyAsClass(User.class);
        userService.createNewUser(user);
    };
}
