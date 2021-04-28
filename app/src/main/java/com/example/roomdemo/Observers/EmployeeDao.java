package com.example.roomdemo.Observers;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdemo.Model.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM EMPLOYEE ORDER BY ID")
    List<Employee> loadAllEmployees();

    @Insert
    void insertEmployee(Employee employee);

    @Update
    void updateEmployee(Employee employee);

    @Delete
    void delete(Employee employee);

    @Query("SELECT * FROM Employee WHERE id = :id")
    Employee loadEmployeeById(int id);
}
