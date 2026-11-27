package com.exercise.groupexpensemanagement.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.exercise.groupexpensemanagement.data.model.Group;
import com.exercise.groupexpensemanagement.data.model.User;
import com.exercise.groupexpensemanagement.databinding.FragmentWelcomeBinding;
import com.exercise.groupexpensemanagement.ui.creategroup.CreateAGroupFragment;
import com.exercise.groupexpensemanagement.ui.groupcreate.CreateAGroupViewModel;
import com.exercise.groupexpensemanagement.ui.main.MainScreenActivity;

public class WelcomeFragment extends Fragment {

    FragmentWelcomeBinding binding;
    CreateAGroupViewModel createAGroupViewModel;
    private User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        createAGroupViewModel = new ViewModelProvider(requireActivity()).get(CreateAGroupViewModel.class);
        createAGroupViewModel.getUser().observe(getViewLifecycleOwner(), userViewModel -> {
            user = userViewModel;
        });
        setUpButtonStart();
        return binding.getRoot();
    }

    private void setUpButtonStart() {
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainScreenActivity.class);
                Group group = user.getNhoMs().get(0);
                Bundle bundle = new Bundle();
                bundle.putSerializable("group", group);
                intent.putExtras(bundle);
                startActivity(intent);
                requireActivity().finish();
            }
        });

    }
}
