package com.exercise.groupexpensemanagement.ui.groupcreate;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.exercise.groupexpensemanagement.data.model.User;
import com.exercise.groupexpensemanagement.databinding.ActivityCreateANewGroupBinding;

public class CreateAGroupActivity extends AppCompatActivity {
    ActivityCreateANewGroupBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateANewGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CreateAGroupViewModel createAGroupViewModel = new ViewModelProvider(this).get(CreateAGroupViewModel.class);
        Bundle bundleReceive = getIntent().getExtras();
        if(bundleReceive != null){
            User user = (User) bundleReceive.get("user");
            createAGroupViewModel.setUser(user);
        }
    }
}
