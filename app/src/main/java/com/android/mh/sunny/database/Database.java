package com.android.mh.sunny.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.android.mh.sunny.database.dao.TimestampDao;
import com.android.mh.sunny.database.dao.UserDao;
import com.android.mh.sunny.database.entitets.Timestamp;
import com.android.mh.sunny.database.entitets.User;

@android.arch.persistence.room.Database(entities = {User.class, Timestamp.class}, exportSchema = false, version = 3)
public abstract class Database extends RoomDatabase {

    private static final String DB_NAME = "person_db";
    private static Database instance;

    public static synchronized Database getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, Database.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

            instance.userDao().insert(new User("m", "m"));
            instance.timestampDao().insert(new Timestamp((int)System.currentTimeMillis()));
        }

        return instance;
    }

    public abstract UserDao userDao();
    public abstract TimestampDao timestampDao();

}
