package com.rental;

/** Kendaraan tipe Motor dengan atribut cc mesin. */
public class Motor extends Kendaraan {
    private int cc;

    public Motor(String nama, double hargaSewa, int cc) {
        super(nama, hargaSewa, "Motor");
        this.cc = cc;
    }

    public int getCc() {
        return cc;
    }

    @Override
    public void tampilKendaraan() {
        System.out.printf("[Motor] %s | Harga: Rp %.0f/hari | cc: %d | %s%n",
                getNama(), getHargaSewa(), cc,
                isTersedia() ? "Tersedia" : "Disewa");
    }
}