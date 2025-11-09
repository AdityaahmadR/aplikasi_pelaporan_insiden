package com.example.aplikasipelaporaninsiden;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView registerLink = findViewById(R.id.register);
        Button loginButton = findViewById(R.id.loginButton);

        registerLink.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("AplikasiPelaporanInsiden", MODE_PRIVATE);
            // Cek apakah waktu login pertama sudah tersimpan
            if (!prefs.contains("welcomeNotificationTimestamp")) {
                // Jika belum, simpan waktu saat ini sebagai waktu login pertama
                SharedPreferences.Editor editor = prefs.edit();
                editor.putLong("welcomeNotificationTimestamp", System.currentTimeMillis());
                editor.apply();
            }

            // Arahkan ke halaman utama setelah login
            Intent intent = new Intent(MainActivity.this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
