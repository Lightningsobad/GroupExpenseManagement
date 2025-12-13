package com.exercise.groupexpensemanagement.ui.member_edit;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.data.api.ApiService;
import com.exercise.groupexpensemanagement.data.model.Expense;
import com.exercise.groupexpensemanagement.data.model.Members;
import com.exercise.groupexpensemanagement.databinding.FragmentEditMemberBinding;
import com.exercise.groupexpensemanagement.ui.main.MainScreenViewModel;
import com.exercise.groupexpensemanagement.util.DateUtils;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMemberFragment extends Fragment {
    FragmentEditMemberBinding binding;

    MainScreenViewModel mainScreenViewModel;

    private Members members;
    private ActivityResultLauncher<String> pickImageLaucher;
    private String selectUriImage ="";

    private int groupId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditMemberBinding.inflate(inflater, container, false);
        mainScreenViewModel = new ViewModelProvider(requireActivity()).get(MainScreenViewModel.class);
        mainScreenViewModel.getGroup().observe(getViewLifecycleOwner(), group -> {
            groupId = group.getId();
        });
        if (getArguments() != null) {
           members  = (Members) getArguments().getSerializable("member_data");
        }

        setUpDataBindinng();
        setUpCalendarSelected();
        setUpPickImageLauncher();
        clickSelecteImage();
        clickSubmitButton();
        clickDeleteButton();
        return binding.getRoot();
    }

    private void clickSelecteImage() {
        binding.btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageLaucher.launch("image/*");
            }
        });
    }

    private void setUpPickImageLauncher() {
        pickImageLaucher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri ->{
            if(uri != null){
                selectUriImage = uri.toString();
                Glide.with(getContext()).load(uri).circleCrop().into(binding.imgAvatar);
            }
        });
    }

    private void clickDeleteButton() {
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.deleteMember(members.getId()).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful() && response.body() != null){
                            boolean isSuccess = response.body();
                            if(isSuccess){
                                Toast.makeText(getContext(), "Delete successfully!", Toast.LENGTH_SHORT).show();
                                mainScreenViewModel.updateGroup(groupId);
                                NavHostFragment.findNavController(EditMemberFragment.this).popBackStack();
                            } else{
                                Toast.makeText(getContext(), "Delete failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void clickSubmitButton() {
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtName.getText().toString();
                int yob = Integer.parseInt(binding.edtYob.getText().toString());
                String address = binding.edtAddress.getText().toString();
                String date = DateUtils.convertToServerDate(binding.edtDate.getText().toString());
                String phone = binding.edtPhone.getText().toString();
                String email = binding.edtEmail.getText().toString();
                ApiService.apiService.updateMember(members.getId(), name, yob, address, date,
                        selectUriImage, phone, email).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccessful() && response.body() != null){
                            boolean isSuccess = response.body();
                            if (isSuccess){
                                Toast.makeText(getContext(), "Information edited successfully!", Toast.LENGTH_SHORT).show();
                                mainScreenViewModel.updateGroup(groupId);
                                NavHostFragment.findNavController(EditMemberFragment.this).popBackStack();
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

    private void setUpCalendarSelected(){
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

    private void setUpDataBindinng() {
        binding.edtName.setText(members.getName());
        binding.edtYob.setText(String.valueOf(members.getYob()));
        binding.edtAddress.setText(members.getAddress());
        binding.edtDate.setText(DateUtils.format(members.getEntryDate()));
        binding.edtPhone.setText(members.getPhone());
        binding.edtEmail.setText(members.getEmail());
        if (members.getImage() != null) {
            Glide.with(getContext()).load(Uri.parse(members.getImage())).circleCrop().into(binding.imgAvatar);
        } else {
            binding.imgAvatar.setImageResource(R.drawable.default_avatar);
        }
    }
}
