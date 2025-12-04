package com.example.aplikasipelaporaninsiden;

public class Laporan {
    private String judul;
    private String deskripsi;
    private String status;

    public Laporan(String judul, String deskripsi, String status) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.status = status;
    }

    public String getJudul() {
        return judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
