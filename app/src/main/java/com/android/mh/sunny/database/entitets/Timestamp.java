package com.android.mh.sunny.database.entitets;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "timestamps")
public class Timestamp {

    @PrimaryKey
    public int id;

    public int dateAndTime;

    public Timestamp(int dateAndTime){
        this.dateAndTime = dateAndTime;
    }
}
