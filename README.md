 Rental Kendaraan Java

Program ini adalah aplikasi konsol sederhana untuk mengelola penyewaan kendaraan. 
Dibangun dengan Java dan Maven, aplikasi menyediakan menu interaktif untuk menambah, menampilkan, menyewa, dan mengembalikan kendaraan.

## Fitur
- **Tambah Kendaraan**: pengguna dapat menambahkan `Mobil`, `Motor`, atau `Bus` baru.
- **Daftar Kendaraan**: menampilkan semua kendaraan beserta status ketersediaannya.
- **Penyewaan**: memilih kendaraan yang tersedia, menentukan lama sewa, dan menyimpan struk.
- **Pengembalian**: mengubah status kendaraan menjadi tersedia dan mencetak struk pengembalian.
- **Penyimpanan Data**: daftar kendaraan tersimpan di file `vehicles.txt` dan struk disimpan pada folder `src/main/resources/struk`.

## Konsep OOP
### Abstraksi & Inheritance
File `Kendaraan.java` mendefinisikan kelas abstrak yang mewakili kendaraan secara umum. Kelas ini diturunkan oleh `Mobil`, `Motor`, dan `Bus` yang masing-masing menambahkan atribut khusus seperti kapasitas penumpang, cc mesin, maupun jumlah kursi.

### Encapsulation
Setiap kelas menyimpan atribut secara `private` dan menyediakan getter/setter seperlunya. Contohnya pada `Kendaraan` terdapat `private String nama` dan metode `getNama()` sehingga data tidak langsung diakses dari luar.

### Polymorphism
`RentalKendaraan.tampilSemua()` memanggil `tampilKendaraan()` dari objek bertipe `Kendaraan`. Implementasi metode ini berbeda pada tiap subclass sehingga informasi kendaraan ditampilkan sesuai jenisnya.

### Exception Handling
Metode `sewa()` pada `Penyewaan` dan `kembalikan()` pada `RentalKendaraan` melempar `Exception` jika kendaraan tidak tersedia. Di `Main`, pemanggilan fungsi tersebut dibungkus blok `try/catch` untuk menampilkan pesan kesalahan tanpa menghentikan program.

### I/O & Operasi File
Aplikasi menggunakan `BufferedReader` dan `PrintWriter` untuk membaca/menulis file. Data kendaraan dimuat dan disimpan ke `vehicles.txt`, sedangkan struk penyewaan atau pengembalian disimpan dengan nama yang berisi timestamp agar mudah diidentifikasi.

## Materi Dasar
- **Struktur Keputusan**: menu di `Main` menggunakan `switch-case` untuk mengeksekusi pilihan pengguna.
- **Struktur Pengulangan**: terdapat `do-while` untuk menampilkan menu berulang kali hingga memilih keluar. Selain itu terdapat `for` untuk menampilkan daftar kendaraan atau menghitung total biaya.
- **Array**: parsing data file di `RentalKendaraan` menggunakan `String[] part = line.split(";")`. Parameter `String[] args` pada `main` juga merupakan contoh array.
- **String**: aplikasi banyak memanfaatkan operasi string, misalnya `equalsIgnoreCase`, `replaceAll`, dan `printf` untuk format teks.

Program akan menampilkan menu dan menyimpan data pada folder `src/main/resources`.
