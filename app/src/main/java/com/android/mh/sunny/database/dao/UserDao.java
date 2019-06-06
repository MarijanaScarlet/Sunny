package com.android.mh.sunny.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.android.mh.sunny.database.entitets.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE username LIKE :uname AND " + "password LIKE :pass LIMIT 1")
    User findUser(String uname, String pass);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

}
