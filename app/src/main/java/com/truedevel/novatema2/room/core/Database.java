package com.truedevel.novatema2.room.core;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.truedevel.novatema2.Contact;

@android.arch.persistence.room.Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase{
    public abstract ContactDao contactDao();
    private static Database INSTANCE;

    public static Database getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (Database.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),Database.class,"Contacts").build();
                }
            }
        }
        return INSTANCE;
    }
}
