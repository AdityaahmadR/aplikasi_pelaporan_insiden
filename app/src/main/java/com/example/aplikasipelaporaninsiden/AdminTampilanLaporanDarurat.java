package com.example.aplikasipelaporaninsiden;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class AdminTampilanLaporanDarurat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hanya menghubungkan ke tampilan XML saja
        setContentView(R.layout.activity_admin_tampilan_laporan_darurat);
    }
}