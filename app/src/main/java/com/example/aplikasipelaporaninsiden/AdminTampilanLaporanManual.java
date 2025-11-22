package com.example.aplikasipelaporaninsiden;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

// Pastikan nama class SAMA dengan nama file (AdminTampilanLaporanManual)
public class AdminTampilanLaporanManual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Menghubungkan ke XML Dashboard
        setContentView(R.layout.activity_admin_tampilan_laporan_manual);

        // TODO: Nanti Backend akan isi logika Sidebar & RecyclerView disini
    }
}