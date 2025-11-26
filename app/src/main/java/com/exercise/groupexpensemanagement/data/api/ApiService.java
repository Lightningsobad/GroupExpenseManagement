package com.exercise.groupexpensemanagement.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public interface ApiService {

    //link API: http://192.168.50.100/qlct/api/

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


}
