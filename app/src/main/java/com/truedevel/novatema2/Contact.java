package com.truedevel.novatema2;

import android.media.Image;

public class Contact {

    int avatar;
    String name;
    String phone;
    String address;
    String birthdate;
    String group;

    Contact(String name, String phone, String address, String birthdate, String group, int avatar) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;
        this.group = group;
        this.avatar = avatar;
    }
}