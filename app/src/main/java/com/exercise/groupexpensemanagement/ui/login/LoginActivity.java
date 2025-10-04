package com.exercise.groupexpensemanagement.ui.login;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.exercise.groupexpensemanagement.databinding.LoginActivityBinding;

public class LoginActivity extends AppCompatActivity {

    LoginActivityBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
