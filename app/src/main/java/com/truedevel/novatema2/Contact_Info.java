package com.truedevel.novatema2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Contact_Info extends AppCompatActivity {
    String phone;
    String fullname;
    int id_contact,ava;
    String date,adress,group;
    TextView tv_tool_name,tv_tool_phone;
    ImageView avatar;
    EditText et_date, et_address, et_group, et_name, et_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info_main);
        Bundle extras = getIntent().getExtras();


        phone = extras.getString("phonenumber");
        fullname = extras.getString("name");
        date = extras.getString("birth");
        adress = extras.getString("adr");
        group = extras.getString("gr");
        ava = extras.getInt("ava");
        id_contact = extras.getInt("idcon");

        tv_tool_name = findViewById(R.id.name_toolbar);
        tv_tool_phone = findViewById(R.id.phone_toolbar);
        et_name = findViewById(R.id.input_name);
        et_phone = findViewById(R.id.input_phone);
        et_date = findViewById(R.id.input_birthdate);
        et_address = findViewById(R.id.input_address);
        et_group = findViewById(R.id.input_group);
        avatar = findViewById(R.id.avatar_tool);

        et_name.setText(fullname);
        et_phone.setText(phone);
        et_date.setText(date);
        et_address.setText(adress);
        et_group.setText(group);
        avatar.setImageResource(ava);


    }
    public void makeCall(View view){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(Contact_Info.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        startActivity(callIntent);
    }
}
