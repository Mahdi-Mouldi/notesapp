package com.example.appnotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String email = null;
        if (bundle != null) {
            email = bundle.getString("email");
        }

        TextView textView = findViewById(R.id.welcomeText);
        if (textView != null) {
            if (email != null && !email.isEmpty()) {
                String username = email.split("@")[0];
                textView.setText("Welcome, " + username + "!");
            } else {
                textView.setText("Welcome!");
            }
        }

        // ====== Redirection automatique après 3 secondes ======
        int delayMillis = 3000; // 3 secondes
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SuccessActivity.this, TasksActiviy.class);
                startActivity(intent);
                finish(); // ferme SuccessActivity
            }
        }, delayMillis);
    }
}
