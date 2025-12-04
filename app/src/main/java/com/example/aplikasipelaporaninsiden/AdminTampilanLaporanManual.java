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
        allLaporanList.add(new Laporan("Evakuasi Hewan", "Tolong pak, hewan peliharaan saya terjebak di Ruang loteng rumah saya...", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Pohon Tumbang", "Pohon besar di depan rumah tumbang dan menutupi jalan utama.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Kebakaran Ringan", "Terjadi kebakaran kecil di dapur akibat korsleting listrik.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Banjir Lokal", "Jalan di depan kompleks tergenang air setinggi 30cm.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Orang Hilang", "Anak tetangga tidak pulang sejak kemarin sore.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Kecelakaan Lalu Lintas", "Terjadi tabrakan antara motor dan mobil di perempatan.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Gangguan Keamanan", "Ada sekelompok orang mencurigakan berkumpul di taman.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Fasilitas Umum Rusak", "Lampu jalan di RT 05 padam total sudah 3 hari.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Penemuan Benda Asing", "Ditemukan tas tanpa pemilik di halte bus.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Evakuasi Sarang Tawon", "Sarang tawon besar dan berbahaya di pohon mangga.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Jalan Rusak", "Terdapat lubang besar yang membahayakan di tengah jalan.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Kebocoran Pipa PDAM", "Air bersih mengalir deras dari pipa yang bocor di trotoar.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Hewan Liar Masuk Pemukiman", "Seekor ular terlihat masuk ke pekarangan rumah warga.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Kerusakan Jembatan", "Jembatan kayu penghubung antar desa mulai rapuh.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Tawuran Antar Warga", "Terjadi perkelahian massal di lapangan desa.", "Laporan Masuk"));
    }

    private void setupPagination() {
        totalPages = (int) Math.ceil((double) allLaporanList.size() / itemsPerPage);
        paginatedLaporanList = new ArrayList<>();
        laporanAdapter = new LaporanAdapter(this, paginatedLaporanList);
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