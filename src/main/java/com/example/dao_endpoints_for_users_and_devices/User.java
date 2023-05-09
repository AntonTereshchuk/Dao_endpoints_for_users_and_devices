package com.example.dao_endpoints_for_users_and_devices;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
    @Min(0)
    int id;
    String name;
    String surname;
    @Size(max =  13)
    String phone;
    @Size(max =  6)
    String gender;

    public User(int id, String name, String surname, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.gender = gender;
    }

}
