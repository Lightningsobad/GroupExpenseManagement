package com.exercise.groupexpensemanagement.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Members implements Serializable {
    @SerializedName("maThanhVien")
    private int id;
    @SerializedName("maNhom")
    private int idGroup;
    @SerializedName("ten")
    private String name;
    @SerializedName("namSinh")
    private int yob;
    @SerializedName("diaChi")
    private String address;
    @SerializedName("ngayVaoNhom")
    private Date entryDate;

    @SerializedName("anhDaiDien")
    private String image;
    @SerializedName("soDienThoai")
    private String phone;
    @SerializedName("email")
    private String email;

    public Members() {
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

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
