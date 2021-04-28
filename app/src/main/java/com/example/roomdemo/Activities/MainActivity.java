package com.example.roomdemo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roomdemo.Adapters.EmployeeAdaptor;
import com.example.roomdemo.DataBase.AppDatabase;
import com.example.roomdemo.DataBase.AppExecutors;
import com.example.roomdemo.Model.Employee;
import com.example.roomdemo.Observers.GetEmployeePosition;
import com.example.roomdemo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GetEmployeePosition {
    FloatingActionButton addFab;
    EmployeeAdaptor employeeAdaptor;
    RecyclerView recyclerView;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        addFab = findViewById(R.id.addFAB);
        recyclerView = findViewById(R.id.recyclerView);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        employeeAdaptor = new EmployeeAdaptor(this, MainActivity.this);
        recyclerView.setAdapter(employeeAdaptor);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<Employee> tasks = employeeAdaptor.getTasks();
                        appDatabase.employeeDao().delete(tasks.get(position));
                        retrieveTasks();
                    }
                });
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveTasks();
    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Employee> employees = appDatabase.employeeDao().loadAllEmployees();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        employeeAdaptor.setTasks(employees);
                    }
                });
            }
        });


    }

    @Override
    public void getPosition(int position) {
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra("employee_id", position);
        startActivity(intent);
    }
}