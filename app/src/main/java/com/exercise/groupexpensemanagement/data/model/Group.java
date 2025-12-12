package com.exercise.groupexpensemanagement.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Group implements Serializable {
    @SerializedName("quYs")
    private List<Fund> funds;
    @SerializedName("sU_KIEN_CHI_TIEU")
    private List<Expense> expenses;
    @SerializedName("thanH_VIEN")
    private List<Members> members;
    @SerializedName("maNhom")
    private int id;
    @SerializedName("maNguoiDung")
    private int userId;
    @SerializedName("tenNhom")
    private String name;
    @SerializedName("ngayTao")
    private Date dateCreated;
    @SerializedName("soThanhVien")
    private Integer memberCount;
    @SerializedName("tongTienQuy")
    private Double totalFund;

    public Group() {
    }


    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Members> getMembers() {
        return members;
    }

    public void setMembers(List<Members> members) {
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public double getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(double totalFund) {
        this.totalFund = totalFund;
    }
}
