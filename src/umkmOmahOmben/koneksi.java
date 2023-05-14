package umkmOmahOmben;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class koneksi {
    private static java.sql.Connection koneksi;
    private static java.sql.Statement stm;
    
     public static java.sql.Connection getKoneksi() throws ClassNotFoundException{
    Class.forName("com.mysql.jdbc.Driver");
    if ( koneksi == null){
        try {
            String url = "jdbc:mysql://localhost:3306/umkm_omah_omben";
            String user = "root";
            String password = "";
            
            koneksi = DriverManager.getConnection(url, user, password);
            stm = koneksi.createStatement();
            System.out.println("koneksi berhasil");
        } catch(SQLException t){
            JOptionPane.showMessageDialog(null,"Error membuat koneksi");
        }
    }
    return koneksi;
}
     
}
