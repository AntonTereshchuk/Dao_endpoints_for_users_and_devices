package com.example.dao_endpoints_for_users_and_devices.controllers;

import com.example.dao_endpoints_for_users_and_devices.ConnectionInitializer;
import com.example.dao_endpoints_for_users_and_devices.User;
import com.example.dao_endpoints_for_users_and_devices.dao.UserDao;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class UsersTableController {

    Connection conn;
    UserDao userDao;

    {
        try {
            conn = ConnectionInitializer.createConnection();
            userDao = new UserDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/allUsers")
    public ArrayList<User> showAllUsers() throws SQLException {
        return (ArrayList<User>) userDao.showAllUsers(conn);
    }

    @GetMapping("/user/{id}")
    public User showUserWithId(@PathVariable Integer id) throws SQLException {
        return userDao.showUserWithId(conn, id);
    }

    @PostMapping("/postUser")
    public String postUser(@RequestBody @Validated User user) throws SQLException {
        userDao.postUser(conn, user);
        return user + " posted successfully";
    }

    @PutMapping("/putUser/{id}")
    public String putUserWithId(@RequestBody @Validated User user, @PathVariable int id) throws SQLException {
        userDao.putUserWithId(conn, user, id);
        return user + " was updated successfully";
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUserWithId(@PathVariable int id) throws SQLException {
        return userDao.deleteUserWithId(conn, id);
    }

}
