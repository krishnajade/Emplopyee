package com.example.employee;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.employee.models.Record;
import com.example.employee.services.RecordApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddRecordActivity extends AppCompatActivity {
    private static final String TAG = "AddRecordActivity";
    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mAgeEditText;
    private EditText mDesignationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        // Initialize views
        mNameEditText = findViewById(R.id.name_edit_text);
        mEmailEditText = findViewById(R.id.email_edit_text);
        mAgeEditText = findViewById(R.id.age_edit_text);
        mDesignationEditText = findViewById(R.id.designation_edit_text);
        Button mAddButton = findViewById(R.id.add_button);

        // Set click listener for add button
        mAddButton.setOnClickListener(v -> {
            // Get user input from EditText views
            String name = mNameEditText.getText().toString().trim();
            String email = mEmailEditText.getText().toString().trim();
            int age = Integer.parseInt(mAgeEditText.getText().toString().trim());
            String designation = mDesignationEditText.getText().toString().trim();

            // Build the Record object to add
            Record recordToAdd = new Record(name, email, age, designation);

            // Make the API call to add the record
            addRecord(recordToAdd);
        });
    }

    private void addRecord(Record recordToAdd) {
        // Create the Gson object
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        // Create the Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trifrnd.in/api28feb/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // Create the RecordApiService instance
        RecordApiService apiService = retrofit.create(RecordApiService.class);

        // Make the API call using the RecordApiService instance
        Call<ResponseBody> call = apiService.addRecord(recordToAdd);

        call.enqueue(new Callback<ResponseBody>() {



//            @Override
//public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
//    if (response.isSuccessful()) {
//        // Handle successful API response
//        Log.d(TAG, "Add record response: " + response.body());
//        finish(); // Finish the activity and go back to main activity
//    } else {
//        // Handle API error
//        Log.e(TAG, "Add record error: " + response.toString());
//        Toast.makeText(AddRecordActivity.this,
//                "Failed to add record: " + response.message(),
//                Toast.LENGTH_SHORT).show();
//    }
//}
@Override
public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
    if (response.isSuccessful()) {
        // Handle successful API response
        Log.d(TAG, "Add record response: " + response.body());
        Toast.makeText(AddRecordActivity.this,
                "Record added successfully!",
                Toast.LENGTH_SHORT).show();
        finish(); // Finish the activity and go back to main activity
    } else {
        // Handle API error
        Log.e(TAG, "Add record error: " + response.toString());
        Toast.makeText(AddRecordActivity.this,
                "Failed to add record: " + response.message(),
                Toast.LENGTH_SHORT).show();
    }
}

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                // Handle network error
                Log.e(TAG, "Add record response body: " + call.request().body());
                Log.e(TAG, "Add record network error: " + t);
                Toast.makeText(AddRecordActivity.this,
                        "Network error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(AddRecordActivity.this,
                        "Network error: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }}


