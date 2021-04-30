package com.example.roomdemo.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "employee")
public class Employee {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "email")
    String email;
    @ColumnInfo(name = "number")
    String number;
    @ColumnInfo(name = "age")
    String age;
    @ColumnInfo(name = "city")
    String city;

    public Employee(String name, String email, String number, String age, String city) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.age = age;
        this.city=city;
    }

    @Ignore
    public Employee(int id, String name, String email, String number, String age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
