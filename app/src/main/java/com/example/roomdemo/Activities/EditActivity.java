package com.example.roomdemo.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdemo.DataBase.AppDatabase;
import com.example.roomdemo.DataBase.AppExecutors;
import com.example.roomdemo.Model.Employee;
import com.example.roomdemo.R;

public class EditActivity extends AppCompatActivity {
    EditText name, email, number, age,city;
    Button btn_save;
    AppDatabase appDatabase;
    Intent intent;
    int empID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        intent = getIntent();
        name = findViewById(R.id.edit_name);
        email = findViewById(R.id.edit_email);
        number = findViewById(R.id.edit_number);
        age = findViewById(R.id.edit_age);
        city = findViewById(R.id.edit_city);
        btn_save = findViewById(R.id.btn_save);
        appDatabase = AppDatabase.getInstance(getApplicationContext());

        if (intent != null && intent.hasExtra("employee_id")) {
            btn_save.setText("Update");

            empID = intent.getIntExtra("employee_id", -1);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Employee employee = appDatabase.employeeDao().loadEmployeeById(empID);
                    populateUI(employee);
                }
            });

        }
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setValidation())
                    saveData();
            }
        });
    }

    public boolean setValidation() {
        if (name.getText().toString().trim().equalsIgnoreCase("")) {
            name.setError("Enter Name");
            return false;
        }
        if (email.getText().toString().trim().equalsIgnoreCase("")) {
            email.setError("Enter Email");
            return false;
        }
        if (number.getText().toString().trim().equalsIgnoreCase("")) {
            number.setError("Enter Number");
            return false;
        }
        if (age.getText().toString().trim().equalsIgnoreCase("")) {
            age.setError("Enter Age");
            return false;
        }
        return true;
    }

    private void populateUI(Employee employee) {
        if (employee == null) {
            return;
        }
        name.setText(employee.getName());
        email.setText(employee.getEmail());
        number.setText(employee.getNumber());
        age.setText(employee.getAge());
        city.setText(employee.getCity());
    }

    private void saveData() {
        Employee employee = new Employee(name.getText().toString(),
                email.getText().toString(),
                number.getText().toString(),
                age.getText().toString(),
                city.getText().toString());
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!intent.hasExtra("employee_id")) {
                    appDatabase.employeeDao().insertEmployee(employee);
                } else {
                    employee.setId(empID);
                    appDatabase.employeeDao().updateEmployee(employee);
                }
                finish();
            }
        });
    }
}