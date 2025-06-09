package com.rental;

/** Kendaraan tipe Bus dengan atribut jumlah kursi. */
public class Bus extends Kendaraan {
    private int jumlahKursi;

    public Bus(String nama, double hargaSewa, int jumlahKursi) {
        super(nama, hargaSewa, "Bus");
        this.jumlahKursi = jumlahKursi;
    }

    public int getJumlahKursi() {
        return jumlahKursi;
    }

    @Override
    public void tampilKendaraan() {
        System.out.printf("[Bus] %s | Harga: Rp %.0f/hari | Kursi: %d | %s%n",
                getNama(), getHargaSewa(), jumlahKursi,
                isTersedia() ? "Tersedia" : "Disewa");
    }
}