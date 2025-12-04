package com.example.aplikasipelaporaninsiden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
    private List<Laporan> allLaporanList;
    private List<Laporan> paginatedLaporanList;

    private int currentPage = 0;
    private int itemsPerPage = 5;
    private int totalPages;

    private ImageView ivPrevPage, ivNextPage;
    private TextView tvPageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tampilan_laporan_manual);

        drawerLayout = findViewById(R.id.drawer_layout);
        ImageView btnSidebar = findViewById(R.id.btnSidebar);
        LinearLayout btnUpload = findViewById(R.id.btnUpload);

        ivPrevPage = findViewById(R.id.ivPrevPage);
        ivNextPage = findViewById(R.id.ivNextPage);
        tvPageNumber = findViewById(R.id.tvPageNumber);

        btnSidebar.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        btnUpload.setOnClickListener(v -> {
            Intent intent = new Intent(AdminTampilanLaporanManual.this, AdminTampilanHalamanVideo.class);
            startActivity(intent);
        });

        SidebarHelper.setupSidebar(this, R.id.btnLaporanManual);

        // Setup RecyclerView
        rvLaporan = findViewById(R.id.rvLaporan);
        rvLaporan.setLayoutManager(new LinearLayoutManager(this));

        // Create dummy data
        createDummyData();

        // Pagination logic
        setupPagination();
        updateRecyclerView();
        updatePaginationControls();

        ivPrevPage.setOnClickListener(v -> {
            if (currentPage > 0) {
                currentPage--;
                updateRecyclerView();
                updatePaginationControls();
            }
        });

        ivNextPage.setOnClickListener(v -> {
            if (currentPage < totalPages - 1) {
                currentPage++;
                updateRecyclerView();
                updatePaginationControls();
            }
        });
    }

    private void createDummyData() {
        allLaporanList = new ArrayList<>();
        allLaporanList.add(new Laporan("Evakuasi Hewan", "Tolong pak, hewan peliharaan saya terjebak di Ruang loteng rumah saya, dia tidak bisa keluar dan terjepit", "Laporan Masuk", "Sabtu, 25 Oktober 2025 (1 Menit lalu)", "Adityaa112", "nime7@gmail.com", R.drawable.dummy_image_1));
        allLaporanList.add(new Laporan("Pohon Tumbang", "Pohon besar di depan rumah tumbang dan menutupi jalan utama.", "Laporan Masuk", "Jumat, 24 Oktober 2025 (1 Hari lalu)", "User_Banjir", "banjir@example.com", R.drawable.dummy_image_2));
        allLaporanList.add(new Laporan("Kebakaran Ringan", "Terjadi kebakaran kecil di dapur akibat korsleting listrik.", "Sedang Berlangsung", "Kamis, 23 Oktober 2025 (2 Hari lalu)", "SaksiMata", "saksi@example.com", R.drawable.dummy_image_1));
        allLaporanList.add(new Laporan("Banjir Lokal", "Jalan di depan kompleks tergenang air setinggi 30cm.", "Selesai", "Rabu, 22 Oktober 2025 (3 Hari lalu)", "Korban_Banjir", "korban@example.com", R.drawable.dummy_image_2));
        // Add more dummy data to fill pages
    }

    private void setupPagination() {
        totalPages = (int) Math.ceil((double) allLaporanList.size() / itemsPerPage);
        paginatedLaporanList = new ArrayList<>();
        // Enable navigation for this page
        laporanAdapter = new LaporanAdapter(this, paginatedLaporanList, true);
        rvLaporan.setAdapter(laporanAdapter);
    }

    private void updateRecyclerView() {
        paginatedLaporanList.clear();
        int startItem = currentPage * itemsPerPage;
        int endItem = Math.min(startItem + itemsPerPage, allLaporanList.size());

        for (int i = startItem; i < endItem; i++) {
            paginatedLaporanList.add(allLaporanList.get(i));
        }

        laporanAdapter.notifyDataSetChanged();
    }

    private void updatePaginationControls() {
        tvPageNumber.setText((currentPage + 1) + "/" + totalPages);

        ivPrevPage.setEnabled(currentPage > 0);
        ivPrevPage.setAlpha(currentPage > 0 ? 1.0f : 0.5f);

        ivNextPage.setEnabled(currentPage < totalPages - 1);
        ivNextPage.setAlpha(currentPage < totalPages - 1 ? 1.0f : 0.5f);
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