package com.app.loginsignuprealtime;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.loginsignuprealtime.DBModel.LocalDB;
import com.app.loginsignuprealtime.DBModel.RoomImplementation;
import com.app.loginsignuprealtime.DBModel.entities.User;

public class SignUp extends AppCompatActivity {

    public static final String STORAGE = "CredsStorage";
    public static final String SAVED_USER_ID = "saved_userId";
    public static final String SAVED_PASSWORD = "saved_password";
    private EditText userName;
    private EditText password;
    private EditText confirmPassword;
    private Button createUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setFormFields();

        createUserBtn.setOnClickListener( v -> {
            if(!password.getText().toString().isEmpty() && !confirmPassword.getText().toString().isEmpty()
                    && password.getText().toString().equalsIgnoreCase(confirmPassword.getText().toString())) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        User userInfo = setUser();
                        LocalDB dbInstance = RoomImplementation.getInstance().getDbInstance();
                        dbInstance.UserDataDAO().createUser(userInfo);
                    }
                    private User setUser() {
                        User userInfo = new User();
                        userInfo.setUserName(userName.getText().toString());
                        userInfo.setPassword(password.getText().toString());
                        return userInfo;
                    }
                });
                SignUp.this.finish();
            }
        });
    }

    private void setFormFields() {
        userName = findViewById(R.id.userId_input);
        password = findViewById(R.id.signup_password_input);
        confirmPassword = findViewById(R.id.confirm_password_input);
        createUserBtn = findViewById(R.id.createUser_btn);
    }
}