package com.rental;

/** Kendaraan tipe Mobil dengan atribut kapasitas penumpang. */
public class Mobil extends Kendaraan {
    private int kapasitasPenumpang;

    public Mobil(String nama, double hargaSewa, int kapasitasPenumpang) {
        super(nama, hargaSewa, "Mobil");
        this.kapasitasPenumpang = kapasitasPenumpang;
    }

    public int getKapasitasPenumpang() {
        return kapasitasPenumpang;
    }

    @Override
    public void tampilKendaraan() {
        System.out.printf("[Mobil] %s | Harga: Rp %.0f/hari | Kapasitas: %d penumpang | %s%n",
                getNama(), getHargaSewa(), kapasitasPenumpang,
                isTersedia() ? "Tersedia" : "Disewa");
    }
}