package com.exercise.groupexpensemanagement.ui.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.exercise.groupexpensemanagement.data.api.ApiService;
import com.exercise.groupexpensemanagement.databinding.FragmentSignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentSignUpBinding.inflate(getLayoutInflater(), container, false);
        setUpIconBack();
        setUpSignUpButton();
        return binding.getRoot();
    }

    private void setUpSignUpButton() {
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.edtUsername.getText().toString();
                String password = binding.edtPassword.getText().toString();
                if (username == null || username.isEmpty() || password == null || password.isEmpty()){
                    Toast.makeText(getContext(), "Please enter username and password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ApiService.apiService.addUser(username, password).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.isSuccessful() && response.body() != null){
                            boolean isSuccess = response.body();
                            if (isSuccess){
                                Toast.makeText(getContext(), "Sign up successfully!", Toast.LENGTH_SHORT).show();
                                NavHostFragment.findNavController(SignUpFragment.this).popBackStack();
                            } else{
                                Toast.makeText(getContext(), "The username already exists.!", Toast.LENGTH_SHORT).show();
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

    private void setUpIconBack() {
        binding.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SignUpFragment.this).popBackStack();
            }
        });
    }
}
