package com.exercise.groupexpensemanagement.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    @SerializedName("nhoMs")
    private List<Group> nhoMs;
    @SerializedName("maNguoiDung")
    private int maNguoiDung;
    @SerializedName("tenDangNhap")
    private String tenDangNhap;
    @SerializedName("matKhau")
    private String matKhau;
    @SerializedName("email")
    private String email;
    @SerializedName("anhDaiDien")
    private String anhDaiDien;

    public List<Group> getNhoMs() {
        return nhoMs;
    }

    public void setNhoMs(List<Group> nhoMs) {
        this.nhoMs = nhoMs;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }
}
