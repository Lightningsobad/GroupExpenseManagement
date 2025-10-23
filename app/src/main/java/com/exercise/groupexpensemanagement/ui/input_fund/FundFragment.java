package com.exercise.groupexpensemanagement.ui.input_fund;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exercise.groupexpensemanagement.databinding.FragmentFundBinding;

public class FundFragment extends Fragment {
    FragmentFundBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFundBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
