package com.exercise.groupexpensemanagement.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.exercise.groupexpensemanagement.databinding.LoginActivityBinding;
import com.exercise.groupexpensemanagement.ui.main.MainScreenActivity;

public class LoginActivity extends AppCompatActivity {

    LoginActivityBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}
