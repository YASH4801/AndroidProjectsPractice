package com.app.loginsignuprealtime.DBModel;

import android.app.Application;

import androidx.room.Room;

public class RoomImplementation extends Application {

    private static RoomImplementation instance;
    private LocalDB dbInstance;

    public static RoomImplementation getInstance(){
        return instance;
    }

    public LocalDB getDbInstance(){
        return this.dbInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        dbInstance = Room.databaseBuilder(getApplicationContext(), LocalDB.class, "testDB").build();
    }


}
