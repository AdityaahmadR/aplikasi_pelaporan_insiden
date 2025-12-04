package com.example.aplikasipelaporaninsiden;

import java.io.Serializable;

// Implement Serializable to allow passing this object between activities
public class Laporan implements Serializable {
    private String judul;
    private String deskripsi;
    private String status;
    private String dateTime;
    private String pelaporNama;
    private String pelaporEmail;
    private int imageResId; // Using a drawable resource ID for dummy data

    public Laporan(String judul, String deskripsi, String status, String dateTime, String pelaporNama, String pelaporEmail, int imageResId) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.status = status;
        this.dateTime = dateTime;
        this.pelaporNama = pelaporNama;
        this.pelaporEmail = pelaporEmail;
        this.imageResId = imageResId;
    }

    // Getters
    public String getJudul() { return judul; }
    public String getDeskripsi() { return deskripsi; }
    public String getStatus() { return status; }
    public String getDateTime() { return dateTime; }
    public String getPelaporNama() { return pelaporNama; }
    public String getPelaporEmail() { return pelaporEmail; }
    public int getImageResId() { return imageResId; }

    // Setter
    public void setStatus(String status) {
        this.status = status;
    }
}
