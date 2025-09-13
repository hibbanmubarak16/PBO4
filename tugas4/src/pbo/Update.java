/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo;
import java.sql.*;
import java.util.*;

public class Update extends Koneksi {
    private Scanner input = new Scanner(System.in);

    public void updateData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.print("Masukkan nama tabel: ");
            String tableName = input.nextLine();

            System.out.print("Masukkan nama kolom primary key: ");
            String pk = input.nextLine();
            System.out.print("Masukkan nilai primary key: ");
            String pkValue = input.nextLine();

            List<String> columns = new ArrayList<>();
            List<String> types = new ArrayList<>();

            // Ambil metadata kolom dan tipe data
            String sqlMeta = "SELECT column_name, data_type FROM information_schema.columns WHERE table_name = ?";
            PreparedStatement stmtMeta = conn.prepareStatement(sqlMeta);
            stmtMeta.setString(1, tableName);
            ResultSet rsMeta = stmtMeta.executeQuery();

            while (rsMeta.next()) {
                String col = rsMeta.getString("column_name");
                if (!col.equals(pk)) {
                    columns.add(col);
                    types.add(rsMeta.getString("data_type"));
                }
            }

            if (columns.isEmpty()) {
                System.out.println("Kolom tidak ditemukan!");
                return;
            }

            // Buat SET clause
            StringBuilder setClause = new StringBuilder();
            for (int i = 0; i < columns.size(); i++) {
                if (i > 0) setClause.append(", ");
                setClause.append(columns.get(i)).append(" = ?");
            }

            String sql = "UPDATE " + tableName + " SET " + setClause + " WHERE " + pk + " = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Isi nilai tiap kolom sesuai tipe data
            for (int i = 0; i < columns.size(); i++) {
                System.out.print("Masukkan atribut untuk " + columns.get(i) + ": ");
                String val = input.nextLine();
                String type = types.get(i).toLowerCase();

                try {
                    if (type.contains("int")) {
                        pstmt.setInt(i + 1, Integer.parseInt(val));
                    } else if (type.equals("bigint")) {
                        pstmt.setLong(i + 1, Long.parseLong(val));
                    } else if (type.equals("numeric") || type.equals("decimal")) {
                        pstmt.setBigDecimal(i + 1, new java.math.BigDecimal(val));
                    } else if (type.equals("boolean")) {
                        pstmt.setBoolean(i + 1, Boolean.parseBoolean(val));
                    } else {
                        pstmt.setString(i + 1, val);
                    }
                } catch (Exception ex) {
                    System.out.println("Input salah untuk kolom " + columns.get(i) + " (" + type + ")");
                    return;
                }
            }

            // Set nilai PK di parameter terakhir
            pstmt.setString(columns.size() + 1, pkValue);

            // Jalankan update
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " baris berhasil diupdate.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Tanya ulang
        System.out.println("Apakah ingin mengganti tabel lagi? (iya/tidak)");
        String jwb = input.next().toLowerCase();
        if (jwb.equals("iya")) {
            updateData(); // panggil ulang
        } else if (jwb.equals("tidak")) {
            System.out.println("Kembali ke Menu Utama");
        } else {
            System.out.println("Input tidak valid, kembali ke Menu Utama");
        }
    }
}
