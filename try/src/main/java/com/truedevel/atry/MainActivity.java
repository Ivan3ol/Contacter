package com.truedevel.atry;



import android.arch.persistence.room.Room;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //AppDatabase db = App.getInstance().getDatabase();
        AppDatabase db =  Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").build();
        EmployeeDao employeeDao = db.employeeDao();
        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "John Smith";
        employee.salary = 10000;
        employeeDao.insert(employee);
        List<Employee> employees = employeeDao.getAll();
       try{ Employee employee1 = employeeDao.getById(1);}catch(Exception e){
           Toast.makeText(this,"Exception",Toast.LENGTH_SHORT).show();
       }
        employee.salary = 20000;
        employeeDao.update(employee);
        // создание LinearLayout
        LinearLayout linLayout = new LinearLayout(this);
        // установим вертикальную ориентацию
        linLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView tv = new TextView(this);
        tv.setText(employee.name);
        tv.setLayoutParams(lpView);
        linLayout.addView(tv);

        Button btn = new Button(this);
        btn.setText("Button");
        linLayout.addView(btn, lpView);

        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.boy);

        setContentView(linLayout, lpView);
    }
}