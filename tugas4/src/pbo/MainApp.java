/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo;
import java.util.*;

public class MainApp{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean jalan = true;

        while (jalan) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Create Table");
            System.out.println("2. Insert Data");
            System.out.println("3. Update Data");
            System.out.println("4. Delete Data");
            System.out.println("5. Read Data");
            System.out.println("6. Close");
            System.out.print("Pilih menu: ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1: 
                    Create create = new Create();
                    create.createTable();
                    break;
                case 2:
                    Insert insert = new Insert();
                    insert.insertData();
                    break;
                case 3: 
                    Update update = new Update();
                    update.updateData();
                    break;
              
                case 4: 
                    Delete delete = new Delete();
                    delete.deleteData();
                    break;
                case 5 : 
                    Read select = new Read();
                    select.selectData();
                    break;
                case 6:
                    jalan = false;
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
    }
}

