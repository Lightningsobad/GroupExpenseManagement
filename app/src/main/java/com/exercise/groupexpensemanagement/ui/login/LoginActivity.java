package com.exercise.groupexpensemanagement.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.databinding.ActivityLoginBinding;
import com.exercise.groupexpensemanagement.ui.main.MainScreenActivity;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
