package com.rental;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RentalKendaraan rental = new RentalKendaraan();
        Scanner sc = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("=== RENTAL KENDARAAN ===");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan Kendaraan");
            System.out.println("3. Penyewaan");
            System.out.println("4. Pengembalian");
            System.out.println("5. Keluar");
            System.out.print("Pilih (1-5): ");
            pilihan = sc.nextInt();
            sc.nextLine();
            switch (pilihan) {
                case 1:
                    System.out.print("Jenis Kendaraan (Mobil/Motor/Bus): ");
                    String jenis = sc.nextLine();
                    System.out.print("Nama/Merek Kendaraan       : ");
                    String nm = sc.nextLine();
                    System.out.print("Harga Sewa per Hari  : ");
                    double harga = sc.nextDouble();
                    sc.nextLine();
                    if ("Mobil".equalsIgnoreCase(jenis)) {
                        System.out.print("Kapasitas Penumpang  : ");
                        int cap = sc.nextInt();
                        sc.nextLine();
                        rental.tambah(new Mobil(nm, harga, cap));
                    } else if ("Motor".equalsIgnoreCase(jenis)) {
                        System.out.print("CC Mesin             : ");
                        int cc = sc.nextInt();
                        sc.nextLine();
                        rental.tambah(new Motor(nm, harga, cc));
                    } else {
                        System.out.print("Jumlah Kursi         : ");
                        int seat = sc.nextInt();
                        sc.nextLine();
                        rental.tambah(new Bus(nm, harga, seat));
                    }
                    rental.saveToFile();
                    break;
                case 2:
                    rental.tampilSemua();
                    break;
                case 3:
                    System.out.print("Nama Pelanggan       : ");
                    String cust = sc.nextLine();
                    Penyewaan p = new Penyewaan(cust);
                    rental.tampilSemua();
                    System.out.print("Nama Kendaraan yang mau disewa: ");
                    String pilihK = sc.nextLine();
                    try {
                        for (Kendaraan k : rental.getDaftar()) {
                            if (k.getNama().equalsIgnoreCase(pilihK)) {
                                p.sewa(k);
                                break;
                            }
                        }
                        System.out.print("Lama sewa (hari)     : ");
                        int hari = sc.nextInt();
                        sc.nextLine();
                        p.simpanStrukSewa(hari);
                        rental.saveToFile();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                case 4:
                    System.out.print("Nama Kendaraan yang dikembalikan: ");
                    String kembali = sc.nextLine();
                    try {
                        rental.kembalikan(kembali);
                        System.out.print("Jumlah hari pinjam (untuk denda): ");
                        int hari = sc.nextInt();
                        sc.nextLine();
                        new Penyewaan("").simpanStrukKembali(hari);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);
        sc.close();
    }
}
