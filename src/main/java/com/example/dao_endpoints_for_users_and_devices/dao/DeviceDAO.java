package com.example.dao_endpoints_for_users_and_devices.dao;

import com.example.dao_endpoints_for_users_and_devices.Device;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceDAO {

    public static List<Device> showAllDevices(Connection conn) throws SQLException {

        Statement st    = conn.createStatement();
        ResultSet rs    = st.executeQuery("SELECT * from Devices order by userId");

        List<Device> devicesList = new ArrayList<>();

        while (rs.next()) {
            Device device = new Device(rs.getInt(1), rs.getString(2), rs.getInt(3));
            devicesList.add(device);
        }

        st.close();

        return devicesList;

    }

    public Device showDeviceWithMacadress(Connection conn, int macadress) throws SQLException {

        Device device;

        PreparedStatement pSt = conn.prepareStatement("SELECT * from Devices where macadress = ?");
        pSt.setInt(1, macadress);
        ResultSet rs          = pSt.executeQuery();

        if (rs.next()) {
            device = new Device(rs.getInt(1), rs.getString(2), rs.getInt(3));
        }
        else {
            System.out.println("No device found by macadress:" + macadress + " !");
            device = null;
        }

        pSt.close();

        return device;

    }

    public String postDevice(Connection conn, Device device) throws SQLException {

        String result;

        PreparedStatement pSt1 = conn.prepareStatement("SELECT * from Devices where userId = ?");
        pSt1.setInt(1, device.getUserId());
        ResultSet rs          = pSt1.executeQuery();

        if (rs.next()) {

            PreparedStatement pSt2 = conn.prepareStatement("INSERT INTO Devices values(?, ?, ?)");
            pSt2.setInt(1, device.getMacadress());
            pSt2.setString(2, device.getTitle());
            pSt2.setInt(3, device.getUserId());
            pSt2.executeUpdate();
            pSt2.close();

            result = "Device was osted successfully";

        } else {

            result = "User id doesn't exist";
        }

        return result;

    }

    public void putDeviceWithMacadress(Connection conn, Device device, int macadress) throws SQLException {

        PreparedStatement pSt = conn.prepareStatement("UPDATE Devices set title = ?, userID = ?" +
                " where macadress = ?");
        pSt.setString(1, device.getTitle());
        pSt.setInt(2, device.getUserId());
        pSt.setInt(3, macadress);
        pSt.executeUpdate();
        pSt.close();

    }

    public String deleteDeviceWithMacadress(Connection conn, int macadress) throws SQLException {

        String result;

        PreparedStatement pSt1 = conn.prepareStatement("SELECT * from Devices where macadress = ?");
        pSt1.setInt(1, macadress);
        ResultSet rs          = pSt1.executeQuery();

        if (rs.next()) {
            PreparedStatement pSt2 = conn.prepareStatement("DELETE from Devices where macadress = ?");
            pSt2.setInt(1, macadress);
            pSt2.executeUpdate();
            pSt2.close();
            result = "Device was successfully deleted";
        } else {
            result = "Device not found";
        }

        pSt1.close();

        return result;

    }

}
