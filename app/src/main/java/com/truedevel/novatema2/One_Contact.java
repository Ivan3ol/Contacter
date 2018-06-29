package com.truedevel.novatema2;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class One_Contact extends AppCompatActivity {
    String phone;
    String fullname;
    int id_contact,ava;
    TextView  tv2, tvAdress, tvBirth,tvGroup,name_tool;
    ImageView btn_send,btn_call,ava_tool;
    public AlertDialog.Builder alert;
    One_Contact x = this;
    Contact old;
    String new_name,new_phone,date,adress,group;
    EditText message;

    ListView messages;
    ArrayList array = new ArrayList();
    ArrayList mess = new ArrayList();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one__contact);
        Bundle extras = getIntent().getExtras();


        phone = extras.getString("phonenumber");
        fullname = extras.getString("name");
        date = extras.getString("birth");
        adress = extras.getString("adr");
        group = extras.getString("gr");
        ava = extras.getInt("ava");
        id_contact = extras.getInt("idcon");

        tvAdress = findViewById(R.id.Address);
        tvBirth = findViewById(R.id.Birthday);
        tvGroup = findViewById(R.id.Group);

        tv2 = findViewById(R.id.textViewPhone);
        ava_tool = findViewById(R.id.avatar_tool);
        name_tool = findViewById(R.id.name_toolbar);

        messages = findViewById(R.id.messages);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mess);
        messages.setAdapter(adapter);

        tv2.setText(phone);
        tvAdress.setText(adress);
        tvBirth.setText(date);
        tvGroup.setText(group);
        btn_call = findViewById(R.id.CallButton);

        message = findViewById(R.id.input_message);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        ava_tool.setImageResource(ava);
        name_tool.setText(fullname);

        btn_send = findViewById(R.id.ImageViewSend);
       btn_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SendMess(message.getText().toString());
            }
        });

    }


    public void makeCall(View view){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(One_Contact.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        startActivity(callIntent);
    }
    void SendMess(String msg) {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        mess.add(msg);
        adapter.notifyDataSetChanged();
        msg = encode(msg);
        myIntent.putExtra(Intent.EXTRA_TEXT, msg);
        startActivity(myIntent);
    }
    static String encode(String s) {
        int bitLength = s.length() * 8;
        int keyLength = 32 - Integer.numberOfLeadingZeros(bitLength);
        int key = 0;
        int phaze = 0b1;
        String result = "";


        switch (keyLength) {
            case 4:
                key = 0b1100;
                break;
            case 5:
                key = 0b10100;
                break;
            case 6:
                key = 0b110000;
                break;
            case 7:
                key = 0b1100000;
                break;
            case 8:
            case 9:
                key = 0b100010000;
                break;
            case 10:
                key = 0b1001000000;
                break;
        }

        for (int i = 0; i < s.length(); i++) {
            int z = 0;
            char ch = s.charAt(i);

            for (int j = 0; j < 8; j++) {
                int temp = phaze & key;

                for (int k = 0; k < keyLength; k++) {
                    z += temp % 2;
                    temp /= 2;
                }
                z %= 2;
                phaze *= 2;
                phaze += z;
            }
            result += ((char) (ch ^ phaze));
        }
        return result;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_edit :
                alert = new AlertDialog.Builder(x);

                old =  MainActivity.products.get(id_contact);

                LinearLayout lila1 = new LinearLayout(x);
                lila1.setOrientation(LinearLayout.VERTICAL);

                final EditText input = new EditText(x);
                input.setText(old.name, TextView.BufferType.SPANNABLE);
                final EditText input1 = new EditText(x);
                input1.setText(old.phone, TextView.BufferType.SPANNABLE);
                final EditText input2 = new EditText(x);
                input2.setText(old.address, TextView.BufferType.SPANNABLE);
                final EditText input3 = new EditText(x);
                input3.setText(old.birthdate, TextView.BufferType.SPANNABLE);
                final EditText input4 = new EditText(x);
                input4.setText(old.group, TextView.BufferType.SPANNABLE);

                lila1.addView(input);
                lila1.addView(input1);
                lila1.addView(input2);
                lila1.addView(input3);
                lila1.addView(input4);
                alert.setView(lila1);

                alert.setTitle("Edit Contact");

                alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        new_name = input.getText().toString();
                        new_phone = input1.getText().toString();
                        String new_ad = input2.getText().toString();
                        String new_date = input3.getText().toString();
                        String new_gr = input4.getText().toString();
                        Toast.makeText(getApplicationContext(), "Edited", Toast.LENGTH_SHORT).show();
                        Intent back = new Intent();
                        back.putExtra("RES", id_contact);
                        back.putExtra("RES_NAME", new_name);
                        back.putExtra("RES_PHONE", new_phone);
                        back.putExtra("RES_ADRESS", new_ad);
                        back.putExtra("RES_DATE", new_date);
                        back.putExtra("RES_GROUP", new_gr);
                        setResult(RESULT_OK, back);
                        finish();

                    }
                });
                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });

                alert.show();
                Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
                break;
             case R.id.item_delete :
                 alert = new AlertDialog.Builder(x);

                 alert.setTitle("Delete");
                 alert.setMessage("Are you sure?");
                 alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int whichButton) {


                         Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                         Intent back = new Intent();
                         back.putExtra("RES", id_contact);
                         setResult(RESULT_OK, back);
                         finish();
                     }
                 });
                 alert.setNegativeButton("Cancel",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int whichButton) {
                                 dialog.cancel();
                             }
                         });

                 alert.show();
                 Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
             break;
        }
        return true;
    }




}







