/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package umkmOmahOmben;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Lenovo
 */
public class laporan1 extends javax.swing.JFrame {

    private void pie1() {

        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            String sql = "SELECT tbl_produk.nama_produk as nama,sum(tbl_transaksi_detail.jumlah_barang) as ttl "
                    + "FROM tbl_transaksi_detail "
                    + "join tbl_produk "
                    + "on tbl_transaksi_detail.id_produk = tbl_produk.id_produk "
                    + "join tbl_transaksi "
                    + "on tbl_transaksi.id_transaksi = tbl_transaksi_detail.id_transaksi "
                    + "where yearweek(tbl_transaksi.tanggal) = yearweek(NOW()) "
                    + "group by tbl_transaksi_detail.id_produk";

            System.out.println(sql);
            Connection conn = (Connection) koneksi.getKoneksi();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                String nama = rs.getString("nama");
                int total = Integer.parseInt(rs.getString("ttl"));
                dataset.setValue(nama, total);
            }

            JFreeChart pieChart = ChartFactory.createPieChart3D("BARANG TERJUAL MINGGU INI", dataset, true, true, true);
            PiePlot3D pie = (PiePlot3D) pieChart.getPlot();
//            pie.setForegroundAlpha(TOP_ALIGNMENT);
            pie.setBackgroundPaint(Color.white);
            pie.setSectionPaint("Jus Jeruk", new java.awt.Color(0, 0, 0));

            ChartPanel piepanel = new ChartPanel(pieChart);

            jpanel.setLayout(new java.awt.BorderLayout());
            jpanel.add(piepanel, BorderLayout.CENTER);
            jpanel.validate();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    /**
     * Creates new form laporan1
     */
    public laporan1() {
        initComponents();
        pie1();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanel = new javax.swing.JPanel();
        bpendapatan = new javax.swing.JButton();
        bpengeluaran = new javax.swing.JButton();
        bdashboard = new javax.swing.JButton();
        bpegawai = new javax.swing.JButton();
        bproduk = new javax.swing.JButton();
        bsuplier = new javax.swing.JButton();
        bmember = new javax.swing.JButton();
        bbarangmasuk = new javax.swing.JButton();
        bpenjualan = new javax.swing.JButton();
        blaporan = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LAPORAN");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 700, 490));

        bpendapatan.setContentAreaFilled(false);
        bpendapatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpendapatanActionPerformed(evt);
            }
        });
        getContentPane().add(bpendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 360, 230));

        bpengeluaran.setContentAreaFilled(false);
        bpengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpengeluaranActionPerformed(evt);
            }
        });
        getContentPane().add(bpengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 360, 220));

        bdashboard.setContentAreaFilled(false);
        bdashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdashboardActionPerformed(evt);
            }
        });
        getContentPane().add(bdashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 3, 100, 110));

        bpegawai.setContentAreaFilled(false);
        bpegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bpegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpegawaiActionPerformed(evt);
            }
        });
        getContentPane().add(bpegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 100, 110));

        bproduk.setBorder(null);
        bproduk.setContentAreaFilled(false);
        bproduk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bprodukActionPerformed(evt);
            }
        });
        getContentPane().add(bproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 90, 110));

        bsuplier.setContentAreaFilled(false);
        bsuplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bsuplier.setDefaultCapable(false);
        bsuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsuplierActionPerformed(evt);
            }
        });
        getContentPane().add(bsuplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 90, 110));

        bmember.setContentAreaFilled(false);
        bmember.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bmember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmemberActionPerformed(evt);
            }
        });
        getContentPane().add(bmember, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 110, 100));

        bbarangmasuk.setContentAreaFilled(false);
        bbarangmasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bbarangmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbarangmasukActionPerformed(evt);
            }
        });
        getContentPane().add(bbarangmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 110, 100));

        bpenjualan.setContentAreaFilled(false);
        bpenjualan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bpenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpenjualanActionPerformed(evt);
            }
        });
        getContentPane().add(bpenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 110, 110));

        blaporan.setContentAreaFilled(false);
        blaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        blaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blaporanActionPerformed(evt);
            }
        });
        getContentPane().add(blaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 110, 110));

        logout.setContentAreaFilled(false);
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 120, 110));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design5/laporan.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bpendapatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpendapatanActionPerformed
        setVisible(false);
        new laporanpendapatan().setVisible(true);
    }//GEN-LAST:event_bpendapatanActionPerformed

    private void bpengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpengeluaranActionPerformed
        new laporanpengeluaran().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_bpengeluaranActionPerformed

    private void bdashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdashboardActionPerformed
        dispose();
        new dashboard().setVisible(true);
    }//GEN-LAST:event_bdashboardActionPerformed

    private void bpegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpegawaiActionPerformed
        int lv = login.level;
        System.out.println(lv);
        if (lv < 2) {
            new pegawai().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Maaf anda tidak bisa mengakses menu ini");
        }

    }//GEN-LAST:event_bpegawaiActionPerformed

    private void bprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bprodukActionPerformed
        int lv = login.level;
        System.out.println(lv);
        if (lv < 2) {
            dispose();
            new produk().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Maaf anda tidak bisa mengakses menu ini");
        }
    }//GEN-LAST:event_bprodukActionPerformed

    private void bsuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsuplierActionPerformed
        dispose();
        new suplier().setVisible(true);
    }//GEN-LAST:event_bsuplierActionPerformed

    private void bmemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmemberActionPerformed
        new member().setVisible(true);
        dispose();
    }//GEN-LAST:event_bmemberActionPerformed

    private void bbarangmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbarangmasukActionPerformed
        dispose();
        new barangmasuk().setVisible(true);
    }//GEN-LAST:event_bbarangmasukActionPerformed

    private void bpenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpenjualanActionPerformed
        dispose();
        new transaksi().setVisible(true);
    }//GEN-LAST:event_bpenjualanActionPerformed

    private void blaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blaporanActionPerformed
        int lv = login.level;
        System.out.println(lv);
        if (lv < 2) {
            dispose();
            new laporan1().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Maaf anda tidak bisa mengakses menu ini");
        }
    }//GEN-LAST:event_blaporanActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        int jawab = JOptionPane.showConfirmDialog(null, "APAKAH ANDA INGIN KELUAR?", "LOGOUT", JOptionPane.YES_NO_OPTION);

        switch (jawab) {
            case JOptionPane.YES_OPTION:
            dispose();
            new login().setVisible(true);
            break;
        }
    }//GEN-LAST:event_logoutActionPerformed

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
            java.util.logging.Logger.getLogger(laporan1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporan1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporan1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporan1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporan1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbarangmasuk;
    private javax.swing.JButton bdashboard;
    private javax.swing.JButton blaporan;
    private javax.swing.JButton bmember;
    private javax.swing.JButton bpegawai;
    private javax.swing.JButton bpendapatan;
    private javax.swing.JButton bpengeluaran;
    private javax.swing.JButton bpenjualan;
    private javax.swing.JButton bproduk;
    private javax.swing.JButton bsuplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jpanel;
    private javax.swing.JButton logout;
    // End of variables declaration//GEN-END:variables
}
