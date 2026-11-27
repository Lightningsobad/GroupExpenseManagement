package com.exercise.groupexpensemanagement.ui.introductioncreate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.databinding.FragmentIntroductionCreateAGroupBinding;
import com.exercise.groupexpensemanagement.ui.signin.SignInFragment;

public class IntroductionCreateAGroupFragment extends Fragment {

    FragmentIntroductionCreateAGroupBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentIntroductionCreateAGroupBinding.inflate(getLayoutInflater(), container, false);

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(IntroductionCreateAGroupFragment.this).navigate(R.id.action_intro_to_detail);
            }
        });
        return binding.getRoot();
    }
}
