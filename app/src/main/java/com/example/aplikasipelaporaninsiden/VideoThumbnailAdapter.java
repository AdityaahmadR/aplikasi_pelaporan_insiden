package com.example.aplikasipelaporaninsiden;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VideoThumbnailAdapter extends RecyclerView.Adapter<VideoThumbnailAdapter.ViewHolder> {

    private final List<String> videoList; // Data List

    public VideoThumbnailAdapter(List<String> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video_thumbnail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Data model Anda akan dimasukkan di sini, misal:
        // VideoModel currentVideo = videoList.get(position);

        // Contoh: Set durasi dummy
        if (position % 2 == 0) {
            holder.tvDuration.setText("00:22");
            holder.ivPlayIcon.setVisibility(View.VISIBLE);
        } else {
            holder.tvDuration.setText("02:00");
            holder.ivPlayIcon.setVisibility(View.GONE);
        }

        // Di sini Anda akan menggunakan library seperti Glide/Picasso untuk memuat gambar ke holder.ivThumbnail
        // Glide.with(holder.ivThumbnail.getContext()).load(currentVideo.getThumbnailUrl()).into(holder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvDuration;
        ImageView ivPlayIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            ivPlayIcon = itemView.findViewById(R.id.ivPlayIcon);

            // Set listener default untuk item di sini
            itemView.setOnClickListener(v -> {
                // Logika klik item akan diimplementasikan di sini (misal: membuka detail video)
            });
        }
    }
}