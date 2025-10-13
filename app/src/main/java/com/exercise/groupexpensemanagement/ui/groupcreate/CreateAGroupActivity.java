package com.exercise.groupexpensemanagement.ui.groupcreate;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.exercise.groupexpensemanagement.databinding.ActivityCreateANewGroupBinding;

public class CreateAGroupActivity extends AppCompatActivity {
    ActivityCreateANewGroupBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateANewGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
