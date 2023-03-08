package com.example.employee.services;

import com.example.employee.models.Record;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RecordApiService {
    @POST("create.php")

    Call<Void> addRecord(@Body Record recordToAdd);
}
