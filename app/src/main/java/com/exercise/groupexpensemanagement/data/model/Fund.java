package com.exercise.groupexpensemanagement.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Fund implements Serializable {
    @SerializedName("doT_DONG_QUY")
    private List<FundClosing> fundClosings;
    @SerializedName("maQuy")
    private int id;
    @SerializedName("tenQuy")
    private String name;
    @SerializedName("dotDongQuyGanNhat")
    private String dateNearestClosing;
    @SerializedName("maNhom")
    private int idGroup;

    public Fund() {
    }



    public List<FundClosing> getFundClosings() {
        return fundClosings;
    }

    public void setFundClosings(List<FundClosing> fundClosings) {
        this.fundClosings = fundClosings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateNearestClosing() {
        return dateNearestClosing;
    }

    public void setDateNearestClosing(String dateNearestClosing) {
        this.dateNearestClosing = dateNearestClosing;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
}
