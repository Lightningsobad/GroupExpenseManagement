package com.exercise.groupexpensemanagement.ui.member_add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exercise.groupexpensemanagement.databinding.FragmentAddMembersBinding;

public class AddMemberFragment extends Fragment {

    FragmentAddMembersBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddMembersBinding.inflate(inflater, container, false);




        return binding.getRoot();
    }
}
