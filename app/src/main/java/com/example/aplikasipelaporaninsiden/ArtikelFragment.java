package com.example.aplikasipelaporaninsiden;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArtikelFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artikel, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.artikel_recycler_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if ((position + 1) % 3 == 0) {
                    return 2; // Item besar
                } else {
                    return 1; // Item kecil
                }
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);

        // Data contoh untuk demonstrasi
        List<Edukasi> edukasiList = new ArrayList<>();
        edukasiList.add(new Edukasi(R.drawable.ic_launcher_background, "Tutorial Padamkan Tabung Gas dengan Gaya Keyakin...", "65 rb x ditonton"));
        edukasiList.add(new Edukasi(R.drawable.ic_launcher_background, "Tutorial Padamkan Tabung Gas dengan Gaya Keyakin...", "65 rb x ditonton"));
        edukasiList.add(new Edukasi(R.drawable.ic_launcher_background, "Begini Cara Memadamkan Api dari Tabung Gas Yang Menyala secara Mudah da Aman", "1,8 rb x ditonton - 10 hari yang lalu"));
        edukasiList.add(new Edukasi(R.drawable.ic_launcher_background, "Tutorial Padamkan Tabung Gas dengan Gaya Keyakin...", ""));
        edukasiList.add(new Edukasi(R.drawable.ic_launcher_background, "Tutorial Padamkan Tabung Gas dengan Gaya Keyakin...", ""));
        edukasiList.add(new Edukasi(R.drawable.ic_launcher_background, "Begini Cara Memadamkan Api dari Tabung Gas Yang Menyala secara Mudah da Aman", "1,8 rb x ditonton - 10 hari yang lalu"));

        EdukasiAdapter adapter = new EdukasiAdapter(edukasiList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
