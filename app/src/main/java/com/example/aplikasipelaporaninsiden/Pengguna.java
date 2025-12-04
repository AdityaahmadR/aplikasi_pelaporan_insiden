package com.example.aplikasipelaporaninsiden;

public class Pengguna {
    private String nama;
    private String email;
    private String riwayatPelaporan;

    public Pengguna(String nama, String email, String riwayatPelaporan) {
        this.nama = nama;
        this.email = email;
        this.riwayatPelaporan = riwayatPelaporan;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getRiwayatPelaporan() {
        return riwayatPelaporan;
    }
}
