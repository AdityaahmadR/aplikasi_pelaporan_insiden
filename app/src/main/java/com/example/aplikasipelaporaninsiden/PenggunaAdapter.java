package com.example.aplikasipelaporaninsiden;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PenggunaAdapter extends RecyclerView.Adapter<PenggunaAdapter.PenggunaViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(Pengguna pengguna);
    }

    private List<Pengguna> penggunaList;
    private Context context;
    private OnDeleteClickListener deleteClickListener;

    public PenggunaAdapter(Context context, List<Pengguna> penggunaList, OnDeleteClickListener deleteClickListener) {
        this.context = context;
        this.penggunaList = penggunaList;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public PenggunaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_database_user, parent, false);
        return new PenggunaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenggunaViewHolder holder, int position) {
        Pengguna pengguna = penggunaList.get(position);
        holder.tvNama.setText(pengguna.getNama());
        holder.tvEmail.setText(pengguna.getEmail());
        holder.tvRiwayat.setText(pengguna.getRiwayatPelaporan());

        holder.btnHapus.setOnClickListener(v -> {
            if (deleteClickListener != null) {
                deleteClickListener.onDeleteClick(pengguna);
            }
        });
    }

    @Override
    public int getItemCount() {
        return penggunaList.size();
    }

    public static class PenggunaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvEmail, tvRiwayat;
        Button btnHapus;

        public PenggunaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvRiwayat = itemView.findViewById(R.id.tvRiwayat);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }
}
