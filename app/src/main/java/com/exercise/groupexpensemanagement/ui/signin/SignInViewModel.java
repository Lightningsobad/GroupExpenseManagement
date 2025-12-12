package com.exercise.groupexpensemanagement.ui.signin;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.exercise.groupexpensemanagement.data.api.ApiService;
import com.exercise.groupexpensemanagement.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInViewModel extends ViewModel {
    private MutableLiveData<Boolean> isLoginSuccessful = new MutableLiveData<>();
    private MutableLiveData<Boolean> isGroupCreated = new MutableLiveData<>();

    private MutableLiveData<User> user = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLoginSuccessful() {
        return isLoginSuccessful;
    }

    public MutableLiveData<Boolean> getIsGroupCreated() {
        return isGroupCreated;
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.postValue(user);
    }


    public void setIsLoginSuccessful(Boolean isLoginSuccessful) {
        this.isLoginSuccessful.postValue(isLoginSuccessful);
    }

    public void setIsGroupCreated(Boolean isGroupCreated) {
        this.isGroupCreated.postValue(isGroupCreated);
    }

    public void checkLogin(String username, String password) {
        ApiService.apiService.getUser(username, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    setUser(user);
                } else {
                    setUser(null);// login sai
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }

    public void checkGroupCreated(String id) {
        ApiService.apiService.isGroupCreated(id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                setIsGroupCreated(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }

        });
    }

    public void resetLoginStatus() {
        isLoginSuccessful.setValue(null);
        isGroupCreated.setValue(null); // Cũng nên reset trạng thái nhóm
    }


}



