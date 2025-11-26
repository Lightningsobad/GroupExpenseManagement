package com.exercise.groupexpensemanagement.data.local.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "USER")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public int userID;

    @NonNull
    public String userName;

    @NonNull
    public String password;
    public String email;
    public String avatar;
}
