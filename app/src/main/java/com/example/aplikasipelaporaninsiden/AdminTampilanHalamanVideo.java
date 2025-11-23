package com.example.aplikasipelaporaninsiden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import java.util.ArrayList;
import java.util.List;

public class AdminTampilanHalamanVideo extends AppCompatActivity {

    private RecyclerView rvVideoGrid;
    private VideoThumbnailAdapter adapter;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tampilan_halaman_video);

        ivBack = findViewById(R.id.ivBack);
        rvVideoGrid = findViewById(R.id.rvVideoGrid);

        // 1. Setup RecyclerView
        // GridLayoutManager (3 kolom) diatur di XML, tetapi bisa diatur di sini juga
        // rvVideoGrid.setLayoutManager(new GridLayoutManager(this, 3));

        // 2. Buat Data Dummy Default
        List<String> dummyData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dummyData.add("Video " + i);
        }

        // 3. Set Adapter
        adapter = new VideoThumbnailAdapter(dummyData);
        rvVideoGrid.setAdapter(adapter);

        // 4. Set Listener Default
        if (ivBack != null) {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}