package com.example.aplikasipelaporaninsiden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
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

        // Handle back press to close drawer
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    if (isEnabled()) {
                        setEnabled(false);
                        getOnBackPressedDispatcher().onBackPressed();
                    }
                }
            }
        });
    }

    private void createDummyData() {
        allLaporanList = new ArrayList<>();
        // Dummy data for Laporan Darurat (you can customize this)
        allLaporanList.add(new Laporan("Serangan Jantung", "Seorang pria paruh baya mengeluh nyeri dada hebat.", "Laporan Masuk", "Senin, 27 Okt 2025 (1 menit lalu)", "Keluarga Pasien", "keluarga@email.com", 0));
        allLaporanList.add(new Laporan("Kebakaran Hebat", "Api melalap sebuah ruko di pusat kota.", "Laporan Masuk", "Senin, 27 Okt 2025 (5 menit lalu)", "Saksi Mata", "saksi_kebakaran@email.com", R.drawable.dummy_image_1));
        allLaporanList.add(new Laporan("Kecelakaan Fatal", "Kecelakaan beruntun di jalan tol, ada korban jiwa.", "Laporan Masuk", "Minggu, 26 Okt 2025 (1 jam lalu)", "Polisi Jalan Raya", "pjr@polisi.go.id", R.drawable.dummy_image_2));
        allLaporanList.add(new Laporan("Bencana Alam: Gempa", "Gempa bumi skala 6.2 mengguncang wilayah.", "Laporan Masuk", "Minggu, 26 Okt 2025 (3 jam lalu)", "BMKG", "info@bmkg.go.id", 0));
        allLaporanList.add(new Laporan("Tindak Kriminal Bersenjata", "Perampokan di sebuah bank, pelaku membawa senjata api.", "Laporan Masuk", "Sabtu, 25 Okt 2025 (1 hari lalu)", "Satpam Bank", "security@bank.com", 0));
        allLaporanList.add(new Laporan("Orang Tenggelam", "Seorang anak dilaporkan tenggelam di sungai.", "Laporan Masuk", "Sabtu, 25 Okt 2025 (1 hari lalu)", "Warga Sekitar", "warga@email.com", R.drawable.dummy_image_1));
    }

    private void setupPagination() {
        totalPages = (int) Math.ceil((double) allLaporanList.size() / itemsPerPage);
        paginatedLaporanList = new ArrayList<>();
        // Disable navigation for this page by passing false
        laporanAdapter = new LaporanAdapter(this, paginatedLaporanList, false);
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

    // The old onBackPressed method is now removed and replaced by the OnBackPressedDispatcher logic above.
}
