package com.exercise.groupexpensemanagement.ui.member_info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exercise.groupexpensemanagement.databinding.FragmentDetailMemberInfoBinding;

public class MemberInfoFragment extends Fragment {
    FragmentDetailMemberInfoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailMemberInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
