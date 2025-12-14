package com.exercise.groupexpensemanagement.data.model;

import java.util.Date;
import java.util.List;

public class ExpenseGroupByDate {
    private Date date;
    private List<TransactionItem> items;

    public ExpenseGroupByDate(Date date, List<TransactionItem> items) {
        this.date = date;
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public List<TransactionItem> getExpenseList() {
        return items;
    }
}
