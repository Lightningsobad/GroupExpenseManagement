package com.exercise.groupexpensemanagement.ui.transaction_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.data.model.Expense;
import com.exercise.groupexpensemanagement.data.model.ExpenseGroupByDate;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ExpenseDayAdapter extends RecyclerView.Adapter<ExpenseDayAdapter.DayViewHolder> {

    private List<ExpenseGroupByDate> list;
    private ExpenseItemAdapter.OnExpenseClickListener listener;

    public interface OnExpenseClickListener {
        void onClick(Expense expense);
    }

    public ExpenseDayAdapter(List<ExpenseGroupByDate> list, ExpenseItemAdapter.OnExpenseClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense_day, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        ExpenseGroupByDate data = list.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        holder.tvDate.setText(sdf.format(data.getDate()));

        holder.rvExpenseItems.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        ExpenseItemAdapter adapter = new ExpenseItemAdapter(data.getExpenseList(),  listener);
        holder.rvExpenseItems.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<ExpenseGroupByDate> newList) {
        this.list = newList;
        notifyDataSetChanged();
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        RecyclerView rvExpenseItems;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDateHeader);
            rvExpenseItems = itemView.findViewById(R.id.rvExpenseItems);
        }
    }
}

