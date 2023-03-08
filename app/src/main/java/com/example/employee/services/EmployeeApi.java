package com.example.employee.services;
import com.example.employee.models.Employee;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface EmployeeApi {
    @GET("read.php")
    Call<List<Employee>> getEmployees();
    @GET("single_read.php")
    Call<Employee> getEmployee(@Query("id") String id);
}
