package com.exercise.groupexpensemanagement.ui.edit_fund;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.exercise.groupexpensemanagement.data.api.ApiService;
import com.exercise.groupexpensemanagement.data.model.Expense;
import com.exercise.groupexpensemanagement.data.model.FundClosing;
import com.exercise.groupexpensemanagement.databinding.FragmentEditFundBinding;
import com.exercise.groupexpensemanagement.ui.edit_expense.EditExpenseFragment;
import com.exercise.groupexpensemanagement.ui.main.MainScreenViewModel;
import com.exercise.groupexpensemanagement.util.DateUtils;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFundFragment extends Fragment {

    FragmentEditFundBinding binding;
    private MainScreenViewModel mainScreenViewModel;
    private FundClosing fundClosing;
    private int groupId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditFundBinding.inflate(inflater, container, false);
        mainScreenViewModel = new ViewModelProvider(requireActivity()).get(MainScreenViewModel.class);
        mainScreenViewModel.getGroup().observe(getViewLifecycleOwner(), group -> {
            groupId = group.getId();
        });
        if (getArguments() != null) {
            fundClosing = (FundClosing) getArguments().getSerializable("fund_closing_data");
        }
        setUpDataBinding();
        setUpCalendarSelected();
        clickSubmitButton();
        clickDeleteButton();
        return binding.getRoot();
    }

    private void clickDeleteButton() {
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.deleteFundClosing(fundClosing.getId()).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful() && response.body() != null){
                            boolean isSuccess = response.body();
                            if(isSuccess){
                                Toast.makeText(getContext(), "Delete successfully!", Toast.LENGTH_SHORT).show();
                                mainScreenViewModel.updateGroup(groupId);
                                NavHostFragment.findNavController(EditFundFragment.this).popBackStack();
                            } else{
                                Toast.makeText(getContext(), "Delete failed!", Toast.LENGTH_SHORT).show();
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

    private void clickSubmitButton() {
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = DateUtils.convertToServerDate(binding.edtDate.getText().toString());
                String note = binding.edtNote.getText().toString();
                int money = Integer.parseInt(binding.edtMoney.getText().toString());
                String name = binding.edtName.getText().toString();
                ApiService.apiService.updateFundClosing(fundClosing.getId(), name, date, money, note).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful() && response.body() != null){
                            boolean isSuccess = response.body();
                            if(isSuccess){
                                Toast.makeText(getContext(), "Information edited successfully!", Toast.LENGTH_SHORT).show();
                                mainScreenViewModel.updateGroup(groupId);
                                NavHostFragment.findNavController(EditFundFragment.this).popBackStack();
                            } else{
                                Toast.makeText(getContext(), "Information edited failed!", Toast.LENGTH_SHORT).show();
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

    private void setUpCalendarSelected() {
        binding.fieldDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpCalendar();
            }
        });
    }

    private void setUpCalendar() {
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

    private void setUpDataBinding() {
        binding.edtDate.setText(DateUtils.format(fundClosing.getDate()));
        binding.edtNote.setText(fundClosing.getNote());
        binding.edtMoney.setText(String.valueOf(fundClosing.getMoneyForEachPerson()));
        binding.edtName.setText(fundClosing.getName());
    }
}
