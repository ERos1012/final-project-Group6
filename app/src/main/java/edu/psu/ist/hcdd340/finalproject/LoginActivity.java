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
    private static final String TAG = "ACTIVITY_LOGIN";
    public static final String EMAIL_KEY = "EMAIL";
    public static final String PASSWORD_KEY = "PASSWORD";
    public static final String SHARED_PREF_NAME = "USERNAME";
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.button_register) {
                    registerUser();
                    Button button = findViewById(R.id.button_register);
                    Snackbar.make(button,
                            "Registered!",
                            Snackbar.LENGTH_LONG).show();
                }
                else if (id == R.id.button_login) {
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
            }
        });
    }

    String getInputFromEditText(int id) {
        EditText v = findViewById(id);
        return v.getText().toString();
    }
    void registerUser() {
        String email = getInputFromEditText(R.id.editTextEmail);
        String password = getInputFromEditText(R.id.editTextPassword);
        Log.d(TAG, "Email: " + email + ", Password: " + password);
        saveUserInformation(email, password);
    }
    void saveUserInformation(String email, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL_KEY, email);
        editor.putString(PASSWORD_KEY, password);
        editor.apply();
    }
    
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.length() >= 6;
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.length() <= 16;
    }
}
