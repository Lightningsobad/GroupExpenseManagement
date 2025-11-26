package com.exercise.groupexpensemanagement.data.local.entity;


import androidx.room.DeleteTable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(
        tableName = "GROUP",
        foreignKeys = @ForeignKey(
                entity = UserEntity.class,
                parentColumns = "userID",
                childColumns = "userID",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("userID")}
)
public class GroupEntity {
    @PrimaryKey(autoGenerate = true)
    public int groupID;

    public int userID; //FK
    public String groupName;
    public Date createDate;
    public int memberCount;
    public double fund;

}
