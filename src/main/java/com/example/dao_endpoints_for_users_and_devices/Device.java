package com.example.dao_endpoints_for_users_and_devices;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class Device {

    @Min(0)
    int macadress;
    String title;
    @Min(0)
    int userId;

    public Device(int macadress, String title, int userId) {
        this.macadress = macadress;
        this.title = title;
        this.userId = userId;
    }

}
