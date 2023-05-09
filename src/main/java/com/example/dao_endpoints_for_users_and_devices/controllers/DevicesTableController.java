package com.example.dao_endpoints_for_users_and_devices.controllers;

import com.example.dao_endpoints_for_users_and_devices.ConnectionInitializer;
import com.example.dao_endpoints_for_users_and_devices.Device;
import com.example.dao_endpoints_for_users_and_devices.dao.DeviceDAO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class DevicesTableController {

    Connection conn;
    DeviceDAO deviceDAO;

    {
        try {
            conn = ConnectionInitializer.createConnection();
            deviceDAO = new DeviceDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/allDevices")
    public ArrayList<Device> showAllDevices() throws SQLException {
        return (ArrayList<Device>) DeviceDAO.showAllDevices(conn);
    }

    @GetMapping("/device/{macadress}")
    public Device showDeviceWithMacadress(@PathVariable int macadress) throws SQLException {
        return deviceDAO.showDeviceWithMacadress(conn, macadress);
    }

    @PostMapping("/postDevice")
    public String postDevice(@RequestBody @Validated Device device) throws SQLException {
        return deviceDAO.postDevice(conn, device);
    }

    @PutMapping("/putDevice/{macadress}")
    public String putDeviceWithMacadress(@RequestBody @Validated Device device, @PathVariable int macadress) throws SQLException {
        deviceDAO.putDeviceWithMacadress(conn, device, macadress);
        return device + " was updated successfully";
    }

    @DeleteMapping("/deleteDevice/{macadress}")
    public String deleteDeviceWithMacadress(@PathVariable int macadress) throws SQLException {
        return deviceDAO.deleteDeviceWithMacadress(conn, macadress);
    }

}
