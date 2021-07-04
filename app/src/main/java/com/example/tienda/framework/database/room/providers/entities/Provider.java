package com.example.tienda.framework.database.room.providers.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "providers")
public class Provider {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String company;
    private String phone;

    public Provider(int id, String name, String company, String phone) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
