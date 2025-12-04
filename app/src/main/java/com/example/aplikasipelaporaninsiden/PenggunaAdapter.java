package com.example.aplikasipelaporaninsiden;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PenggunaAdapter extends RecyclerView.Adapter<PenggunaAdapter.PenggunaViewHolder> {

    private List<Pengguna> penggunaList;
    private Context context;

    public PenggunaAdapter(Context context, List<Pengguna> penggunaList) {
        this.context = context;
        this.penggunaList = penggunaList;
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
    }

    @Override
    public int getItemCount() {
        return penggunaList.size();
    }

    public static class PenggunaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvEmail, tvRiwayat;

        public PenggunaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvRiwayat = itemView.findViewById(R.id.tvRiwayat);
        }
    }
}
