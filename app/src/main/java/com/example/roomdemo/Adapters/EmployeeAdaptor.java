package com.example.roomdemo.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdemo.Activities.EditActivity;
import com.example.roomdemo.DataBase.AppDatabase;
import com.example.roomdemo.Model.Employee;
import com.example.roomdemo.Observers.GetEmployeePosition;
import com.example.roomdemo.R;

import java.util.List;

public class EmployeeAdaptor extends RecyclerView.Adapter<EmployeeAdaptor.MyViewHolder> {
    private Context context;
    private List<Employee> mEmployeeList;
    GetEmployeePosition getEmployeePosition;

    public EmployeeAdaptor(Context context, GetEmployeePosition getEmployeePosition) {
        this.context = context;
        this.getEmployeePosition = getEmployeePosition;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdaptor.MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mEmployeeList.get(i).getName());
        myViewHolder.email.setText(mEmployeeList.get(i).getEmail());
        myViewHolder.number.setText(mEmployeeList.get(i).getNumber());
        myViewHolder.age.setText(mEmployeeList.get(i).getAge());

        myViewHolder.editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmployeePosition.getPosition(mEmployeeList.get(i).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mEmployeeList == null) {
            return 0;
        }
        return mEmployeeList.size();

    }

    public void setTasks(List<Employee> employeeList) {
        mEmployeeList = employeeList;
        notifyDataSetChanged();
    }

    public List<Employee> getTasks() {
        return mEmployeeList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, number, age;
        ImageView editImage;
        AppDatabase mDb;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            name = itemView.findViewById(R.id.employee_name);
            email = itemView.findViewById(R.id.employee_email);
            number = itemView.findViewById(R.id.employee_number);
            age = itemView.findViewById(R.id.employee_age);
            editImage = itemView.findViewById(R.id.edit_Image);

        }
    }
}
