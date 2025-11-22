package com.example.aplikasipelaporaninsiden; // Pastikan package ini sesuai

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class AdminTampilanAwal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kita cukup panggil layout XML Login yang sudah jadi tadi
        setContentView(R.layout.activity_admin_tampilan_awal);
    }
}