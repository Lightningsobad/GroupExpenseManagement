package com.exercise.groupexpensemanagement.ui.transaction_detail;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.data.model.Expense;
import com.exercise.groupexpensemanagement.data.model.TransactionItem;

import java.util.List;

public class ExpenseItemAdapter extends RecyclerView.Adapter<ExpenseItemAdapter.ViewHolder> {

    private List<TransactionItem> list;
    private OnExpenseClickListener listener;

    public interface OnExpenseClickListener {
        void onClick(TransactionItem expense);
    }

    public ExpenseItemAdapter(List<TransactionItem> list, OnExpenseClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_expense_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransactionItem e = list.get(position);
        holder.tvName.setText(e.getTitle());
        int money = e.getAmount();
        holder.tvMoney.setText(String.format("%,d", money));

        holder.tvMoney.setTextColor(
                money >= 0 ? Color.BLUE : Color.RED
        );

        holder.itemView.setOnClickListener(v -> {
            listener.onClick(e);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvMoney;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCategory);
            tvMoney = itemView.findViewById(R.id.tvAmount);
        }
    }
}

