package com.example.aplikasipelaporaninsiden;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Pastikan menggunakan kelas model "Edukasi"
public class EdukasiAdapter extends RecyclerView.Adapter<EdukasiAdapter.EdukasiViewHolder> {

    private List<Edukasi> edukasiList;

    // Konstruktor harus menerima List<Edukasi>
    public EdukasiAdapter(List<Edukasi> edukasiList) {
        this.edukasiList = edukasiList;
    }

    @NonNull
    @Override
    public EdukasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edukasi, parent, false);
        return new EdukasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EdukasiViewHolder holder, int position) {
        Edukasi edukasi = edukasiList.get(position);
        holder.thumbnail.setImageResource(edukasi.getThumbnail());
        holder.title.setText(edukasi.getTitle());
        holder.views.setText(edukasi.getViews());
    }

    @Override
    public int getItemCount() {
        return edukasiList.size();
    }

    static class EdukasiViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView title;
        TextView views;

        public EdukasiViewHolder(@NonNull View itemView) {
            super(itemView);
            // Pastikan ID cocok dengan item_edukasi.xml
            thumbnail = itemView.findViewById(R.id.edukasi_image);
            title = itemView.findViewById(R.id.edukasi_title);
            views = itemView.findViewById(R.id.edukasi_views);
        }
    }
}
