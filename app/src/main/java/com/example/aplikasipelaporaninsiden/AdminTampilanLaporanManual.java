package com.example.aplikasipelaporaninsiden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AdminTampilanLaporanManual extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView rvLaporan;
    private LaporanAdapter laporanAdapter;
    private List<Laporan> laporanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tampilan_laporan_manual);

        drawerLayout = findViewById(R.id.drawer_layout);
        ImageView btnSidebar = findViewById(R.id.btnSidebar);
        LinearLayout btnUpload = findViewById(R.id.btnUpload);

        btnSidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTampilanLaporanManual.this, AdminTampilanHalamanVideo.class);
                startActivity(intent);
            }
        });

        SidebarHelper.setupSidebar(this, R.id.btnLaporanManual);

        // Setup RecyclerView
        rvLaporan = findViewById(R.id.rvLaporan);
        rvLaporan.setLayoutManager(new LinearLayoutManager(this));

        // Create dummy data
        laporanList = new ArrayList<>();
        laporanList.add(new Laporan("Evakuasi Hewan", "Tolong pak, hewan peliharaan saya terjebak di Ruang loteng rumah saya...", "Laporan Masuk"));
        laporanList.add(new Laporan("Pohon Tumbang", "Pohon besar di depan rumah tumbang dan menutupi jalan utama.", "Laporan Masuk"));
        laporanList.add(new Laporan("Kebakaran Ringan", "Terjadi kebakaran kecil di dapur akibat korsleting listrik.", "Laporan Masuk"));

        // Set up the adapter
        laporanAdapter = new LaporanAdapter(this, laporanList);
        rvLaporan.setAdapter(laporanAdapter);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}