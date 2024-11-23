package com.testing.helloworld;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        var firstName = getIntent().getStringExtra(MainActivity.FIRST_NAME);
        var lastName = getIntent().getStringExtra(MainActivity.LAST_NAME);
        var email = getIntent().getStringExtra(MainActivity.EMAIL_ADDRESS);
        displayName(firstName, lastName, email);
    }

    @SuppressLint("SetTextI18n")
    private void displayName(String firstName, String lastName, String email) {
        TextView first  = findViewById(R.id.firstName_display_box);
        TextView last  = findViewById(R.id.lastName_display_box);
        TextView emailAddress  = findViewById(R.id.email_display_box);
        first.setText("First Name : " + firstName);
        last.setText("Last Name : " + (lastName != null ? lastName : ""));
        emailAddress.setText("Email : " + email);
        Button exitButton = findViewById(R.id.ext_btn);
        exitButton.setOnClickListener(v -> {
            Intent returnIntent = new Intent(Welcome.this, MainActivity.class);
            startActivity(returnIntent);
        });
    }
}