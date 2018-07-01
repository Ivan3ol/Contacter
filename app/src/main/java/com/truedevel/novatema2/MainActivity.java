package com.truedevel.novatema2;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.truedevel.novatema2.room.App;
import com.truedevel.novatema2.room.ContactRepository;
import com.truedevel.novatema2.room.ContactViewModel;
import com.truedevel.novatema2.room.core.ContactDao;
import com.truedevel.novatema2.room.core.Database;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  MainActivity extends AppCompatActivity {


    static ArrayList<Contact> products = new ArrayList<>();
    static ArrayList<String> groups = new ArrayList<>();
    BoxAdapter boxAdapter;
    String name,phone;
    ContactViewModel mRoomModule;
    MainActivity x;
    ListView lvMain;
     AlertDialog.Builder alert;
    int secondActivityID = 2;
    ContactDao contactDao;
    Database db;
    ArrayList<Contact> ar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRoomModule = ViewModelProviders.of(this).get(ContactViewModel.class);
        lvMain = findViewById(R.id.lvMain);


        mRoomModule.getmRepository().getmAllContacts().observeForever(new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                products.clear();
                products.addAll(contacts);
                boxAdapter = new BoxAdapter(getBaseContext(), products);
                lvMain.setAdapter(boxAdapter);
            }
        });

//      fillData();
        filldata_always();

        // настраиваем список

        // генерируем данные для адаптера
        groups.add("Relatives");
        groups.add("Friends");
        groups.add("Coworkers");


        x = this;


        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ar.add(new Contact("sdfs", "sds7d5","sdsd","fgdf","we",231));
//                mRoomModule.getmRepository().insertEndMethod(ar);
                alert = new AlertDialog.Builder(x);
                LinearLayout lila1 = new LinearLayout(x);
                lila1.setOrientation(LinearLayout.VERTICAL); //1 is for vertical orientation
               /* final Spinner spin = new Spinner(x);
                spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                SpinnerAdapter customAdapter=new SpinnerAdapter(getApplicationContext());
                spin.setAdapter(customAdapter);*/
                final EditText input = new EditText(x);
                input.setHint("Name");
                final EditText input1 = new EditText(x);
                input1.setHint("Phone");
                final EditText input2 = new EditText(x);
                input2.setHint("Address");
                final EditText input3 = new EditText(x);
                input3.setHint("Bithdate");
                final EditText input4 = new EditText(x);
                input4.setHint("Group");



                lila1.addView(input);
                lila1.addView(input1);
                lila1.addView(input2);
                lila1.addView(input3);
                lila1.addView(input4);
                alert.setView(lila1);

                alert.setTitle("Add contact");

                alert.setPositiveButton("Ok", (dialog, whichButton) -> {
                    name = input.getText().toString();
                    phone = input1.getText().toString();
                    String ad = input2.getText().toString();
                    String birth = input3.getText().toString();
                    String group = input4.getText().toString();
                    ar.add(new Contact(name, phone,ad,birth,group,R.drawable.man_3));
                    mRoomModule.getmRepository().insertEndMethod(ar);
                    mRoomModule.getmRepository().getmAllContacts().observeForever(contacts -> {
                        products.clear();
                        products.addAll(contacts);
                        boxAdapter = new BoxAdapter(getBaseContext(), products);
                        lvMain.setAdapter(boxAdapter);
                    });
                    products.addAll(ar);
                    ar.clear();
                    boxAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

                });
                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                dialog.cancel();
                            }
                        });

                alert.show();
            }
        });

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent i = new Intent(x, One_Contact.class);
               i.putExtra("name", products.get(position).name);
                i.putExtra("phonenumber",  products.get(position).phone);
                i.putExtra("adr", products.get(position).address);
                i.putExtra("birth",  products.get(position).birthdate);
                i.putExtra("gr",  products.get(position).group);
                i.putExtra("ava",  products.get(position).avatar);
                i.putExtra("idcon",  position);
                startActivityForResult(i, secondActivityID);

            }
        });
    }




    void fillData() {
//      try{  products.addAll(mRoomModule.getmRepository().getmAllContacts().); } catch(NullPointerException e){
//          Toast.makeText(this,"Exception",Toast.LENGTH_SHORT).show();
//      }
        products.add(new Contact("Product ", "+380930542117","New York","23.03","Friends",R.drawable.boy));
        products.add(new Contact("Jhon Smith", "+380930542118","Selo","6.09","Relatives",R.drawable.boy_1));
        products.add(new Contact("David Tenant", "+380930542119","Kyiv","5.15","Classmates",R.drawable.girl));
        products.add(new Contact("Sergey", "+380930542110","Striy","7.09","Coworkers",R.drawable.girl_1));
        products.add(new Contact("David ", "+380930542111","Moldova","6.01","Friends",R.drawable.man));
        products.add(new Contact("Akakiy ", "+380930542112","Moscow","1.01","Coworkers",R.drawable.man_1));
        products.add(new Contact("Krasava Vashe ", "+380930542113","Minsk","6.5","Classmates",R.drawable.man_2));
        products.add(new Contact("Chotkiy paza ", "+380930542114","Gus-Chrustalnoe","8.07","Relatives",R.drawable.man_3));
        products.add(new Contact("Narmalno ", "+380930542115","Drogobich","4.11","Friends",R.drawable.man_4));
//        contactDao.insert(new Contact("Sergey", "+380930542110","Striy","7.09","Coworkers",R.drawable.girl_1));
        mRoomModule.getmRepository().insertEndMethod(products);

    }
    void filldata_always(){
        ArrayList local = new ArrayList();
        local.add(new Contact("Narmalno ", "+380930542115","Drogobich","4.11","Friends",R.drawable.man_4));
        mRoomModule.getmRepository().insertEndMethod(local);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == secondActivityID){
                Bundle extras = data.getExtras();
                int ar = extras.getInt("RES");
                String new_name = extras.getString("RES_NAME");
                String new_phone = extras.getString("RES_PHONE");
                String new_ad = extras.getString("RES_ADRESS");
                String new_date = extras.getString("RES_DATE");
                String new_gr = extras.getString("RES_GROUP");
                int new_photo = products.get(ar).avatar;

                if(new_name!=null & new_phone!=null){

                    products.set(ar,new Contact(new_name,new_phone,new_ad,new_date,new_gr,new_photo));
                    mRoomModule.getmRepository().clearAll();
                    mRoomModule.getmRepository().insertEndMethod(products);
                } else {
                    mRoomModule.getmRepository().delete(products.get(ar));
                  //  products.remove(ar);
                   // mRoomModule.getmRepository().clearAll();
                   // mRoomModule.getmRepository().insertEndMethod(products);

                }
                    boxAdapter.notifyDataSetChanged();
            }
        }
    }
}