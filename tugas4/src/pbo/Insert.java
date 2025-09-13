/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo;
import java.sql.*;
import java.util.*;

public class Insert extends Koneksi {

    private Scanner input = new Scanner(System.in);

    public void insertData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            // Minta nama tabel
            System.out.print("Masukkan nama tabel: ");
            String tableName = input.nextLine();

            // Ambil metadata kolom dari tabel (nama + tipe)
            String sqlMeta = "SELECT column_name, data_type FROM information_schema.columns WHERE table_name = ? ORDER BY ordinal_position";
            PreparedStatement psMeta = conn.prepareStatement(sqlMeta);
            psMeta.setString(1, tableName.toLowerCase());

            ResultSet rsMeta = psMeta.executeQuery();

            List<String> columns = new ArrayList<>();
            List<String> types = new ArrayList<>();
            StringBuilder colNames = new StringBuilder();
            StringBuilder colValues = new StringBuilder();

            while (rsMeta.next()) {
                String col = rsMeta.getString("column_name");
                String type = rsMeta.getString("data_type");
                columns.add(col);
                types.add(type);

                if (colNames.length() > 0) {
                    colNames.append(", ");
                    colValues.append(", ");
                }
                colNames.append(col);
                colValues.append("?");
            }

            if (columns.isEmpty()) {
                System.out.println("Tabel tidak ditemukan atau tidak punya kolom!");
                return;
            }

            // Buat query insert
            String sql = "INSERT INTO " + tableName + " (" + colNames + ") VALUES (" + colValues + ")";
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
                    System.out.println(" Input salah untuk kolom " + columns.get(i) + " (" + type + ")");
                    return;
                }
            }

            // Eksekusi insert setelah semua nilai diisi
            pstmt.executeUpdate();
            System.out.println("Data berhasil ditambahkan ke tabel " + tableName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Apakah ingin menambahkan data lagi? (iya atau tidak)");
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
