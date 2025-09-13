/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;


public class Create extends Koneksi {

    public void createTable() {

        Scanner sc = new Scanner(System.in);

        // Input nama tabel
        System.out.print("Input Nama Tabel : ");
        String Tbl = sc.nextLine();

        // Input jumlah kolom
        System.out.print("Input jumlah kolom : ");
        int jumlahKolom = sc.nextInt();
        sc.nextLine(); // biar nextInt gak ganggu nextLine

        // Simpan definisi kolom
        StringBuilder kolomDefinisi = new StringBuilder();

        for (int i = 1; i <= jumlahKolom; i++) {
            System.out.println("Input Nama Kolom " + i + " : ");
            String namaKolom = sc.nextLine();

            System.out.println("Input tipe kolom dan ukuran (contoh: VARCHAR(50), INT, DATE). \n"
                    + "Tambahkan 'PRIMARY KEY' jika kolom ini primary key: ");
            String tipeKolom = sc.nextLine();

            kolomDefinisi.append(namaKolom).append(" ").append(tipeKolom);

            if (i < jumlahKolom) {
                kolomDefinisi.append(", ");
            }
        }

        String QUERY = "CREATE TABLE " + Tbl + " ( " + kolomDefinisi + " );";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(QUERY);
            System.out.println("Tabel " + Tbl + " berhasil dibuat!");
        } catch (SQLException ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Apakah ingin menambahkan tabel lagi? (iya atau tidak)");
        char jwb = sc.next().toLowerCase().charAt(0);
        sc.nextLine();
        switch (jwb) {
            case 'y':
                break;
            case 't':
                System.out.println("Kembali Ke Menu Utama");
                return;
            default:
                System.out.println("Input tidak valid, kembali ke Menu Utama");
                return;
        }

    }
}
