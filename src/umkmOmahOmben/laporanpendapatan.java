/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package umkmOmahOmben;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Lenovo
 */
public class laporanpendapatan extends javax.swing.JFrame {

    int jumlahbeli = 0;
    int jumlahjual = 0;

    public static void convertTwoTablesToExcel(JTable table1, JTable table2, File file) {
        try {

            FileWriter fw = new FileWriter(file);

            TableModel model1 = table1.getModel();
            TableModel model2 = table2.getModel();
            int columnCount1 = model1.getColumnCount();
            int columnCount2 = model2.getColumnCount();
            fw.write("PEMBELIAN");
            fw.write("\n");
            // Tuliskan header kolom untuk tabel 1
            for (int col = 0; col < columnCount1; col++) {
                fw.write(model1.getColumnName(col));
                if (col < columnCount1 - 1) {
                    fw.write("\t"); // Karakter pemisah: tab
                }
            }
            fw.write("\n");

            // Tuliskan data baris untuk tabel 1
            int rowCount1 = model1.getRowCount();
            for (int row = 0; row < rowCount1; row++) {
                for (int col = 0; col < columnCount1; col++) {
                    Object value = model1.getValueAt(row, col);
                    fw.write(value.toString());
                    if (col < columnCount1 - 1) {
                        fw.write("\t"); // Karakter pemisah: tab
                    }
                }

                fw.write("\n");
            }

            fw.write("\n");
            fw.write("\n");
            fw.write("PENJUALAN");
            fw.write("\n");

            // Tuliskan header kolom untuk tabel 2
            for (int col = 0; col < columnCount2; col++) {
                fw.write(model2.getColumnName(col));
                if (col < columnCount2 - 1) {
                    fw.write("\t"); // Karakter pemisah: tab
                }
            }
            fw.write("\n");

            // Tuliskan data baris untuk tabel 2
            int rowCount2 = model2.getRowCount();
            for (int row = 0; row < rowCount2; row++) {
                for (int col = 0; col < columnCount2; col++) {
                    Object value = model2.getValueAt(row, col);
                    fw.write(value.toString());
                    if (col < columnCount2 - 1) {
                        fw.write("\t"); // Karakter pemisah: tab
                    }
                }
                fw.write("\n");
            }

            fw.close();
            System.out.println("Tabel berhasil dikonversi menjadi file Excel.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RubahKeExcel(javax.swing.JTable table1, File file) {
        try {
            DefaultTableModel Model_Data = (DefaultTableModel) table1.getModel();
            FileWriter ObjWriter = new FileWriter(file);

            for (int i = 0; i < Model_Data.getColumnCount(); i++) {
                ObjWriter.write(Model_Data.getColumnName(i) + "\t");
            }

            ObjWriter.write("\n");

            for (int i = 0; i < Model_Data.getRowCount(); i++) {
                for (int j = 0; j < Model_Data.getColumnCount(); j++) {
                    ObjWriter.write(Model_Data.getValueAt(i, j).toString() + "\t");
                }
                ObjWriter.write("\n");
            }

            ObjWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

//    public void tanggal(String awal, String akhir) {
//        String tampilan = "yyyy-MM-dd";
//        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
//        awal = String.valueOf(fm.format(tglawal.getDate()));
//
//        String tampilan1 = "yyyy-MM-dd";
//        SimpleDateFormat fm1 = new SimpleDateFormat(tampilan1);
//        akhir = String.valueOf(fm1.format(tglakhir.getDate()));
//
//        tglawal.setDateFormatString("yyyy-MM-dd");
//        tglakhir.setDateFormatString("yyyy-MM-dd");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        
//        
//        
//Date tgl = tglawal.getDate();
//String awal1 = df.format(tgl);
//        awal = String.valueOf(df.format(tglawal.getDate()));
//        akhir = String.valueOf(tglakhir.getDate());
//        System.out.println(awal1);
//    }
    public void cetak(String awal, String akhir) {
        try {
            Connection conn = (Connection) koneksi.getKoneksi();
            // Menyiapkan id transaksi
            HashMap parameter = new HashMap();
            parameter.put("awal", awal);
            parameter.put("akhir", akhir);

            // Membaca file JasperDesign dari JRXML
            InputStream file = getClass().getResourceAsStream("/report/laporan_pendapatan.jrxml");

            JasperDesign desain = JRXmlLoader.load(file);

            // Mengompilasi report dari JasperDesign
            JasperReport report = JasperCompileManager.compileReport(desain);

            // Mengisi report dengan data dan parameter
            JasperPrint print = JasperFillManager.fillReport(report, parameter, conn);

            // Menampilkan jasper viewer
            JasperViewer jview = new JasperViewer(print, false);
            jview.setTitle("Cetak Laporan " + akhir + " sampi " + akhir);
            jview.setVisible(true);
            jview.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jview.setLocationRelativeTo(null);
            jview.setFitPageZoomRatio();

            // Menutup jasper viewer saat user menekan tombol close
            jview.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    jview.dispose();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pembelian() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Id Pegawai");
        model.addColumn("Id Suplier");
        model.addColumn("Jumlah Barang");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");

        try {
            String sql = "select * from tbl_brgmsk order by id_transaksi asc ";
            Connection conn = (Connection) koneksi.getKoneksi();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6)});

            }
            tpembelian.setModel(model);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error Pada Tabel 'LAPORAN PENGELUARAN'");
        }

    }

    private void penjualan() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Id Pegawai");
        model.addColumn("Id Member");
        model.addColumn("Nama Produk");
        model.addColumn("Jumlah Barang");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");
        model.addColumn("Pembayaran");
        model.addColumn("Kembalian");

        //menampilkan data database kedalam tabel
        try {
            String sql = "SELECT * "
                    + "FROM tbl_transaksi ";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2),
                    res.getString(3), res.getString(8), res.getString(4), res.getString(5),
                    res.getString(6), res.getString(7), res.getString(8)});
            }
            tpenjualan.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi ERROR Pada 'TABEL PENJUALAN'");
        }

    }

    public void total() {

        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);

        String awal = String.valueOf(fm.format(tglawal.getDate()));
        String akhir = String.valueOf(fm.format(tglakhir.getDate()));

        try {
            String sql = "SELECT sum(total_harga) as total FROM tbl_transaksi where date(tanggal) between '" + awal + "' and '" + akhir + "'";

            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                jumlahjual = Integer.parseInt(res.getString("total"));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            String sql = "SELECT sum(total_harga) as total FROM tbl_brgmsk where date(tanggal) between '" + awal + "' and '" + akhir + "'";

            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                jumlahbeli = Integer.parseInt(res.getString("total"));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        total.setText(String.valueOf(jumlahjual - jumlahbeli));
    }

//     private void kalender() {
//        Date ys = new Date();
//        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
//
//        tglawal.setText(fm.format(ys));
//        tglakhir.setText(fm.format(ys));
//
//    }
    /**
     * Creates new form laporanpendapatan
     */
    public laporanpendapatan() {

        initComponents();
        pembelian();
        penjualan();
//        kalender();
//        total();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        tglakhir = new com.toedter.calendar.JDateChooser();
        tglawal = new com.toedter.calendar.JDateChooser();
        bkeluar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpembelian = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        total = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tpenjualan = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LAPORAN PENDAPATAN");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("EXPORT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 170, -1, -1));

        tglakhir.setDateFormatString("MMM d,y");
        tglakhir.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(tglakhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 270, 60));

        tglawal.setDateFormatString("MMM d,y");
        tglawal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(tglawal, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 270, 60));

        bkeluar.setContentAreaFilled(false);
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(bkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 90));

        tpembelian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tpembelian);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 1210, 170));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 154, 140, 50));

        total.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 650, 270, 50));

        tpenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tpenjualan);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 1210, 190));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton2.setText("REFERSH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 70, 140, 40));

        jLabel3.setText("Tabel Pembelian :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel1.setText("Tabel Penjualan :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, 10));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design5/laporan pendapatan.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        setVisible(false);
        new laporan1().setVisible(true);
    }//GEN-LAST:event_bkeluarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        String awal = null ;
//        String akhir = null;
//
//        tanggal(awal, akhir);

        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String awal = String.valueOf(fm.format(tglawal.getDate()));
        String akhir = String.valueOf(fm.format(tglakhir.getDate()));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Id Pegawai");
        model.addColumn("Id Member");
        model.addColumn("Nama Produk");
        model.addColumn("Jumlah Barang");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");
        model.addColumn("Pembayaran");
        model.addColumn("Kembalian");

        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("Id Transaksi");
        model1.addColumn("Id Pegawai");
        model1.addColumn("Id Suplier");
        model1.addColumn("Jumlah Barang");
        model1.addColumn("Total Harga");
        model1.addColumn("Tanggal");
        try {
            String sql = "SELECT * FROM tbl_transaksi "
                    + "where tanggal between '" + awal + "' and '" + akhir + "' ";

            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2),
                    res.getString(3), res.getString(8), res.getString(4), res.getString(5),
                    res.getString(6), res.getString(7), res.getString(8)});
                tpenjualan.setModel(model);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR Pada Tombol Cari");
        }

        try {
            String sql = "SELECT * FROM tbl_brgmsk where date(tanggal) between '" + awal + "' and '" + akhir + "'";

            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                model1.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6)});
                tpembelian.setModel(model1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage() + "ERROR Pada Tombol Cari");
        }

        total();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        pembelian();
        penjualan();
        total();

        tglawal.setDate(null);
        tglakhir.setDate(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//        JFileChooser path = new JFileChooser();
//        path.showOpenDialog(this);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try {
//            File f = path.getSelectedFile();
            String location = "D:\\";
            String filename = location + "LAPORAN PENDAPATAN_" + date + ".xls";
            File fp = new File(filename);

//            RubahKeExcel(tabel, fp);
            convertTwoTablesToExcel(tpembelian, tpenjualan, fp);
            System.out.println(fp);
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(laporanpendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporanpendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporanpendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporanpendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporanpendapatan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser tglakhir;
    private com.toedter.calendar.JDateChooser tglawal;
    private javax.swing.JTextField total;
    private javax.swing.JTable tpembelian;
    private javax.swing.JTable tpenjualan;
    // End of variables declaration//GEN-END:variables
}
