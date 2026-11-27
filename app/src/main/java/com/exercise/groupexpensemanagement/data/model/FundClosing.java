package com.exercise.groupexpensemanagement.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class FundClosing implements Serializable, TransactionItem {
    @SerializedName("maDotDongQuy")
    private int id;
    @SerializedName("maQuy")
    private int idFund;
    @SerializedName("tenDotDongQuy")
    private String name;
    @SerializedName("ngayBatDauDong")
    private Date date;
    @SerializedName("soTienMoiNguoiCanDong")
    private Integer moneyForEachPerson;
    @SerializedName("ghiChu")
    private String note;

    public FundClosing() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setMoneyForEachPerson(Integer moneyForEachPerson) {
        this.moneyForEachPerson = moneyForEachPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFund() {
        return idFund;
    }

    public void setIdFund(int idFund) {
        this.idFund = idFund;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMoneyForEachPerson() {
        return moneyForEachPerson;
    }

    public void setMoneyForEachPerson(int moneyForEachPerson) {
        this.moneyForEachPerson = moneyForEachPerson;
    }

    @Override
    public String getTitle() {
        return getName();
    }

    @Override
    public int getAmount() {
        return getMoneyForEachPerson();
    }
}
