package com.exercise.groupexpensemanagement.data.api;

import com.exercise.groupexpensemanagement.data.model.Group;
import com.exercise.groupexpensemanagement.data.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface ApiService {

    //link API: http://192.168.50.100/qlct/api/

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.165/qlct/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("user")
    Call<List<User>> getListUser();

    @GET("login")
    Call<Boolean> isLoginSuccessful(@Query("username") String username, @Query("password") String password);

    @GET("group/checkCreated")
    Call<Boolean> isGroupCreated(@Query("id") String id);

    @GET("getUser")
    Call<User> getUser(@Query("username") String username, @Query("password") String password);

    @GET("group/getInfo")
    Call<Group> getGroup(@Query("id") int id);

    @POST("expense/add")
    Call<Boolean> addExpense(@Query("groupId") int groupId,
                             @Query("name") String name, @Query("date") String date,
                             @Query("money") int money, @Query("note") String note);

    @PUT("expense/update")
    Call<Boolean> updateExpense(@Query("id") int id,
                             @Query("name") String name, @Query("date") String date,
                             @Query("money") int money, @Query("note") String note);

    @DELETE("expense/delete")
    Call<Boolean> deleteExpense(@Query("id") int id);


    @POST("fund/add")
    Call<Boolean> addFundClosing(@Query("fundId") int fundId,
                             @Query("name") String name, @Query("date") String date,
                             @Query("money") int money, @Query("note") String note);
}
