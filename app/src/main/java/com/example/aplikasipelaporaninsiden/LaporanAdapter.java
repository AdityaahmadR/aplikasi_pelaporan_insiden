package com.example.aplikasipelaporaninsiden;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder> {

    private List<Laporan> laporanList;
    private Context context;
    private boolean isNavigationEnabled;

    public LaporanAdapter(Context context, List<Laporan> laporanList, boolean isNavigationEnabled) {
        this.context = context;
        this.laporanList = laporanList;
        this.isNavigationEnabled = isNavigationEnabled;
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

        holder.bind(laporan);

        if (isNavigationEnabled) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AdminTampilanLaporanManualLanjutan.class);
                    intent.putExtra("LAPORAN_EXTRA", laporan); // Pass the whole Laporan object
                    context.startActivity(intent);
                }
            });
        } else {
            holder.itemView.setOnClickListener(null);
        }

        holder.cardStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatusMenu(v, holder, laporan);
            }
        });
    }

    private void showStatusMenu(View view, LaporanViewHolder holder, Laporan laporan) {
        PopupMenu popup = new PopupMenu(context, view);
        popup.getMenuInflater().inflate(R.menu.status_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                String newStatus = item.getTitle().toString();
                laporan.setStatus(newStatus);
                holder.bind(laporan); // Re-bind the view to update color and text
                return true;
            }
        });

        popup.show();
    }

    @Override
    public int getItemCount() {
        return laporanList.size();
    }

    public static class LaporanViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvDeskripsi, tvStatus;
        CardView cardStatus;

        public LaporanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            cardStatus = itemView.findViewById(R.id.cardStatus);
        }

        public void bind(Laporan laporan) {
            tvStatus.setText(laporan.getStatus());
            int colorResId;
            switch (laporan.getStatus()) {
                case "Sedang Berlangsung":
                    colorResId = R.color.status_sedang_berlangsung;
                    break;
                case "Selesai":
                    colorResId = R.color.status_selesai;
                    break;
                case "Laporan Masuk":
                default:
                    colorResId = R.color.status_laporan_masuk;
                    break;
            }
            cardStatus.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(), colorResId));
        }
    }
}
