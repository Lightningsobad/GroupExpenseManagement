package com.exercise.groupexpensemanagement.ui.member;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exercise.groupexpensemanagement.databinding.FragmentDashboardBinding;
import com.exercise.groupexpensemanagement.databinding.FragmentMemberBinding;


public class MemberFragment extends Fragment {
    FragmentMemberBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMemberBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
