package com.example.employee;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.employee.models.Employee;
import com.example.employee.services.EmployeeApi;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowRecordsActivity extends AppCompatActivity {
    private final List<Employee> employees = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_records);
        //id,name,email,int age,designation,created
        int idWidth = 30;
        int nameWidth = 80;
        int emailWidth = 50;
        int ageWidth = 40;
        int designationWidth = 70;
        int createdWidth = 90;

        TableLayout employeetable = findViewById(R.id.employee_table);

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trifrnd.in/api28feb/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the API service
        EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);

        // Make a network request to get the employees
        Call<List<Employee>> call = employeeApi.getEmployees();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(@NonNull Call<List<Employee>> call, @NonNull Response<List<Employee>> response) {
                if (!response.isSuccessful()) {
                    // Handle the error
                    return;
                }

                // Add each employee to the list
                assert response.body() != null;
                employees.addAll(response.body());

                // Add each employee to the table as a new row
                for (Employee employee : employees) {
                    TableRow row = new TableRow(ShowRecordsActivity.this);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));

                    // Set the divider drawable for the row
                    row.setDividerDrawable(ContextCompat.getDrawable(ShowRecordsActivity.this, R.drawable.table_row_divider));
                    row.setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);

                    //id,name,email,int age,designation,created
                    TextView idTextView = new TextView(ShowRecordsActivity.this);
                    idTextView.setText((Integer.toString(employee.getId())));
                    idTextView.setPadding(10, 10, 10, 10);
                    idTextView.setWidth(idWidth);
                    row.addView(idTextView);

                    TextView nameTextView = new TextView(ShowRecordsActivity.this);
                    nameTextView.setText(employee.getName());
                    nameTextView.setPadding(10, 10, 10, 10);
                    nameTextView.setWidth(nameWidth);
                    row.addView(nameTextView);

                    TextView emailTextView = new TextView(ShowRecordsActivity.this);
                    emailTextView.setText(employee.getEmail());
                    emailTextView.setPadding(10, 10, 10, 10);
                    emailTextView.setWidth(emailWidth);
                    row.addView(emailTextView);

                    TextView ageTextView = new TextView(ShowRecordsActivity.this);
                    ageTextView.setText((Integer.toString(employee.getAge())));
                    ageTextView.setPadding(10, 10, 10, 10);
                    ageTextView.setWidth(ageWidth);
                    row.addView(ageTextView);

                    TextView designationTextView = new TextView(ShowRecordsActivity.this);
                    designationTextView.setText(employee.getDesignation());
                    designationTextView.setPadding(10, 10, 10, 10);
                    designationTextView.setWidth(designationWidth);
                    row.addView(designationTextView);

                    TextView createdTextView = new TextView(ShowRecordsActivity.this);
                    createdTextView.setText(employee.getCreated());
                    createdTextView.setPadding(10, 10, 10, 10);
                    createdTextView.setWidth(createdWidth);
                    row.addView(createdTextView);

                    employeetable.addView(row);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
            }
        });

    }

}

