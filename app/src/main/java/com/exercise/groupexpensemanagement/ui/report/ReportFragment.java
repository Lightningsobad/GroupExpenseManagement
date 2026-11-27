package com.exercise.groupexpensemanagement.ui.report;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.exercise.groupexpensemanagement.data.model.Expense;
import com.exercise.groupexpensemanagement.databinding.FragmentDashboardBinding;
import com.exercise.groupexpensemanagement.databinding.FragmentReportBinding;
import com.exercise.groupexpensemanagement.ui.main.MainScreenViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportFragment extends Fragment {
    FragmentReportBinding binding;

    MainScreenViewModel mainScreenViewModel;

    private List<Expense> expenses;

    private int selectedYear;
    private int selectedMonth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReportBinding.inflate(inflater, container, false);
        mainScreenViewModel = new ViewModelProvider(requireActivity()).get(MainScreenViewModel.class);
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
        setupPieChart(binding.pieChartExpenseAndFund);
        updateOpenFragment();
        setUpMonthChangeListener();
        setUpExportButton();
        return binding.getRoot();
    }

    private void setUpExportButton() {
        binding.tvReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.set(selectedYear, selectedMonth - 1, 1);

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;

                exportExcel(year, month);
            }
        });
    }

    private void updateOpenFragment(){
        Calendar cal = Calendar.getInstance();
        selectedYear = cal.get(Calendar.YEAR);
        selectedMonth = cal.get(Calendar.MONTH) + 1;
        updatePieChart(selectedYear, selectedMonth);
    }


    private void setUpMonthChangeListener(){
        binding.calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedYear = year;
            selectedMonth = month + 1;
            updatePieChart(selectedYear, selectedMonth);
        });
    }


    private void updatePieChart(int year, int month) {

        mainScreenViewModel.getGroup()
                .observe(getViewLifecycleOwner(), group -> {
                    expenses = group.getExpenses();
                    if (expenses == null || expenses.isEmpty()) {
                        binding.pieChartExpenseAndFund.clear();
                        binding.pieChartExpenseAndFund.setNoDataText("No data available");
                        binding.pieChartExpenseAndFund.invalidate();
                        return;
                    }
                    List<Expense> filtered = new ArrayList<>();
                    Calendar cal = Calendar.getInstance();

                    for (Expense e : expenses) {
                        cal.setTime(e.getDateBegin());

                        int y = cal.get(Calendar.YEAR);
                        int m = cal.get(Calendar.MONTH) + 1;

                        if (y == year && m == month) {
                            filtered.add(e);
                        }
                    }

                    // Nếu tháng đó không có chi tiêu
                    if (filtered.isEmpty()) {
                        binding.pieChartExpenseAndFund.clear();
                        binding.pieChartExpenseAndFund.setNoDataText("No data for this month");
                        binding.pieChartExpenseAndFund.invalidate();
                        binding.tvTotalExpenses.setText(String.format("%,d VNĐ", 0));
                        return;
                    }

                    // Gom nhóm theo category
                    Map<String, Integer> categoryMap = new HashMap<>();
                    for (Expense e : filtered) {
                        String category = e.getName();
                        int amount = e.getMoney();

                        if (!categoryMap.containsKey(category))
                            categoryMap.put(category, amount);
                        else
                            categoryMap.put(category, categoryMap.get(category) + amount);
                    }

                    // Tạo Entry cho PieChart
                    ArrayList<PieEntry> entries = new ArrayList<>();
                    for (Map.Entry<String, Integer> entry : categoryMap.entrySet()) {
                        entries.add(new PieEntry(entry.getValue(), entry.getKey()));
                    }

                    PieDataSet dataSet = new PieDataSet(entries, "");
                    dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    dataSet.setValueTextSize(16f);
                    dataSet.setValueTextColor(Color.WHITE);
                    dataSet.setValueFormatter(new PercentFormatter(binding.pieChartExpenseAndFund));

                    PieData data = new PieData(dataSet);

                    binding.pieChartExpenseAndFund.setData(data);
                    binding.pieChartExpenseAndFund.invalidate();

                    updateTotalExpense(year, month);
                });
    }

    private int calculateTotalExpense(int year, int month) {
        if (expenses == null || expenses.isEmpty()) return 0;

        int total = 0;
        Calendar cal = Calendar.getInstance();

        for (Expense e : expenses) {
            cal.setTime(e.getDateBegin());
            int y = cal.get(Calendar.YEAR);
            int m = cal.get(Calendar.MONTH) + 1;

            if (y == year && m == month) {
                total += e.getMoney();
            }
        }

        return total;
    }

    private void updateTotalExpense(int year, int month) {

        int total = calculateTotalExpense(year, month);
        // Format tiền cho đẹp
        String formatted = String.format("%,d VNĐ", total * -1);
        binding.tvTotalExpenses.setText(formatted);
    }

    private void exportExcel(int year, int month) {

        // 1. Lọc dữ liệu theo tháng
        if (expenses == null || expenses.isEmpty()) {
            Toast.makeText(getContext(), "No data to export!", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Expense> filtered = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        for (Expense e : expenses) {
            cal.setTime(e.getDateBegin());
            int y = cal.get(Calendar.YEAR);
            int m = cal.get(Calendar.MONTH) + 1;

            if (y == year && m == month) {
                filtered.add(e);
            }
        }

        if (filtered.isEmpty()) {
            Toast.makeText(getContext(), "No data in this month!", Toast.LENGTH_SHORT).show();
            return;
        }

        // 2. Gom nhóm category
        Map<String, Integer> categoryMap = new HashMap<>();
        for (Expense e : filtered) {
            String category = e.getName();
            int amount = e.getMoney();
            categoryMap.put(category, categoryMap.getOrDefault(category, 0) + amount);
        }

        // 3. Tạo file Excel
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Month " + month + "-" + year);

            // Tạo dòng tiêu đề
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Category");
            header.createCell(1).setCellValue("Amount (VNĐ)");

            // Thêm dữ liệu category
            int rowIndex = 1;
            for (Map.Entry<String, Integer> entry : categoryMap.entrySet()) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }

            // Tổng tiền cuối bảng
            Row totalRow = sheet.createRow(rowIndex + 1);
            totalRow.createCell(0).setCellValue("Total:");
            totalRow.createCell(1).setCellValue(calculateTotalExpense(year, month));

            // 4. Lưu file vào thư mục Downloads
            String fileName = "ChiTieu_Thang_" + month + "_" + year + ".xlsx";

            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(downloadsDir, fileName);

            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();

            Toast.makeText(getContext(),
                    "Export file successful!\nSave in: Downloads/" + fileName,
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error when exporting!", Toast.LENGTH_SHORT).show();
        }
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
