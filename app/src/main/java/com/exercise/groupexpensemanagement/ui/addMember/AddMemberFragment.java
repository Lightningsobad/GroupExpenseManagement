package com.exercise.groupexpensemanagement.ui.addMember;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.databinding.FragmentAddMembersBinding;
import com.exercise.groupexpensemanagement.ui.introductioncreate.IntroductionCreateAGroupFragment;

public class AddMemberFragment extends Fragment {

    FragmentAddMembersBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddMembersBinding.inflate(inflater, container, false);




        return binding.getRoot();
    }
}
