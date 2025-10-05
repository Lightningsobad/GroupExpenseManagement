package com.exercise.groupexpensemanagement.ui.dashboad;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exercise.groupexpensemanagement.databinding.DashboadFragmentBinding;

public class DashboardFragment extends Fragment {
    DashboadFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DashboadFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
