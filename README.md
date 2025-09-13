Tugas Pertemuan 4 – CRUD Java dengan PostgreSQL
Repository ini berisi implementasi CRUD (Create, Read, Update, Delete) menggunakan bahasa pemrograman Java dengan koneksi ke PostgreSQL melalui JDBC. Program ini dibuat untuk tugas kuliah Pemrograman Berorientasi Objek.

Struktur Project
Koneksi.java
Berisi konfigurasi koneksi ke database PostgreSQL (URL, USER, PASSWORD). Class ini menjadi dasar untuk class lainnya.

Create.java
Meng-handle pembuatan tabel baru dalam database. User diminta untuk memasukkan nama tabel, jumlah kolom, nama kolom, tipe data, dan menentukan primary key.

Insert.java
Digunakan untuk memasukkan data ke tabel yang sudah ada. Program akan menampilkan daftar tabel, user memilih tabel, lalu mengisi nilai untuk tiap kolom yang tersedia.

Read.java
Menampilkan data dari tabel tertentu. Program akan menyesuaikan jumlah kolom sehingga bisa menampilkan semua isi tabel dengan fleksibel.

Update.java
Mengubah data pada tabel. User memilih tabel, menentukan kolom mana yang ingin diubah, lalu mengisi nilai baru.

Delete.java
Menghapus data. User bisa memilih untuk menghapus seluruh tabel, menghapus kolom (atribut), atau hanya menghapus baris data tertentu.

Database.java Menyediakan logika tambahan sebagai “menu utama” agar user bisa memilih operasi CRUD yang diinginkan.# PBO4
