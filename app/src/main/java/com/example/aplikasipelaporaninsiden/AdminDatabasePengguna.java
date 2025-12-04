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

public class AdminDatabasePengguna extends AppCompatActivity implements PenggunaAdapter.OnDeleteClickListener {

    private DrawerLayout drawerLayout;
    private RecyclerView rvDatabase;
    private PenggunaAdapter penggunaAdapter;
    private List<Pengguna> allPenggunaList;
    private List<Pengguna> paginatedPenggunaList;

    private int currentPage = 0;
    private int itemsPerPage = 5; // Set item limit to 5 per page
    private int totalPages;

    private ImageView ivPrevPage, ivNextPage;
    private TextView tvPageNumber;
    private LinearLayout layoutTambahUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_database_pengguna);

        drawerLayout = findViewById(R.id.drawer_layout);
        ImageView btnSidebar = findViewById(R.id.btnSidebar);
        LinearLayout btnUpload = findViewById(R.id.btnUpload);

        ivPrevPage = findViewById(R.id.ivPrevPage);
        ivNextPage = findViewById(R.id.ivNextPage);
        tvPageNumber = findViewById(R.id.tvPageNumber);
        layoutTambahUser = findViewById(R.id.layoutTambahUser);

        btnSidebar.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        btnUpload.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDatabasePengguna.this, AdminTampilanHalamanVideo.class);
            startActivity(intent);
        });

        SidebarHelper.setupSidebar(this, R.id.btnDatabase);

        // Setup RecyclerView
        rvDatabase = findViewById(R.id.rvDatabase);
        rvDatabase.setLayoutManager(new LinearLayoutManager(this));
        rvDatabase.setNestedScrollingEnabled(false); // Important for NestedScrollView

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
        allPenggunaList = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            allPenggunaList.add(new Pengguna("User Name " + i, "user" + i + "@example.com", (i % 5) + " Pelaporan"));
        }
    }

    private void setupPagination() {
        totalPages = (int) Math.ceil((double) allPenggunaList.size() / itemsPerPage);
        paginatedPenggunaList = new ArrayList<>();
        penggunaAdapter = new PenggunaAdapter(this, paginatedPenggunaList, this);
        rvDatabase.setAdapter(penggunaAdapter);
    }

    private void updateRecyclerView() {
        paginatedPenggunaList.clear();
        int startItem = currentPage * itemsPerPage;
        int endItem = Math.min(startItem + itemsPerPage, allPenggunaList.size());

        for (int i = startItem; i < endItem; i++) {
            paginatedPenggunaList.add(allPenggunaList.get(i));
        }

        penggunaAdapter.notifyDataSetChanged();
    }

    private void updatePaginationControls() {
        // Recalculate total pages in case of deletion
        totalPages = (int) Math.ceil((double) allPenggunaList.size() / itemsPerPage);
        if (totalPages == 0) totalPages = 1; // Avoid 0/0 display
        if (currentPage >= totalPages) {
            currentPage = totalPages - 1;
        }

        tvPageNumber.setText((currentPage + 1) + "/" + totalPages);

        ivPrevPage.setEnabled(currentPage > 0);
        ivPrevPage.setAlpha(currentPage > 0 ? 1.0f : 0.5f);

        ivNextPage.setEnabled(currentPage < totalPages - 1);
        ivNextPage.setAlpha(currentPage < totalPages - 1 ? 1.0f : 0.5f);

        // Show 'Tambah User' button only on the last page
        if (currentPage == totalPages - 1) {
            layoutTambahUser.setVisibility(View.VISIBLE);
        } else {
            layoutTambahUser.setVisibility(View.GONE);
        }
         // Also handle case where there are no items
        if (allPenggunaList.isEmpty()) {
            layoutTambahUser.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDeleteClick(Pengguna pengguna) {
        allPenggunaList.remove(pengguna);
        updatePaginationControls(); // Update page counts first
        updateRecyclerView();     // Then refresh the view
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