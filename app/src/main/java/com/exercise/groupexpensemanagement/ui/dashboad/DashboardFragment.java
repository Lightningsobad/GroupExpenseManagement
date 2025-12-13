package com.exercise.groupexpensemanagement.ui.dashboad;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;


import com.exercise.groupexpensemanagement.data.api.ApiService;
import com.exercise.groupexpensemanagement.data.model.Expense;
import com.exercise.groupexpensemanagement.data.model.FundClosing;
import com.exercise.groupexpensemanagement.data.model.Group;
import com.exercise.groupexpensemanagement.data.model.User;
import com.exercise.groupexpensemanagement.databinding.FragmentDashboardBinding;
import com.exercise.groupexpensemanagement.ui.main.MainScreenActivity;
import com.exercise.groupexpensemanagement.ui.main.MainScreenViewModel;
import com.exercise.groupexpensemanagement.util.DateUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    FragmentDashboardBinding binding;
    private MainScreenViewModel mainScreenViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        mainScreenViewModel = new ViewModelProvider(requireActivity()).get(MainScreenViewModel.class);
        updateDashboardInfomation();
        setupPieChart(binding.pieChartExpenseAndFund);
        updatepieChartExpenseAndFund();
        return binding.getRoot();
    }



    private void updateDashboardInfomation() {
        mainScreenViewModel.getGroup().observe(getViewLifecycleOwner(), group -> {
            binding.tvGroupName.setText(group.getName());
            binding.tvTotalMembers.setText(String.valueOf(group.getMembers().size()));
            binding.tvTotalFund.setText(
               group.getFunds().get(0).getFundClosings().isEmpty() ? "-" : String.valueOf(calculateTotalFundRemaining(group)));
            binding.tvDateClosing.setText(group.getFunds().get(0).getFundClosings().isEmpty() ? "-" : DateUtils.format(getNearestClosingDate(group.getFunds().get(0).getFundClosings())));
        });
    }

    private int calculateTotalFund(Group group) {
        if(group.getFunds().get(0).getFundClosings().isEmpty())
            return 0;
        int totalFund = 0;
        for (int i = 0; i < group.getFunds().get(0).getFundClosings().size(); i++){
            totalFund += group.getFunds().get(0).getFundClosings().get(i).getMoneyForEachPerson() * group.getMembers().size();
        }
        return totalFund;
    }

    private int calculateTotalFundRemaining(Group group) {
       return calculateTotalFund(group) - calculateTotalExpense(group);
    }

    public Date getNearestClosingDate(List<FundClosing> list) {
        Date today = new Date();
        return list.stream()
                .map(FundClosing::getDate)
                .filter(d -> d != null && !d.after(today))
                .max(Date::compareTo)
                .orElse(null);
    }

    private int calculateTotalExpense(Group group) {
        if(group.getExpenses().isEmpty())
            return 0;
        int totalExpense = group.getExpenses().stream().mapToInt(Expense::getMoney).sum();
        return totalExpense;
    }


    private void updatepieChartExpenseAndFund() {
        mainScreenViewModel.getGroup().observe(getViewLifecycleOwner(), group -> {
            if (group.getExpenses().isEmpty() && group.getFunds().get(0).getFundClosings().isEmpty()) {
                binding.pieChartExpenseAndFund.clear();
                binding.pieChartExpenseAndFund.setNoDataText("No data for this");
                binding.pieChartExpenseAndFund.invalidate();
                return;
            }
            Map<String, Integer> categoryMap = new HashMap<>();
            categoryMap.put("Expense", calculateTotalExpense(group));
            categoryMap.put("Fund remaining", calculateTotalFund(group) - calculateTotalExpense(group));

            ArrayList<PieEntry> entries = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : categoryMap.entrySet()) {
                entries.add(new PieEntry(entry.getValue(), entry.getKey()));
            }

            PieDataSet dataSet = new PieDataSet(entries, null);
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            dataSet.setValueFormatter(new PercentFormatter(binding.pieChartExpenseAndFund));
            dataSet.setValueTextSize(14f);
            dataSet.setValueTextColor(Color.WHITE);

            PieData data = new PieData(dataSet);
            binding.pieChartExpenseAndFund.setData(data);
        });

    }




    private void setupPieChart(PieChart pieChart) {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleRadius(55f);
        pieChart.setEntryLabelTextSize(16f);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.animateY(1000);
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(16f);
        legend.setTextColor(Color.BLACK);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
    }


}
