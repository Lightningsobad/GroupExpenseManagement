package com.exercise.groupexpensemanagement.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {
    @SerializedName("maSuKienChiTieu")
    private int id;
    @SerializedName("maNhom")
    private int idGroup;
    @SerializedName("tenSuKienChiTieu")
    private String name;
    @SerializedName("ngayBatDau")
    private Date dateBegin;
    @SerializedName("ngayKetThuc")
    private String dateEnd;
    @SerializedName("tongTienChiTieu")
    private Integer money;
    @SerializedName("ghiChu")
    private String note;

    public Expense() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
