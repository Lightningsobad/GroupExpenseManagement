package com.exercise.groupexpensemanagement.ui.transaction_detail;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exercise.groupexpensemanagement.databinding.FragmentDetailTransactionBinding;

public class TransactionDetailFragment extends Fragment {

    FragmentDetailTransactionBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailTransactionBinding.inflate(inflater, container, false);

        setUpCalendar();
        return binding.getRoot();
    }

    private void setUpCalendar() {
        binding.calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String daySelected = dayOfMonth + "/" + (month + 1);
                binding.tvDateSelected.setText(daySelected);
            }
        });
    }

}


