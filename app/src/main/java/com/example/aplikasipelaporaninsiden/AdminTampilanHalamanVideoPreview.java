package com.example.aplikasipelaporaninsiden;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class AdminTampilanHalamanVideoPreview extends AppCompatActivity {

    private VideoView videoView;
    private TextView tvVideoTitle;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tampilan_halaman_video_preview);

        videoView = findViewById(R.id.video_view);
        tvVideoTitle = findViewById(R.id.tvVideoTitle);
        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(v -> finish());

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
