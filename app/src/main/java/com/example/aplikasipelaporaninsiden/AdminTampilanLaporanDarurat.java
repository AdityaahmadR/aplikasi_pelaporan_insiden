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

public class AdminTampilanLaporanDarurat extends AppCompatActivity {

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
        setContentView(R.layout.activity_admin_tampilan_laporan_darurat);

        drawerLayout = findViewById(R.id.drawer_layout);
        ImageView btnSidebar = findViewById(R.id.btnSidebar);
        LinearLayout btnUpload = findViewById(R.id.btnUpload);

        ivPrevPage = findViewById(R.id.ivPrevPage);
        ivNextPage = findViewById(R.id.ivNextPage);
        tvPageNumber = findViewById(R.id.tvPageNumber);

        btnSidebar.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        btnUpload.setOnClickListener(v -> {
            Intent intent = new Intent(AdminTampilanLaporanDarurat.this, AdminTampilanHalamanVideo.class);
            startActivity(intent);
        });

        SidebarHelper.setupSidebar(this, R.id.btnLaporanDarurat);

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
        // Dummy data for Laporan Darurat (you can customize this)
        allLaporanList.add(new Laporan("Serangan Jantung", "Seorang pria paruh baya mengeluh nyeri dada hebat.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Kebakaran Hebat", "Api melalap sebuah ruko di pusat kota.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Kecelakaan Fatal", "Kecelakaan beruntun di jalan tol, ada korban jiwa.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Bencana Alam: Gempa", "Gempa bumi skala 6.2 mengguncang wilayah.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Tindak Kriminal Bersenjata", "Perampokan di sebuah bank, pelaku membawa senjata api.", "Laporan Masuk"));
        allLaporanList.add(new Laporan("Orang Tenggelam", "Seorang anak dilaporkan tenggelam di sungai.", "Laporan Masuk"));
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