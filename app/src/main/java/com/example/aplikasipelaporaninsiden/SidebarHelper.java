package com.example.aplikasipelaporaninsiden;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

public class SidebarHelper {

    public static void setupSidebar(final Activity activity, int activeViewId) {
        LinearLayout btnLaporanManual = activity.findViewById(R.id.btnLaporanManual);
        LinearLayout btnLaporanDarurat = activity.findViewById(R.id.btnLaporanDarurat);
        LinearLayout btnDatabase = activity.findViewById(R.id.btnDatabase);
        LinearLayout btnLogout = activity.findViewById(R.id.btnLogout);

        btnLaporanManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AdminTampilanLaporanManual.class);
                activity.startActivity(intent);
            }
        });

        btnLaporanDarurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AdminTampilanLaporanDarurat.class);
                activity.startActivity(intent);
            }
        });

        btnDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AdminDatabasePengguna.class);
                activity.startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AdminTampilanAwal.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(intent);
            }
        });

        // Set active state
        View activeView = activity.findViewById(activeViewId);
        if (activeView != null) {
            activeView.setActivated(true);
        }
    }
}
