package com.example.employee;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.employee.models.Employee;
import com.example.employee.services.EmployeeApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ShowByIdActivity extends AppCompatActivity {
    private EditText mEmployeeIdEditText;
    private TextView mIdTextView;
    private TextView mNameTextView;
    private TextView mEmailTextView;
    private TextView mAgeTextView;
    private TextView mDesignationTextView;
    private TextView mCreatedTextView;
    private TableLayout mTableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_by_id);

        mEmployeeIdEditText = findViewById(R.id.employee_id);
        Button mGetEmployeeButton = findViewById(R.id.get_employee_button);

        mIdTextView = findViewById(R.id.idValue);
        mNameTextView = findViewById(R.id.nameValue);
        mEmailTextView = findViewById(R.id.emailValue);
        mAgeTextView = findViewById(R.id.ageValue);
        mDesignationTextView = findViewById(R.id.designationValue);
        mCreatedTextView = findViewById(R.id.createdValue);

        mTableLayout = findViewById(R.id.tableLayout);

        mGetEmployeeButton.setOnClickListener(view -> {
            String employeeId = mEmployeeIdEditText.getText().toString();
            if (employeeId.isEmpty()) {
                Toast.makeText(ShowByIdActivity.this, "Please enter employee ID", Toast.LENGTH_SHORT).show();
            } else {
                getEmployeeById(employeeId);
            }
        });
    }
    private void getEmployeeById(String employeeId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trifrnd.in/api28feb/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeApi apiService = retrofit.create(EmployeeApi.class);
        Call<Employee> call = apiService.getEmployee(employeeId);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(@NonNull Call<Employee> call, @NonNull Response<Employee> response) {
                if (response.isSuccessful()) {
                    Employee employee = response.body();
                    displayEmployeeData(employee);
                } else {
                    Toast.makeText(ShowByIdActivity.this, "Failed to get employee data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<Employee> call, @NonNull Throwable t) {
                Toast.makeText(ShowByIdActivity.this, "Failed to get employee data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void displayEmployeeData(Employee employee) {
        if (employee != null) {
            mIdTextView.setText(Integer.toString(employee.getId()));
            mNameTextView.setText(employee.getName());
            mEmailTextView.setText(employee.getEmail());
            mAgeTextView.setText(Integer.toString(employee.getAge()));
            mDesignationTextView.setText(employee.getDesignation());
            mCreatedTextView.setText(employee.getCreated());
            mTableLayout.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(ShowByIdActivity.this, "Employee data not found", Toast.LENGTH_SHORT).show();
        }
    }
}