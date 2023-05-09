package com.example.dao_endpoints_for_users_and_devices.dao;

import com.example.dao_endpoints_for_users_and_devices.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    public List<User> showAllUsers(Connection conn) throws SQLException {

        Statement st    = conn.createStatement();
        ResultSet rs    = st.executeQuery("SELECT * from Users");

        List<User> usersList = new ArrayList<>();

        while (rs.next()) {
            User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5));
            usersList.add(user);
        }

        st.close();

        return usersList;

    }

    public User showUserWithId(Connection conn, Integer id) throws SQLException {

        User user;

        PreparedStatement pSt = conn.prepareStatement("SELECT * from Users where id = ?");
        pSt.setInt(1, id);
        ResultSet rs          = pSt.executeQuery();

        if (rs.next()) {
            user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5));
        }
        else {
            System.out.println("No user found by id:" + id + " !");
            user = null;
        }

        pSt.close();

        return user;

    }

    public void postUser(Connection conn, User user) throws SQLException {

        PreparedStatement pSt = conn.prepareStatement("INSERT INTO Users values(?, ?, ?, ?, ?)");
        pSt.setInt(1, user.getId());
        pSt.setString(2, user.getName());
        pSt.setString(3, user.getSurname());
        pSt.setString(4, user.getPhone());
        pSt.setString(5, user.getGender());
        pSt.executeUpdate();
        pSt.close();

    }

    public void putUserWithId(Connection conn, User user, int id) throws SQLException {

        PreparedStatement pSt = conn.prepareStatement("UPDATE Users set name = ?, surname = ?," +
                " phone = ?, gender = ? where id = ?");
        pSt.setString(1, user.getName());
        pSt.setString(2, user.getSurname());
        pSt.setString(3, user.getPhone());
        pSt.setString(4, user.getGender());
        pSt.setInt(5, id);
        pSt.executeUpdate();
        pSt.close();

    }

    public String deleteUserWithId(Connection conn, int id) throws SQLException {

        String result;

        PreparedStatement pSt1 = conn.prepareStatement("SELECT * from Users where id = ?");
        pSt1.setInt(1, id);
        ResultSet rs          = pSt1.executeQuery();

        if (rs.next()) {
            PreparedStatement pSt2 = conn.prepareStatement("DELETE from Users where id = ?");
            pSt2.setInt(1, id);
            pSt2.executeUpdate();
            pSt2.close();
            result = "User was successfully deleted";
        } else {
            result = "User not found";
        }

        pSt1.close();

        return result;

    }

}
