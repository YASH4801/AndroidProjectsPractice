package com.app.test_intent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Integer CAPTURE_CODE = 302;

    private Button captureButton;
    private ImageView imageField;

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

        captureButton = findViewById(R.id.capture_btn);
        imageField = findViewById(R.id.image_box);

        captureButton.setOnClickListener(v -> {
            Intent captureIntent = new Intent();
            captureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(captureIntent, CAPTURE_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CAPTURE_CODE) {
            if (data != null && data.getExtras() != null) {
                Bitmap capturedData = (Bitmap) data.getExtras().get("data");
                imageField.setImageBitmap(capturedData);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}