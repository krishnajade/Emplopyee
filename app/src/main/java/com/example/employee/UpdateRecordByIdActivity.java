package com.example.employee;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.employee.models.ApiClient;
import com.example.employee.models.Employee;
import com.example.employee.services.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class UpdateRecordByIdActivity extends AppCompatActivity {
    private EditText idEditText;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText ageEditText;
    private EditText designationEditText;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record_by_id);

        idEditText = findViewById(R.id.id_edit_text);
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        ageEditText = findViewById(R.id.age_edit_text);
        designationEditText = findViewById(R.id.designation_edit_text);
        Button updateButton = findViewById(R.id.update_button);

        apiService = ApiClient.getClient().create(ApiService.class);

        updateButton.setOnClickListener(v -> {
            int id = Integer.parseInt(idEditText.getText().toString());
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            int age = Integer.parseInt(ageEditText.getText().toString());
            String designation = designationEditText.getText().toString();

            Employee employee = new Employee(id, name, email, age, designation);

            Call<Employee> call = apiService.updateEmployee(employee);

            call.enqueue(new Callback<Employee>() {
                @Override
                public void onResponse(@NonNull Call<Employee> call, @NonNull Response<Employee> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        String message = response.body().getServerResponse();
                        Toast.makeText(UpdateRecordByIdActivity.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateRecordByIdActivity.this, "Error updating employee record", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(@NonNull Call<Employee> call, @NonNull Throwable t) {
                    Toast.makeText(UpdateRecordByIdActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
