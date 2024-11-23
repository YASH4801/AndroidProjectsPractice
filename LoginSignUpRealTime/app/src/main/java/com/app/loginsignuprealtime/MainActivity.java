package com.app.loginsignuprealtime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.loginsignuprealtime.DBModel.LocalDB;
import com.app.loginsignuprealtime.DBModel.RoomImplementation;
import com.app.loginsignuprealtime.DBModel.entities.User;

public class MainActivity extends AppCompatActivity {

    private EditText userNameLogin;
    private EditText passwordLogin;

    private Button login_button;
    private Button signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupElementLinks();
        loginSignupRedirection();
        login_button.setOnClickListener(v -> {
            String inputUserId = userNameLogin.getText().toString();
            String inputPassword = passwordLogin.getText().toString();
            new Thread(() -> performLoginFromDB(inputUserId, inputPassword));
        });
    }

    private void performLoginFromDB(String inputUserId, String inputPassword) {
        User dbUserInfo = fetchUserData(inputUserId);
        String db_user_id = dbUserInfo.getUserName();
        String db_password = dbUserInfo.getPassword();

        if ((!inputUserId.isEmpty() && !db_user_id.isEmpty()) && inputUserId.equalsIgnoreCase(db_user_id)){
            if ((!inputPassword.isEmpty() && !db_password.isEmpty()) && inputPassword.equalsIgnoreCase(db_password)){
                System.out.println(inputUserId + " is Input User Id");
                System.out.println(inputPassword + " is Input Password");
                Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_LONG).show();
                System.out.println(inputPassword + " and " + db_password);
            }
        } else {
            System.out.println(inputUserId + " and " + db_user_id);
            Toast.makeText(MainActivity.this, "Wrong User Id", Toast.LENGTH_SHORT).show();
        }
    }

    private User fetchUserData(String inputUserId) {
        LocalDB dbInstance = RoomImplementation.getInstance().getDbInstance();
        return dbInstance.UserDataDAO().getUserData(inputUserId);
    }

    private void loginSignupRedirection() {
        signup_button.setOnClickListener(v -> {
            Intent signup_redirection_intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(signup_redirection_intent);
        });
    }

    private void setupElementLinks() {
        userNameLogin = findViewById(R.id.userId);
        passwordLogin = findViewById(R.id.password);
        login_button = findViewById(R.id.login_btn);
        signup_button = findViewById(R.id.signup_btn);
    }
}