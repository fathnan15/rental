package com.rental;

import java.io.Serializable;

/**
 * Kelas abstrak dasar untuk semua jenis kendaraan.
 */
public abstract class Kendaraan implements Serializable {
    private String nama;
    private double hargaSewa;
    private String jenis;
    private boolean status; // true = tersedia, false = disewa

    public Kendaraan(String nama, double hargaSewa, String jenis) {
        this.nama = nama;
        this.hargaSewa = hargaSewa;
        this.jenis = jenis;
        this.status = true;
    }

    public String getNama() {
        return nama;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public String getJenis() {
        return jenis;
    }

    public boolean isTersedia() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Tampilkan informasi kendaraan spesifik.
     */
    public abstract void tampilKendaraan();
}