package com.exercise.groupexpensemanagement.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.databinding.FragmentSignInBinding;
import com.exercise.groupexpensemanagement.ui.groupcreate.CreateAGroupActivity;
import com.exercise.groupexpensemanagement.ui.main.MainScreenActivity;

public class SignInFragment extends Fragment {
    FragmentSignInBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentSignInBinding.inflate(getLayoutInflater(), container, false);


        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SignInFragment.this).navigate(R.id.action_sign_up_to_sign_in);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), CreateAGroupActivity.class);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
}
