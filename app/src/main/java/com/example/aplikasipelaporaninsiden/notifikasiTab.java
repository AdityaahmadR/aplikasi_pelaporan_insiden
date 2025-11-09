package com.example.aplikasipelaporaninsiden;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.concurrent.TimeUnit;

public class notifikasiTab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi_tab);

        Toolbar toolbar = findViewById(R.id.toolbar);
        View notificationPopup = findViewById(R.id.notification_popup);
        Button laporButton = findViewById(R.id.lapor_button_notification);
        TextView timestampText = findViewById(R.id.tv_notification_timestamp);

        // Atur toolbar dan tombol kembali
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(notifikasiTab.this, Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        // Cek apakah ada waktu login pertama yang tersimpan
        SharedPreferences prefs = getSharedPreferences("AplikasiPelaporanInsiden", MODE_PRIVATE);
        long notificationTimestamp = prefs.getLong("welcomeNotificationTimestamp", 0);

        if (notificationTimestamp > 0) {
            notificationPopup.setVisibility(View.VISIBLE);
            timestampText.setText(getFormattedTimeAgo(notificationTimestamp));
        }

        laporButton.setOnClickListener(v -> {
            // Ganti LaporanActivity.class dengan activity pelaporan yang benar
            // Intent intent = new Intent(notifikasiTab.this, LaporanActivity.class);
            // startActivity(intent);
        });
    }

    private String getFormattedTimeAgo(long pastTimeMillis) {
        long currentTimeMillis = System.currentTimeMillis();
        long diffMillis = currentTimeMillis - pastTimeMillis;

        long days = TimeUnit.MILLISECONDS.toDays(diffMillis);
        if (days > 0) {
            return days + " hari lalu";
        }

        long hours = TimeUnit.MILLISECONDS.toHours(diffMillis);
        if (hours > 0) {
            return hours + " jam lalu";
        }

        long minutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis);
        if (minutes > 0) {
            return minutes + " menit lalu";
        }

        return "Baru saja";
    }
}
