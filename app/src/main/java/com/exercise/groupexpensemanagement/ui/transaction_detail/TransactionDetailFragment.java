package com.exercise.groupexpensemanagement.ui.transaction_detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.data.model.Expense;
import com.exercise.groupexpensemanagement.data.model.ExpenseGroupByDate;
import com.exercise.groupexpensemanagement.databinding.FragmentDetailTransactionBinding;
import com.exercise.groupexpensemanagement.ui.edit_expense.EditExpenseFragment;
import com.exercise.groupexpensemanagement.ui.main.MainScreenViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionDetailFragment extends Fragment {

    FragmentDetailTransactionBinding binding;
    private List<Expense> allExpenses = new ArrayList<>();
    private ExpenseDayAdapter adapter;

    private MainScreenViewModel mainScreenViewModel;
    private int currentMonth;
    private int currentYear;

    private int groupId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailTransactionBinding.inflate(inflater, container, false);
        mainScreenViewModel = new ViewModelProvider(requireActivity()).get(MainScreenViewModel.class);
        mainScreenViewModel.getGroup().observe(getViewLifecycleOwner(), group -> {
            groupId = group.getId();
        });
        setUpRecycleView();
        loadAllExpense();
        setUpMonthChangeListener();

        return binding.getRoot();
    }

    private void setUpMonthChangeListener() {
        binding.calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // month trả về 0–11 → +1
            int selectedMonth = month + 1;
            int selectedYear = year;

            currentMonth = selectedMonth;
            currentYear = selectedYear;

            updateList();   // gọi lại để lọc danh sách theo tháng mới
        });
    }

    private void loadAllExpense() {
        mainScreenViewModel.getGroup().observe(getViewLifecycleOwner(), group -> {
            allExpenses = group.getExpenses();
            Calendar c = Calendar.getInstance();
            currentMonth = c.get(Calendar.MONTH) + 1;
            currentYear = c.get(Calendar.YEAR);
            updateList();
        });
    }

    private void updateList() {
        List<ExpenseGroupByDate> result = groupExpenseByMonth(allExpenses, currentMonth, currentYear);
        adapter.updateList(result);
    }

    private void setUpRecycleView() {
        adapter = new ExpenseDayAdapter(new ArrayList<>(), expense -> {
            if (expense != null)
                openEditExpenseFragment(expense);
        });
        binding.listOfItems.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.listOfItems.setAdapter(adapter);
    }

    private void openEditExpenseFragment(Expense expense) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("expense_data", expense);

        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.action_detail_to_edit, bundle);
    }





    private List<ExpenseGroupByDate> groupExpenseByMonth(List<Expense> all, int month, int year) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // 1. Lọc theo tháng
        List<Expense> filtered = all.stream()
                .filter(e -> {
                    Calendar c = Calendar.getInstance();
                    c.setTime(e.getDateBegin());
                    return (c.get(Calendar.MONTH) + 1 == month &&
                            c.get(Calendar.YEAR) == year);
                })
                .collect(Collectors.toList());

        // 2. Gom theo ngày
        Map<String, List<Expense>> grouped = filtered.stream()
                .collect(Collectors.groupingBy(e -> sdf.format(e.getDateBegin())));

        // 3. Convert Map → List<ExpenseGroupByDate>
        List<ExpenseGroupByDate> result = new ArrayList<>();
        for (String day : grouped.keySet()) {
            Date date;
            try {
                date = sdf.parse(day);
            } catch (Exception ex) {
                continue;
            }
            result.add(new ExpenseGroupByDate(date, grouped.get(day)));
        }

        // Sort giảm dần theo ngày (mới nhất → cũ)
        result.sort((a, b) -> b.getDate().compareTo(a.getDate()));

        return result;
    }




}


