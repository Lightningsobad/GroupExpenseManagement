package com.exercise.groupexpensemanagement.ui.groupcreate;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.exercise.groupexpensemanagement.data.api.ApiService;
import com.exercise.groupexpensemanagement.data.model.Group;
import com.exercise.groupexpensemanagement.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAGroupViewModel extends ViewModel {
    private final MutableLiveData<User> userModel = new MutableLiveData<>();

    public void setUser(User user) {
        userModel.setValue(user);
    }

    public MutableLiveData<User> getUser() {
        return userModel;
    }

    public void updateUser(String username, String password) {
        ApiService.apiService.getUser(username, password).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null)
                    setUser(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("CreateAGroupViewModel", "Error updating user", t);
            }
        });
    }
}
