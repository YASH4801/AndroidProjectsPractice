package com.app.loginsignuprealtime.DBModel.Actions;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.app.loginsignuprealtime.DBModel.entities.User;

@Dao
public interface UserDataDAO {
    @Insert
    void createUser(User userDataObject);

    @Query("Select * from user where userName like :userName")
    User getUserData(String userName);
}
