package com.android.mh.sunny.database.entitets;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = "username", unique = true )})
public class User {
    @PrimaryKey
    public int id;

    public String username;
    public String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
