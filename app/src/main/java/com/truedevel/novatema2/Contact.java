package com.truedevel.novatema2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
@Entity(tableName = "Contacts", indices = {@Index(value = {"phone"},unique = true)})
public class Contact {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "phone")
    public String phone;
    @ColumnInfo(name = "address")
    public String address;
    @ColumnInfo(name = "avatar")
    public int avatar;
    @ColumnInfo(name = "birthdate")
    public String birthdate;
    @ColumnInfo(name = "group")
    public String group;

    Contact(String name, String phone, String address, String birthdate, String group, int avatar) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;
        this.group = group;
        this.avatar = avatar;
    }
}