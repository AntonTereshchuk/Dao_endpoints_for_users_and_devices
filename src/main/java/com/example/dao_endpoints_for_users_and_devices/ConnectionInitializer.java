package com.example.dao_endpoints_for_users_and_devices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionInitializer {

    static final String db_url = "jdbc:mysql://localhost:3306/users_and_devices";
    static final String db_user = "root";
    static final String db_password = "2020";

    public static   Connection createConnection() throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection(db_url, db_user, db_password);
        return connection;
    }

}