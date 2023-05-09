package com.example.dao_endpoints_for_users_and_devices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class DaoEndpointsForUsersAndDevicesApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(DaoEndpointsForUsersAndDevicesApplication.class, args);
    }

}
