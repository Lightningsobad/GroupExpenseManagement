package com.exercise.groupexpensemanagement.data.model;

import java.util.Date;
import java.util.List;

public class ExpenseGroupByDate {
    private Date date;
    private List<Expense> expenseList;

    public ExpenseGroupByDate(Date date, List<Expense> expenseList) {
        this.date = date;
        this.expenseList = expenseList;
    }

    public Date getDate() {
        return date;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }
}
