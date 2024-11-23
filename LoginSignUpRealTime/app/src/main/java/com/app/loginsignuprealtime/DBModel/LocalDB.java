package com.app.loginsignuprealtime.DBModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.app.loginsignuprealtime.DBModel.Actions.UserDataDAO;
import com.app.loginsignuprealtime.DBModel.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class LocalDB extends RoomDatabase {
    public abstract  UserDataDAO UserDataDAO();
}
