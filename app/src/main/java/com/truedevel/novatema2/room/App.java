package com.truedevel.novatema2.room;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.truedevel.novatema2.room.core.Database;

public class App extends Application {

    public static App instance;

    private Database database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, Database.class, "database")
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public Database getDatabase() {
        return database;
    }
}