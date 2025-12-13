package com.exercise.groupexpensemanagement.ui.member;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.data.model.Members;
import com.exercise.groupexpensemanagement.databinding.FragmentDashboardBinding;
import com.exercise.groupexpensemanagement.databinding.FragmentMemberBinding;
import com.exercise.groupexpensemanagement.ui.main.MainScreenViewModel;

import java.util.List;


public class MemberFragment extends Fragment {
    FragmentMemberBinding binding;

    MainScreenViewModel mainScreenViewModel;
    private int groupId;

    MemberAdapter adapter;

    List<Members> membersList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMemberBinding.inflate(inflater, container, false);
        mainScreenViewModel = new ViewModelProvider(requireActivity()).get(MainScreenViewModel.class);
        mainScreenViewModel.getGroup().observe(getViewLifecycleOwner(), group -> {
            membersList = group.getMembers();
            setUpRecycleView();
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MemberFragment.this).navigate(R.id.action_list_to_add);
            }
        });
        return binding.getRoot();
    }


    private void setUpRecycleView() {
        adapter = new MemberAdapter(membersList, members -> {
            if (members != null)
                openEditMembersInfomation(members);
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    private void openEditMembersInfomation(Members members) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("member_data", members);
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.action_list_to_edit, bundle);
    }
}
