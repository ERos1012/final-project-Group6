package edu.psu.ist.hcdd340.finalproject;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextEmail = findViewById(R.id.editTextEmail);
                String email = editTextEmail.getText().toString();

                EditText editTextPassword = findViewById(R.id.editTextPassword);
                String password = editTextPassword.getText().toString();

                // Validate the username and password
                if (isValidEmail(email) && isValidPassword(password)) {
                    // Login successful
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // Invalid username or password
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.length() >= 6;
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.length() <= 16;
    }
}
