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
            .baseUrl("http://192.168.1.197/qlct/api/")
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

    @POST("addUser")
    Call<Boolean> addUser(@Query("username") String username, @Query("password") String password);

    @GET("group/getInfo")
    Call<Group> getGroup(@Query("id") int id);

    @POST("group/add")
    Call<Boolean> createGroup(@Query("userId") int userId, @Query("name") String name, @Query("date") String date);

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

    @PUT("fund/update")
    Call<Boolean> updateFundClosing(@Query("fundId") int id,
                                @Query("name") String name, @Query("date") String date,
                                @Query("money") int money, @Query("note") String note);

    @DELETE("fund/delete")
    Call<Boolean> deleteFundClosing(@Query("id") int id);


    @POST("member/add")
    Call<Boolean> addMember(@Query("groupId") int groupId,
                               @Query("name") String name, @Query("yob") int yob,
                               @Query("address") String address, @Query("date") String date,
                               @Query("image") String image, @Query("phone") String phone,
                               @Query("email") String email);
    @PUT("member/update")
    Call<Boolean> updateMember(@Query("id") int id,
                               @Query("name") String name, @Query("yob") int yob,
                               @Query("address") String address, @Query("date") String date,
                               @Query("image") String image, @Query("phone") String phone,
                               @Query("email") String email);

    @DELETE("member/delete")
    Call<Boolean> deleteMember(@Query("id") int id);
}
