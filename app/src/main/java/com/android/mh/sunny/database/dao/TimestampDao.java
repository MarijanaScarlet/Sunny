package com.android.mh.sunny.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.android.mh.sunny.database.entitets.Timestamp;

import java.util.List;

@Dao
public interface TimestampDao {

    @Query("SELECT * FROM timestamps")
    List<Timestamp> getAll();

    @Insert
    void insert(Timestamp timestamp);

    @Delete
    void delete(Timestamp timestamp);
}
