package com.exercise.groupexpensemanagement.ui.member_edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exercise.groupexpensemanagement.databinding.FragmentEditMemberBinding;

public class EditMemberFragment extends Fragment {
    FragmentEditMemberBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEditMemberBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
