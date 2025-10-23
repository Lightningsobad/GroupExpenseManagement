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

public class TransactionFragment extends Fragment {
    FragmentInputTransactionBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInputTransactionBinding.inflate(inflater, container, false);

        replaceChildFragment(new ExpenseFragment());

        binding.tabChi.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).withEndAction(() -> {
            binding.tabThu.animate().scaleX(1f).scaleY(1f).setDuration(100);
        }).start();

        binding.tabChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(true);
                replaceChildFragment(new ExpenseFragment());
            }
        });

        binding.tabThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(false);
                replaceChildFragment(new FundFragment());
            }
        });
        return binding.getRoot();
    }

    private void replaceChildFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.child_fragment_container, fragment)
                .commit();
    }

    private void selectTab(boolean isChi) {
        if (isChi) {
            binding.tabChi.setBackgroundResource(R.drawable.tab_selected);
            binding.tabChi.setTextColor(getResources().getColor(android.R.color.white));

            binding.tabThu.setBackgroundResource(R.drawable.tab_unselected);
            binding.tabThu.setTextColor(getResources().getColor(R.color.primary_color));
        } else {
            binding.tabThu.setBackgroundResource(R.drawable.tab_selected);
            binding.tabThu.setTextColor(getResources().getColor(android.R.color.white));

            binding.tabChi.setBackgroundResource(R.drawable.tab_unselected);
            binding.tabChi.setTextColor(getResources().getColor(R.color.primary_color));
        }
    }
}
