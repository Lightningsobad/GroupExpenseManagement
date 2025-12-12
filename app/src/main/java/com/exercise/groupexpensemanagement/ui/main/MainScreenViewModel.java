package com.exercise.groupexpensemanagement.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.exercise.groupexpensemanagement.data.api.ApiService;
import com.exercise.groupexpensemanagement.data.model.Group;
import com.exercise.groupexpensemanagement.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenViewModel extends ViewModel {
    private final MutableLiveData<Group> group = new MutableLiveData<>();

    public void setGroup(Group g) {
        group.setValue(g);
    }

    public MutableLiveData<Group> getGroup() {
        return group;
    }


    public void updateGroup(int groupId) {
        ApiService.apiService.getGroup(groupId).enqueue(new Callback<Group>() {

            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                if (response.isSuccessful() && response.body() != null)
                    setGroup(response.body());
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                Log.i("MainScreenViewModel", "Error updating group", t);
            }
        });
    }
}
