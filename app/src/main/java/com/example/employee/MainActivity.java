package com.example.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = findViewById(R.id.add_button);
        Button deleteBtn = findViewById(R.id.delete_button);
        Button updateBtn = findViewById(R.id.update_button);
        Button showAllBtn = findViewById(R.id.show_all_button);
        Button showByIdBtn=findViewById(R.id.show_by_id_button);

        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
            startActivity(intent);
        });

        deleteBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DeleteRecordByIdActivity.class);
            startActivity(intent);
        });

        updateBtn.setOnClickListener(view -> {
            // Code to handle update button click
        });

        showAllBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ShowRecordsActivity.class);
            startActivity(intent);
        });
        showByIdBtn.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,ShowByIdActivity.class);
            startActivity(intent);
        });
    }
}
