package com.exercise.groupexpensemanagement.ui.transaction_input;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.exercise.groupexpensemanagement.ui.input_expense.ExpenseFragment;
import com.exercise.groupexpensemanagement.ui.input_fund.FundFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ExpenseFragment();
            case 1:
                return new FundFragment();
            default:
                return new ExpenseFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    
}
