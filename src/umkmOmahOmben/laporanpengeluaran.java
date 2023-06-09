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
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**"'
 * "
 * "
 * 
 *
 * @author Lenovo
 */
public class laporanpengeluaran extends javax.swing.JFrame {

    public void RubahKeExcel(javax.swing.JTable table, File file) {
        try {
            DefaultTableModel Model_Data = (DefaultTableModel) table.getModel();
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

    public void excel(javax.swing.JTable table, File file) {

        try {
            DefaultTableModel Model_Data = (DefaultTableModel) tabel.getModel();
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

    public void cetak(String awal, String akhir) {
        try {
            Connection conn = (Connection) koneksi.getKoneksi();
            // Menyiapkan id transaksi
            HashMap parameter = new HashMap();
            parameter.put("awal", awal);
            parameter.put("akhir", akhir);

            // Membaca file JasperDesign dari JRXML
            InputStream file = getClass().getResourceAsStream("/report/laporan_penjualan.jrxml");

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

    public void tabel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Id Pegawai");
        model.addColumn("Id Suplier");
        model.addColumn("Jumlah Barang");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");

        try {
            String sql = "select * from tbl_brgmsk where 1 ";
            Connection conn = (Connection) koneksi.getKoneksi();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6)});
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error Pada Tabel 'LAPORAN PENGELUARAN'");
        }
        tabel.setModel(model);
    }

//    private void kalender() {
//        Date ys = new Date();
//        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
//
//        tglawal.setText(fm.format(ys));
//        tglakhir.setText(fm.format(ys));
//
//    }
    private void total() {

        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String awal = String.valueOf(fm.format(tglawal.getDate()));
        String akhir = String.valueOf(fm.format(tglakhir.getDate()));

        try {
            String sql = "select sum(total_harga) as total  from tbl_brgmsk "
                    + "where date(tanggal) between '" + awal + "' and '" + akhir + "'";
            Connection conn = (Connection) koneksi.getKoneksi();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                ttotal.setText(rs.getString("total"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Terjadi ERROR Pada Penjumlahan");
        }
    }

    /**
     * Creates new form laporanpengeluaran
     */
    public laporanpengeluaran() {
        initComponents();
//        kalender();
        tabel();
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
        tabel = new javax.swing.JTable();
        ttotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LAPORAN PENGELUARAN");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("EXPORT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 180, -1, -1));
        getContentPane().add(tglakhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 260, 50));
        getContentPane().add(tglawal, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 260, 50));

        bkeluar.setContentAreaFilled(false);
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(bkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 90));

        tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 250, 1210, 380));

        ttotal.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ttotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ttotalFocusGained(evt);
            }
        });
        ttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttotalActionPerformed(evt);
            }
        });
        getContentPane().add(ttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(968, 647, 270, 50));

        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 160, 140, 60));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton2.setText("REFERSH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 70, 140, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design5/laporan pengeluaran.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttotalActionPerformed

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        setVisible(false);
        new laporan1().setVisible(true);
    }//GEN-LAST:event_bkeluarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String awal = String.valueOf(fm.format(tglawal.getDate()));
        String akhir = String.valueOf(fm.format(tglakhir.getDate()));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Id Pegawai");
        model.addColumn("Id Suplier");
        model.addColumn("Jumlah Barang");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");

        try {
            String sql = "select * from tbl_brgmsk "
                    + "where date(tanggal) between '" + awal + "' and '" + akhir + "'";

            Connection conn = (Connection) koneksi.getKoneksi();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println(sql);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6)});
                tabel.setModel(model);

            }

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        total();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void ttotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ttotalFocusGained

    }//GEN-LAST:event_ttotalFocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        kalender();
        tabel();
        total();
        tglawal.setDate(null);
        tglakhir.setDate(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//        JFileChooser path = new JFileChooser();
//
//        path.showOpenDialog(this);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try {
//            File f = path.getSelectedFile();
            String location = "D:\\";
            String filename = location + "LAPORAN PENGELUARAN_" + date + ".xls";
            File fp = new File(filename);

            RubahKeExcel(tabel, fp);
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
            java.util.logging.Logger.getLogger(laporanpengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporanpengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporanpengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporanpengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporanpengeluaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel;
    private com.toedter.calendar.JDateChooser tglakhir;
    private com.toedter.calendar.JDateChooser tglawal;
    private javax.swing.JTextField ttotal;
    // End of variables declaration//GEN-END:variables
}
