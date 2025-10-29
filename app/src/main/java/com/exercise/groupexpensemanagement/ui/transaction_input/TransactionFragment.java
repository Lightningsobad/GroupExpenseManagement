package com.exercise.groupexpensemanagement.ui.transaction_input;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.databinding.FragmentInputTransactionBinding;
import com.exercise.groupexpensemanagement.ui.input_expense.ExpenseFragment;
import com.exercise.groupexpensemanagement.ui.input_fund.FundFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TransactionFragment extends Fragment {
    FragmentInputTransactionBinding binding;

    private ViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInputTransactionBinding.inflate(inflater, container, false);

        adapter = new ViewPagerAdapter(getActivity());
        binding.viewPager.setAdapter(adapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, i) -> {
            switch (i) {
                case 0:
                    tab.setText(R.string.expense);
                    break;
                case 1:
                    tab.setText(R.string.fund);
                    break;
            }
        }).attach();



        return binding.getRoot();
    }
}
