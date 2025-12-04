package com.example.aplikasipelaporaninsiden;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder> {

    private List<Laporan> laporanList;
    private Context context;

    public LaporanAdapter(Context context, List<Laporan> laporanList) {
        this.context = context;
        this.laporanList = laporanList;
    }

    @NonNull
    @Override
    public LaporanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_laporan_manual, parent, false);
        return new LaporanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanViewHolder holder, int position) {
        Laporan laporan = laporanList.get(position);
        holder.tvJudul.setText(laporan.getJudul());
        holder.tvDeskripsi.setText(laporan.getDeskripsi());
        holder.tvStatus.setText(laporan.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AdminTampilanLaporanManualLanjutan.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return laporanList.size();
    }

    public static class LaporanViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvDeskripsi, tvStatus;

        public LaporanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
