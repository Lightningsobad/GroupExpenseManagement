package com.exercise.groupexpensemanagement.ui.member_add;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.exercise.groupexpensemanagement.data.api.ApiService;
import com.exercise.groupexpensemanagement.databinding.FragmentAddMembersBinding;
import com.exercise.groupexpensemanagement.ui.main.MainScreenViewModel;
import com.exercise.groupexpensemanagement.ui.member_edit.EditMemberFragment;
import com.exercise.groupexpensemanagement.util.DateUtils;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMemberFragment extends Fragment {

    FragmentAddMembersBinding binding;

    MainScreenViewModel mainScreenViewModel;

    private int groupId;
    private ActivityResultLauncher<String> pickImageLaucher;

    private String selectUriImage ="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddMembersBinding.inflate(inflater, container, false);
        mainScreenViewModel = new ViewModelProvider(requireActivity()).get(MainScreenViewModel.class);
        mainScreenViewModel.getGroup().observe(getViewLifecycleOwner(), group -> {
            groupId = group.getId();
        });
        setUpCalendarSelected();
        clickSelecteImage();
        clickAddButton();
        return binding.getRoot();
    }

    private void clickAddButton() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtName.getText().toString();
                int yob = Integer.parseInt(binding.edtYob.getText().toString());
                String address = binding.edtAddress.getText().toString();
                String date = DateUtils.convertToServerDate(binding.edtDate.getText().toString());
                String phone = binding.edtPhone.getText().toString();
                String email = binding.edtEmail.getText().toString();
                String image;
                if(selectUriImage != null){
                    image = selectUriImage;
                } else{
                    image = null;
                }
                ApiService.apiService.addMember(groupId, name, yob, address, date, image, phone, email).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful() && response.body() != null){
                            boolean isSuccess = response.body();
                            if(isSuccess){
                                Toast.makeText(getContext(), "Add member successfully!", Toast.LENGTH_SHORT).show();
                                mainScreenViewModel.updateGroup(groupId);
                                NavHostFragment.findNavController(AddMemberFragment.this).popBackStack();
                            } else{
                                Toast.makeText(getContext(), "Add member failed!", Toast.LENGTH_SHORT).show();
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

    private void clickSelecteImage() {
        pickImageLaucher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri ->{
            if(uri != null){
                selectUriImage = uri.toString();
                Glide.with(AddMemberFragment.this).load(uri).circleCrop().into(binding.imgAvatar);
            }
        });

        binding.btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageLaucher.launch("image/*");
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
}
