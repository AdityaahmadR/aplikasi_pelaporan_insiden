package com.example.aplikasipelaporaninsiden;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// DIperbaiki agar sesuai dengan struktur proyek saat ini
public class scrolledukasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolledukasi);

        RecyclerView recyclerView = findViewById(R.id.edukasi_recycler_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if ((position + 1) % 3 == 0) {
                    return 2; // Item besar
                } else {
                    return 1; // Item kecil
                }
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);

        // Menggunakan kelas "Edukasi" yang benar
        List<Edukasi> edukasiList = new ArrayList<>();
        edukasiList.add(new Edukasi(R.drawable.ic_launcher_background, "Title 1", "Views 1"));
        edukasiList.add(new Edukasi(R.drawable.ic_launcher_background, "Title 2", "Views 2"));
        edukasiList.add(new Edukasi(R.drawable.ic_launcher_background, "Title 3 (Large)", "Views 3"));

        EdukasiAdapter adapter = new EdukasiAdapter(edukasiList);
        recyclerView.setAdapter(adapter);
    }
}
