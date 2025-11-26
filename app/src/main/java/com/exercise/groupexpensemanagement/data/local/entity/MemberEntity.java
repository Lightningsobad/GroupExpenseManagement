package com.exercise.groupexpensemanagement.data.local.entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(
        tableName = "MEMBER",
        foreignKeys = @ForeignKey(
                entity = GroupEntity.class,
                parentColumns = "groupID",
                childColumns = "groupID",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("groupID")}
)
public class MemberEntity {
    @PrimaryKey(autoGenerate = true)
    public int memberID;

    public int groupID; //FK
    public String name;
    public int yob;
    public String address;
    public Date entryDate;
    public String phoneNumber;
    public String email;
    public String avatar;

}
