package com.example.aplikasipelaporaninsiden;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class AdminTampilanHalamanVideoPreview extends AppCompatActivity {

    private VideoView videoView;
    private TextView tvVideoTitle;
    private ImageView ivBack;
    private Button btnBatal, btnLanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tampilan_halaman_video_preview);

        videoView = findViewById(R.id.video_view);
        tvVideoTitle = findViewById(R.id.tvVideoTitle);
        ivBack = findViewById(R.id.ivBack);
        btnBatal = findViewById(R.id.btnBatal);
        btnLanjut = findViewById(R.id.btnLanjut);

        ivBack.setOnClickListener(v -> finish());

        btnBatal.setOnClickListener(v -> {
            // Go back to the video grid page
            finish();
        });

        btnLanjut.setOnClickListener(v -> {
            // Navigate to the UploadVideo activity
            Intent intent = new Intent(AdminTampilanHalamanVideoPreview.this, UploadVideo.class);
            startActivity(intent);
        });

        VideoItem videoItem = (VideoItem) getIntent().getSerializableExtra("VIDEO_ITEM");

        if (videoItem != null) {
            tvVideoTitle.setText(videoItem.getTitle());

            if (videoItem.getVideoRawResId() != 0) {
                String videoPath = "android.resource://" + getPackageName() + "/" + videoItem.getVideoRawResId();
                Uri videoUri = Uri.parse(videoPath);
                videoView.setVideoURI(videoUri);
                videoView.start();
            }
        }
    }
}
