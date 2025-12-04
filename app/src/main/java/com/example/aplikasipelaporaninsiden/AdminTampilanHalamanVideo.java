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
    private VideoAdapter adapter;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tampilan_halaman_video);

        ivBack = findViewById(R.id.ivBack);
        rvVideoGrid = findViewById(R.id.rvVideoGrid);

        // The GridLayoutManager is already set in the XML, so we don't need to set it here.

        // Create Dummy Data
        List<VideoItem> dummyData = new ArrayList<>();
        dummyData.add(new VideoItem("Video 1", "00:15", R.drawable.dummy_image_1, R.raw.dummy_video));
        dummyData.add(new VideoItem("Video 2", "01:05", R.drawable.dummy_image_2, R.raw.dummy_video));
        dummyData.add(new VideoItem("Video 3", "00:42", R.drawable.dummy_image_1, R.raw.dummy_video));
        dummyData.add(new VideoItem("Video 4", "02:10", R.drawable.dummy_image_2, R.raw.dummy_video));
        dummyData.add(new VideoItem("Video 5", "00:30", R.drawable.dummy_image_1, R.raw.dummy_video));
        dummyData.add(new VideoItem("Video 6", "05:00", R.drawable.dummy_image_2, R.raw.dummy_video));

        // Set Adapter
        adapter = new VideoAdapter(this, dummyData);
        rvVideoGrid.setAdapter(adapter);

        // Set Back Button Listener
        ivBack.setOnClickListener(v -> finish());
    }
}