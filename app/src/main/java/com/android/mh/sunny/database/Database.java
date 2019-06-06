package com.android.mh.sunny.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.android.mh.sunny.database.dao.UserDao;
import com.android.mh.sunny.database.entitets.User;

@android.arch.persistence.room.Database(entities = {User.class}, exportSchema = false, version = 1)
public abstract class Database extends RoomDatabase {

    private static final String DB_NAME = "person_db";
    private static Database instance;

    public static synchronized Database getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, Database.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

            //instance.userDao().insert(new User("m", "m"));
        }

        return instance;
    }

    public abstract UserDao userDao();

}
