package com.example.employee.services;

import com.example.employee.models.Employee;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("update.php")
    Call<Employee> updateEmployee(@Body Employee employee);
}
