package com.example.aplikasipelaporaninsiden;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class edukasiTab extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi_tab);

        RecyclerView recyclerView = findViewById(R.id.edukasi_recycler_view);
        TextView noVideoText = findViewById(R.id.no_video_text);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // Logika untuk menampilkan/menyembunyikan video
        ArrayList<Edukasi> edukasiList = new ArrayList<>();
        if (edukasiList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            noVideoText.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            noVideoText.setVisibility(View.GONE);
        }

        // Menangani klik pada bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.navigation_edukasi) {
                    // Sudah di halaman ini
                    return true;
                } else if (itemId == R.id.navigation_laporan) {
                    Intent intent = new Intent(getApplicationContext(), History_Laporan.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.navigation_profil) {
                    // Ganti ProfilActivity.class dengan nama activity profil Anda
                    // Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                    // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    // startActivity(intent);
                    // overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.navigation_edukasi);
    }
}
