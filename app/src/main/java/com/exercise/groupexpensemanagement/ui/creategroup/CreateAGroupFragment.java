package com.exercise.groupexpensemanagement.ui.creategroup;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.data.api.ApiService;
import com.exercise.groupexpensemanagement.data.model.Group;
import com.exercise.groupexpensemanagement.data.model.User;
import com.exercise.groupexpensemanagement.databinding.FragmentCreateANewGroupBinding;
import com.exercise.groupexpensemanagement.ui.groupcreate.CreateAGroupViewModel;
import com.exercise.groupexpensemanagement.ui.introductioncreate.IntroductionCreateAGroupFragment;
import com.exercise.groupexpensemanagement.ui.main.MainScreenActivity;
import com.exercise.groupexpensemanagement.util.DateUtils;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAGroupFragment extends Fragment {
    FragmentCreateANewGroupBinding binding;
    CreateAGroupViewModel createAGroupViewModel;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateANewGroupBinding.inflate(getLayoutInflater(), container, false);
        createAGroupViewModel = new ViewModelProvider(requireActivity()).get(CreateAGroupViewModel.class);
        createAGroupViewModel.getUser().observe(getViewLifecycleOwner(), userModel ->{
            user = userModel;
        });
        setUpCalendar();
        setUpButtonComplete();
        return binding.getRoot();
    }



    private void setUpButtonComplete() {
        binding.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtNameGroup.getText().toString();
                String date = DateUtils.convertToServerDate(binding.edtDate.getText().toString());
                ApiService.apiService.createGroup(user.getMaNguoiDung(), name, date).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            boolean isSuccessful = response.body();
                            if (isSuccessful) {
                                Toast.makeText(getActivity(), "Create group successful!", Toast.LENGTH_SHORT).show();
                                createAGroupViewModel.updateUser(user.getTenDangNhap(), user.getMatKhau());
                                NavHostFragment.findNavController(CreateAGroupFragment.this).navigate(R.id.action_create_to_welcome);
                            } else{
                                Toast.makeText(getActivity(), "Create group failed!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(getActivity(), "API ERROR!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }


    private void setUpCalendar() {
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
    }
}
