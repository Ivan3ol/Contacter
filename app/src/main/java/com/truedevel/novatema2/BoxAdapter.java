package com.truedevel.novatema2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Contact> contacts;

    BoxAdapter(Context context, ArrayList<Contact> contacts) {
        ctx = context;
        this.contacts = contacts;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return contacts.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        Contact c = getContact(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.Name)).setText(c.name);
        ((TextView) view.findViewById(R.id.Phone)).setText(c.phone);

        return view;
    }

    // товар по позиции
    Contact getContact(int position) {
        return ((Contact) getItem(position));

    }
}