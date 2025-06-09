package com.rental;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Pencatatan penyewaan dan penyimpanan struk ke file.
 */
public class Penyewaan {
    private List<Kendaraan> disewa = new ArrayList<>();
    private String namaPelanggan;
    private static final String RESOURCES_DIR = "rental/src/main/resources";
    private static final String STRUK_DIR = RESOURCES_DIR + "/struk";

    public Penyewaan(String nama) {
        this.namaPelanggan = nama;
    }

    /** Tambah kendaraan ke daftar sewa. */
    public void sewa(Kendaraan k) throws Exception {
        if (!k.isTersedia())
            throw new Exception("Kendaraan tidak tersedia: " + k.getNama());
        k.setStatus(false);
        disewa.add(k);
        new RentalKendaraan().saveToFile();
    }

    /** Hitung total biaya sewa. */
    public double hitungBiaya(int hari) {
        double total = 0;
        for (Kendaraan k : disewa) {
            total += k.getHargaSewa() * hari;
        }
        return total;
    }

    /**
     * Simpan struk penyewaan ke file teks di resources/struk dengan format nama
     * mudah dibaca.
     */
    public void simpanStrukSewa(int hari) {
        File dir = new File(STRUK_DIR);
        if (!dir.exists())
            dir.mkdirs();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String safeName = namaPelanggan.replaceAll("\\s+", "_");
        String namaFile = String.format("struk_sewa_%s_%s.txt", safeName, timestamp);
        writeReceipt(hari, namaFile, false);
    }

    /** Simpan struk pengembalian ke file teks di resources/struk. */
    public void simpanStrukKembali(int hari) {
        File dir = new File(STRUK_DIR);
        if (!dir.exists())
            dir.mkdirs();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String safeName = namaPelanggan.replaceAll("\\s+", "_");
        String namaFile = String.format("struk_kembali_%s_%s.txt", safeName, timestamp);
        writeReceipt(hari, namaFile, true);
    }

    private void writeReceipt(int hari, String namaFile, boolean isReturn) {
        File fileStruk = new File(STRUK_DIR, namaFile);
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileStruk))) {
            pw.println(isReturn ? "--- STRUK PENGEMBALIAN ---" : "--- STRUK PENYEWAAN ---");
            pw.println("Pelanggan : " + namaPelanggan);
            pw.println(isReturn ? "Hari pinjam : " + hari : "Hari sewa   : " + hari);
            pw.println();
            for (Kendaraan k : disewa) {
                pw.printf("- %s (%s) : Rp %.0f/hari%n", k.getNama(), k.getJenis(), k.getHargaSewa());
            }
            pw.println();
            pw.printf(isReturn ? "TOTAL BIAYA (denda?): Rp %.0f%n" : "TOTAL BIAYA           : Rp %.0f%n",
                    hitungBiaya(hari));
            pw.println("-----------------------");
            System.out.println("Struk disimpan di: " + fileStruk.getPath());
        } catch (IOException e) {
            System.err.println("Gagal simpan struk: " + e.getMessage());
        }
    }
}