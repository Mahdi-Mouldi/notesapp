package com.example.appnotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appnotes.db.AuthService;

public class SignupActivity extends AppCompatActivity {
    private EditText emailInput, passwordInput, confirmPasswordInput;
    private Button signupButton;
    private AuthService authService;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        String msg = data.getString("message");
        Log.d(TAG, "*********" + msg);

        authService = new AuthService(this);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.passwordConfirm);
        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString();
                String confirmPassword = confirmPasswordInput.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Passwords didn't match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(SignupActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }


                boolean ok = authService.register(email, password);

                if (!ok) {
                    Toast.makeText(SignupActivity.this, "Registration failed (maybe " +
                                    "email exists)",
                            Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, "onClick: Email = " + email);
                    data.putString("register_email", email);
                    intent.putExtras(data);
                    setResult(RESULT_OK, intent);

                    Toast.makeText(SignupActivity.this, "Registered — please login",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}