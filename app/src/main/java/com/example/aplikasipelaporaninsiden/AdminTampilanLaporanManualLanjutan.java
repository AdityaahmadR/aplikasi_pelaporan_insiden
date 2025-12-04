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

public class AdminTampilanLaporanManualLanjutan extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private TextView tvDateTime, tvJenisLaporan, tvPelaporNama, tvPelaporEmail, tvDeskripsi;
    private ImageView ivLaporanImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tampilan_laporan_manual_lanjutan);

        drawerLayout = findViewById(R.id.drawer_layout);
        ImageView btnSidebar = findViewById(R.id.btnSidebar);
        LinearLayout btnUpload = findViewById(R.id.btnUpload);
        ImageView ivBack = findViewById(R.id.ivBack);

        // Initialize Views
        tvDateTime = findViewById(R.id.tvDateTime);
        tvJenisLaporan = findViewById(R.id.tvJenisLaporan);
        tvPelaporNama = findViewById(R.id.tvPelaporNama);
        tvPelaporEmail = findViewById(R.id.tvPelaporEmail);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        ivLaporanImage = findViewById(R.id.ivLaporanImage);

        btnSidebar.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        btnUpload.setOnClickListener(v -> {
            Intent intent = new Intent(AdminTampilanLaporanManualLanjutan.this, AdminTampilanHalamanVideo.class);
            startActivity(intent);
        });

        ivBack.setOnClickListener(v -> finish());

        SidebarHelper.setupSidebar(this, R.id.btnLaporanManual);

        // Get data from intent
        Laporan laporan = (Laporan) getIntent().getSerializableExtra("LAPORAN_EXTRA");

        // Populate views with data
        if (laporan != null) {
            tvDateTime.setText(laporan.getDateTime());
            tvJenisLaporan.setText(laporan.getJudul());
            tvPelaporNama.setText(laporan.getPelaporNama());
            tvPelaporEmail.setText(laporan.getPelaporEmail());
            tvDeskripsi.setText(laporan.getDeskripsi());

            if (laporan.getImageResId() != 0) {
                ivLaporanImage.setImageResource(laporan.getImageResId());
                ivLaporanImage.setVisibility(View.VISIBLE);
                findViewById(R.id.ivImagePlaceholder).setVisibility(View.GONE);
            } else {
                ivLaporanImage.setVisibility(View.GONE);
                findViewById(R.id.ivImagePlaceholder).setVisibility(View.VISIBLE);
            }
        }

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

    // The old onBackPressed method is now removed and replaced by the OnBackPressedDispatcher logic above.
}
