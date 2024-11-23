package com.testing.helloworld;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL_ADDRESS = "emailAddress";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSave = findViewById(R.id.saveBtn);

        btnSave.setOnClickListener(v -> {
            Map<String, String> data = saveText(v);
            Intent intent = new Intent(MainActivity.this, Welcome.class);
            intent.putExtra(FIRST_NAME, data.get(FIRST_NAME));
            intent.putExtra(LAST_NAME, data.get(LAST_NAME));
            intent.putExtra(EMAIL_ADDRESS, data.get(EMAIL_ADDRESS));
            startActivity(intent);
        });
    }


    public Map<String, String> saveText(View view){
        EditText name = findViewById(R.id.nameTextBox);
        EditText email = findViewById(R.id.emailTextBox);
        Map<String, String> dataMap = new HashMap<>();
        String firstName;
        String lastName;
        String nameText = name.getText().toString();
        try {
            String[] names = nameText.split(" ");
            firstName = names[0];
            lastName = names[1];
        } catch (Exception e){
            firstName = nameText;
            lastName = "";
        }
        dataMap.put(FIRST_NAME, firstName);
        dataMap.put(LAST_NAME, lastName);
        dataMap.put(EMAIL_ADDRESS, email.getText().toString());
        return dataMap;
    }


}