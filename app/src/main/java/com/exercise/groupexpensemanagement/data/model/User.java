package com.exercise.groupexpensemanagement.data.model;

import java.util.List;

public class User {
    private List<Group> nhoMs;
    private String maNguoiDung;
    private String tenDangNhap;
    private String matKhau;
    private String email;
    private String anhDaiDien;

    public List<Group> getNhoMs() {
        return nhoMs;
    }

    public void setNhoMs(List<Group> nhoMs) {
        this.nhoMs = nhoMs;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
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
