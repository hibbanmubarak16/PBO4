/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo;
import java.sql.*;
import java.util.*;

public class Read extends Koneksi {

    private Scanner input = new Scanner(System.in);

    public void selectData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.print("Masukkan nama tabel: ");
            String tableName = input.nextLine();

            String sql = "SELECT * FROM " + tableName;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            System.out.println("\n--- DATA DARI TABEL " + tableName + " ---");

            // Header
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(meta.getColumnName(i) + "\t");
            }
            System.out.println("\n-------------------------------------------");

            // Rows
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Apakah ingin menampilkan data lagi? (iya atau tidak)");
        char jwb = input.next().toLowerCase().charAt(0);
        input.nextLine();
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
