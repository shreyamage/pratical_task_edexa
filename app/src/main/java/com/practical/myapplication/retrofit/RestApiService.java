package com.practical.myapplication.retrofit;


import com.practical.myapplication.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApiService {

    @GET("9ed2356404d63b6e718d")
    Call<List<Employee>> getEmployeeData();

}
