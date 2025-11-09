package com.example.aplikasipelaporaninsiden;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView loginLink = findViewById(R.id.loginLink);
        Button registerButton = findViewById(R.id.registerButton);

        loginLink.setOnClickListener(v -> {
            // Kembali ke halaman login
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
        });

        registerButton.setOnClickListener(v -> {
            // Arahkan ke halaman login setelah registrasi
            Intent intent = new Intent(Register.this, MainActivity.class);
            // Membersihkan activity stack agar tidak bisa kembali ke halaman register
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
