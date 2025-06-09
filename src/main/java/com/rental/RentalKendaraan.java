package com.rental;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manajemen daftar kendaraan: load, save, tambah, tampil, dan kembalikan.
 */
public class RentalKendaraan {
    private List<Kendaraan> daftar = new ArrayList<>();
    private static final String RESOURCES_DIR = "rental/src/main/resources";
    private static final String VEHICLES_FILE = RESOURCES_DIR + "/vehicles.txt";

    public RentalKendaraan() {
        loadFromFile();
    }

    /** Tambah kendaraan baru ke daftar. */
    public void tambah(Kendaraan k) {
        daftar.add(k);
    }

    /** Tampilkan semua kendaraan. */
    public void tampilSemua() {
        System.out.println("=== Daftar Kendaraan ===");
        for (Kendaraan k : daftar) {
            k.tampilKendaraan();
        }
    }

    /** Kembalikan kendaraan berdasarkan nama, update status dan simpan. */
    public void kembalikan(String nama) throws Exception {
        for (Kendaraan k : daftar) {
            if (k.getNama().equalsIgnoreCase(nama)) {
                if (k.isTersedia()) {
                    throw new Exception("Kendaraan sudah tersedia (sudah dikembalikan): " + nama);
                }
                k.setStatus(true);
                saveToFile();
                System.out.println("Kendaraan berhasil dikembalikan: " + nama);
                return;
            }
        }
        throw new Exception("Kendaraan tidak ditemukan: " + nama);
    }

    /** Simpan daftar kendaraan ke file teks di resources. */
    public void saveToFile() {
        try {
            File dir = new File(RESOURCES_DIR);
            if (!dir.exists())
                dir.mkdirs();
            try (PrintWriter pw = new PrintWriter(new FileWriter(VEHICLES_FILE))) {
                for (Kendaraan k : daftar) {
                    pw.printf("%s;%s;%.0f;%b;%s%n",
                            k.getJenis(), k.getNama(), k.getHargaSewa(), k.isTersedia(),
                            k instanceof Mobil ? "cap=" + ((Mobil) k).getKapasitasPenumpang()
                                    : k instanceof Motor ? "cc=" + ((Motor) k).getCc()
                                            : k instanceof Bus ? "seat=" + ((Bus) k).getJumlahKursi() : "");
                }
            }
        } catch (IOException e) {
            System.err.println("Gagal simpan data kendaraan: " + e.getMessage());
        }
    }

    /** Muat daftar kendaraan dari file teks di resources. */
    private void loadFromFile() {
        File f = new File(VEHICLES_FILE);
        if (!f.exists())
            return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] part = line.split(";");
                String jenis = part[0], nama = part[1];
                double harga = Double.parseDouble(part[2]);
                boolean status = Boolean.parseBoolean(part[3]);
                String spec = part[4];
                Kendaraan k;
                if ("Mobil".equals(jenis)) {
                    int cap = Integer.parseInt(spec.split("=")[1]);
                    k = new Mobil(nama, harga, cap);
                } else if ("Motor".equals(jenis)) {
                    int cc = Integer.parseInt(spec.split("=")[1]);
                    k = new Motor(nama, harga, cc);
                } else {
                    int seat = Integer.parseInt(spec.split("=")[1]);
                    k = new Bus(nama, harga, seat);
                }
                k.setStatus(status);
                daftar.add(k);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Gagal muat data kendaraan: " + e.getMessage());
        }
    }

    public List<Kendaraan> getDaftar() {
        return daftar;
    }
}