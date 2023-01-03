package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {

    public static void main(String[] args) {


        UserService userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Александр" , "Иванович" , (byte) 45);
        userService.saveUser("Людмила" , "Викторовна" , (byte) 42);
        userService.saveUser("Ярослав" , "Александрович" , (byte) 11);
        userService.saveUser("Степан" , "Александрович" , (byte) 16);
        List<User> users = userService.getAllUsers();
        System.out.println(users.toString());
        userService.removeUserById();
        userService.cleanUsersTable();

    }
}