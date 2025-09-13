/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo;
import java.sql.*;
import java.util.*;

public class Delete extends Koneksi {

    private Scanner input = new Scanner(System.in);

    public void deleteData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            
            System.out.println("=== MENU DELETE DATA ===");
            System.out.println("1. Hapus berdasarkan kolom (misal primary key)");
            System.out.println("2. Hapus seluruh isi tabel");
            System.out.print("Pilih menu (1/2): ");
            int pilihan = input.nextInt();
            input.nextLine(); // clear buffer

            if (pilihan == 1) {
                // Hapus berdasarkan kolom
                System.out.print("Masukkan nama tabel: ");
                String tableName = input.nextLine();

                System.out.print("Masukkan nama kolom: ");
                String col = input.nextLine();
                System.out.print("Masukkan nilai yang ingin dihapus: ");
                String value = input.nextLine();

                String sql = "DELETE FROM " + tableName + " WHERE " + col + " = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println(rows + " data berhasil dihapus dari tabel " + tableName);
                } else {
                    System.out.println("Data tidak ditemukan.");
                }

            } else if (pilihan == 2) {
                // Hapus seluruh isi tabel
                System.out.print("Masukkan nama tabel: ");
                String tableName = input.nextLine();

                System.out.print("Apakah kamu yakin ingin menghapus SEMUA data dari tabel " 
                                  + tableName + " ? (iya/tidak): ");
                String confirm = input.nextLine().toLowerCase();

                if (confirm.equals("iya")) {
                    String sql = "DELETE FROM " + tableName;
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    int rows = pstmt.executeUpdate();
                    System.out.println(rows + " data berhasil dihapus dari tabel " + tableName);
                } else {
                    System.out.println("Penghapusan dibatalkan.");
                }
            } else {
                System.out.println("Pilihan tidak valid.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Tanya ulang
        System.out.println("Apakah ingin menghapus data lagi? (iya/tidak)");
        String jwb = input.next().toLowerCase();
        if (jwb.equals("iya")) {
            deleteData(); // ulangi proses
        } else if (jwb.equals("tidak")) {
            System.out.println("Kembali ke Menu Utama");
        } else {
            System.out.println("Input tidak valid, kembali ke Menu Utama");
        }
    }
}

