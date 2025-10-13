package com.exercise.groupexpensemanagement.ui.creategroup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.databinding.FragmentCreateANewGroupBinding;
import com.exercise.groupexpensemanagement.ui.addMember.AddMemberFragment;
import com.exercise.groupexpensemanagement.ui.groupcreate.CreateAGroupActivity;
import com.exercise.groupexpensemanagement.ui.main.MainScreenActivity;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateAGroupFragment extends Fragment {
    FragmentCreateANewGroupBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateANewGroupBinding.inflate(getLayoutInflater(), container, false);

        binding.fieldDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CreateAGroupFragment.this).navigate(R.id.action_create_to_add_member);
            }
        });
        binding.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), MainScreenActivity.class);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
}
