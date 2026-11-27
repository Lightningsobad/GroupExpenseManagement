package com.exercise.groupexpensemanagement.ui.input_expense;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.exercise.groupexpensemanagement.data.api.ApiService;
import com.exercise.groupexpensemanagement.databinding.FragmentExpenseBinding;
import com.exercise.groupexpensemanagement.ui.main.MainScreenViewModel;
import com.exercise.groupexpensemanagement.util.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseFragment extends Fragment {
    FragmentExpenseBinding binding;
    private MainScreenViewModel mainScreenViewModel;

    private int groupId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentExpenseBinding.inflate(inflater, container, false);
        mainScreenViewModel = new ViewModelProvider(requireActivity()).get(MainScreenViewModel.class);
        mainScreenViewModel.getGroup().observe(getViewLifecycleOwner(), group -> {
            groupId = group.getId();
        });
        setUpCalendarSelected();
        setUpCategorySelected();
        clickSubmitButton();
        return binding.getRoot();
    }

    private void setUpCalendarSelected(){
        binding.fieldDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpCalendar();
            }
        });
    }

    private void clickSubmitButton() {
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = DateUtils.convertToServerDate(binding.edtDate.getText().toString());
                String note = binding.edtNote.getText().toString();
                int money = Integer.parseInt(binding.edtMoney.getText().toString());
                String name = binding.tvCategory.getText().toString();
                ApiService.apiService.addExpense(groupId, name, date, money, note).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful() && response.body() != null){
                            boolean isSuccess = response.body();
                            if(isSuccess){
                                Toast.makeText(getContext(), "Add expense successfully!", Toast.LENGTH_SHORT).show();
                                mainScreenViewModel.updateGroup(groupId);
                            } else{
                                Toast.makeText(getContext(), "Add expense failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(getContext(), "API ERROR!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void setUpCategorySelected() {
        for (int i = 0; i < binding.gridCategory.getChildCount(); i++) {
            LinearLayout item = (LinearLayout) binding.gridCategory.getChildAt(i);

            int finalI = i;
            item.setOnClickListener(v -> {

                // Bỏ chọn tất cả ô khác
                for (int j = 0; j < binding.gridCategory.getChildCount(); j++) {
                    binding.gridCategory.getChildAt(j).setSelected(false);
                }

                // Chọn ô hiện tại
                item.setSelected(true);

                // Lấy text trong LinearLayout
                TextView textView = (TextView) item.getChildAt(1);
                String categoryName = textView.getText().toString();
                binding.tvCategory.setText(categoryName);
            });
        }
    }

    private void setUpCalendar(){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(
                getContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = String.format(Locale.getDefault(),
                            "%02d/%02d/%d",
                            selectedDay, selectedMonth + 1, selectedYear); //tháng được đánh số từ 0-11
                    binding.edtDate.setText(selectedDate);
                },
                year, month, day
        );
        dialog.show();
    }
}
