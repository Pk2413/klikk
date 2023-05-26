package umkmOmahOmben;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import java.sql.Statement;
import java.sql.ResultSet;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Lenovo
 */
public class dashboard extends javax.swing.JFrame {

    public String getNamaHari(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "Minggu";
            case Calendar.MONDAY:
                return "Senin";
            case Calendar.TUESDAY:
                return "Selasa";
            case Calendar.WEDNESDAY:
                return "Rabu";
            case Calendar.THURSDAY:
                return "Kamis";
            case Calendar.FRIDAY:
                return "Jumat";
            case Calendar.SATURDAY:
                return "Sabtu";
            default:
                return "null";
        }
    }

    private String getWeek() {
        Calendar c = Calendar.getInstance();
        Date d = new Date(c.get(Calendar.YEAR) - 1900, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) - 7);
        c.setTime(d);

        Calendar cc = Calendar.getInstance();
        for (int i = 1; i <= 7; i++) {
            d = new Date(c.get(Calendar.YEAR) - 1900, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) + i);
            cc.setTime(d);
//            System.out.println(this.getNamaHari(cc.get(Calendar.DAY_OF_WEEK)));
        }

        return null;
    }

    private int getData(String date) throws Exception {
        String sql1 = "SELECT  sum(total_harga) as pembelian "
                + "from tbl_brgmsk "
                + "WHERE tanggal = '" + date + "' "
                + "";
        
String sql2 = "select sum(total_harga) as penjualan from tbl_transaksi where tanggal ='"+date+"'"
        + "";
        Connection c = (Connection) koneksi.getKoneksi();
        Statement s = c.createStatement();
        ResultSet a = s.executeQuery(sql1);
        
        Statement t = c.createStatement();
        ResultSet b = t.executeQuery(sql2);
        System.out.println(sql1);
        System.out.println(sql2);
        
        int penjualan = 0;
        int pembelian = 0;
        int total = 0;
        
        if (a.next()) {
            pembelian = a.getInt("pembelian");
            
        } 
        if(b.next()){
            penjualan = b.getInt("penjualan");
        }
        
        total = penjualan - pembelian;
        System.out.println("total : " + total);
        
        return total;
        
    }

    private void createDataset() {

        try {
//            String sql = "SELECT dayname(tbl_transaksi.tanggal) as tgl1, dayname(tbl_brgmsk.tanggal) as tgl,"
//                    + "sum(tbl_transaksi.total_harga - tbl_brgmsk.harga_total) as total "
//                    + "FROM tbl_brgmsk "
//                    + "join tbl_transaksi "
//                    + "on tbl_transaksi.tanggal = tbl_brgmsk.tanggal "
//                    + "where yearweek(tbl_transaksi.tanggal) = yearweek(now()) "
//                    + "and yearweek(tbl_brgmsk.tanggal) = yearweek(NOW()) "
//                    + "GROUP BY tgl, tgl1 "
//                    + "order by dayofweek(tbl_transaksi.tanggal) asc; ";
//            JDBCCategoryDataset dataset = new JDBCCategoryDataset(koneksi.getKoneksi(), sql);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            Calendar c = Calendar.getInstance();
            Date d = new Date(c.get(Calendar.YEAR) - 1900, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) - 7);
            c.setTime(d);

            Calendar cc = Calendar.getInstance();
            for (int i = 1; i <= 7; i++) {
                d = new Date(c.get(Calendar.YEAR) - 1900, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) + i);
                cc.setTime(d);
                String tgl = String.format("%d-%02d-%02d", cc.get(Calendar.YEAR), cc.get(Calendar.MONTH) + 1, cc.get(Calendar.DAY_OF_MONTH));
                String hari = this.getNamaHari(cc.get(Calendar.DAY_OF_WEEK));
                System.out.println(tgl);
//                System.out.println(hari);
//                System.out.println(cc.get(Calendar.DAY_OF_WEEK));
                dataset.setValue(this.getData(tgl), "Amount", hari);
            }

            JFreeChart chart = ChartFactory.createLineChart("PENDAPATAN MINGGU INI", "hari",
                    "PPENJUALAN", dataset, PlotOrientation.VERTICAL, false, true, true);
            chart.setBackgroundPaint(Color.white);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.black);

            ChartPanel barpanel = new ChartPanel(chart);

            jpanel.add(barpanel, BorderLayout.CENTER);
            jpanel.validate();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void PEMASUKAN() {
        try {
            String sql = "select sum(jumlah_barang), sum(total_harga) as harga, tanggal "
                    + "FROM tbl_transaksi "
                    + "group by tanggal "
                    + "having tanggal = DATE(NOW());";

            Connection conn = (Connection) koneksi.getKoneksi();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                lpemasukan.setText(rs.getString("harga"));

            } else {
                lpemasukan.setText("0");
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void PENGELUARAN() {
        try {
            String sql = "select sum(jumlah_barang), sum(total_harga) as total, tanggal "
                    + "FROM tbl_brgmsk "
                    + "group by tanggal "
                    + "having tanggal = DATE(NOW());";
            Connection conn = koneksi.getKoneksi();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                lpengeluaran.setText(rs.getString("total"));
            } else {
                lpengeluaran.setText("0");
            }

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void PRODUK() {

        try {
            String sql = "select sum(jumlah_barang) as produk, sum(total_harga) as harga, day(tanggal) as tgl "
                    + "FROM tbl_transaksi group by tgl "
                    + "having tgl = day(NOW())";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                lterjual.setText("Hari Ini => " + rs.getString("produk"));

            } else {
                lterjual.setText("Hari Ini => 0");
            }

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Creates new form dashboard
     */
    public dashboard() {
        initComponents();
        PRODUK();
        PENGELUARAN();
        PEMASUKAN();
        createDataset();
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bmember = new javax.swing.JButton();
        bpegawai = new javax.swing.JButton();
        bbarangmasuk = new javax.swing.JButton();
        bproduk = new javax.swing.JButton();
        bpenjualan = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        bsuplier = new javax.swing.JButton();
        lpengeluaran = new javax.swing.JLabel();
        lpemasukan = new javax.swing.JLabel();
        lterjual = new javax.swing.JLabel();
        bdashboard = new javax.swing.JButton();
        blaporan = new javax.swing.JButton();
        jpanel = new javax.swing.JPanel();
        jj = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DASHBOARD");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bmember.setContentAreaFilled(false);
        bmember.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bmember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmemberActionPerformed(evt);
            }
        });
        getContentPane().add(bmember, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 110, 100));

        bpegawai.setContentAreaFilled(false);
        bpegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bpegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpegawaiActionPerformed(evt);
            }
        });
        getContentPane().add(bpegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 100, 110));

        bbarangmasuk.setContentAreaFilled(false);
        bbarangmasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bbarangmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbarangmasukActionPerformed(evt);
            }
        });
        getContentPane().add(bbarangmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 110, 100));

        bproduk.setBorder(null);
        bproduk.setContentAreaFilled(false);
        bproduk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bprodukActionPerformed(evt);
            }
        });
        getContentPane().add(bproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 90, 110));

        bpenjualan.setContentAreaFilled(false);
        bpenjualan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bpenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpenjualanActionPerformed(evt);
            }
        });
        getContentPane().add(bpenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 110, 110));

        logout.setContentAreaFilled(false);
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 120, 110));

        bsuplier.setContentAreaFilled(false);
        bsuplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bsuplier.setDefaultCapable(false);
        bsuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsuplierActionPerformed(evt);
            }
        });
        getContentPane().add(bsuplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 90, 110));

        lpengeluaran.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lpengeluaran.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lpengeluaran.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lpengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, 160, 30));

        lpemasukan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lpemasukan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lpemasukan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lpemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 160, 30));

        lterjual.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lterjual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lterjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 617, 160, 30));

        bdashboard.setContentAreaFilled(false);
        bdashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdashboardActionPerformed(evt);
            }
        });
        getContentPane().add(bdashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 3, 100, 110));

        blaporan.setContentAreaFilled(false);
        blaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        blaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blaporanActionPerformed(evt);
            }
        });
        getContentPane().add(blaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 110, 110));

        jpanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 800, 510));
        jpanel.getAccessibleContext().setAccessibleDescription("");

        jj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design5/dashboard.png"))); // NOI18N
        jj.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jj, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void bbarangmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbarangmasukActionPerformed

        int lv = login.level;
        System.out.println(lv);
        if (lv <= 2) {
            dispose();
            new barangmasuk().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Maaf anda tidak bisa mengakses menu ini");
        }
    }//GEN-LAST:event_bbarangmasukActionPerformed

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

    private void bpenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpenjualanActionPerformed
       int lv = login.level;
        System.out.println(lv);
        if (lv <= 2) {
            dispose();
            new transaksi().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Maaf anda tidak bisa mengakses menu ini");
        } 
        
    }//GEN-LAST:event_bpenjualanActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        int jawab = JOptionPane.showConfirmDialog(null, "APAKAH ANDA INGIN KELUAR?", "LOGOUT", JOptionPane.YES_NO_OPTION);

        switch (jawab) {
            case JOptionPane.YES_OPTION:
                dispose();
                new login().setVisible(true);
                break;
        }
    }//GEN-LAST:event_logoutActionPerformed

    private void bsuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsuplierActionPerformed
        int lv = login.level;
        System.out.println(lv);
        if (lv <= 2) {
            dispose();
            new suplier().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Maaf anda tidak bisa mengakses menu ini");
        } 
    }//GEN-LAST:event_bsuplierActionPerformed

    private void bdashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdashboardActionPerformed
       int lv = login.level;
        System.out.println(lv);
        if (lv <= 2) {
            dispose();
            new dashboard().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Maaf anda tidak bisa mengakses menu ini");
        }  
    }//GEN-LAST:event_bdashboardActionPerformed

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

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void bmemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmemberActionPerformed
       int lv = login.level;
        System.out.println(lv);
        if (lv <= 2) {
            dispose();
            new member().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Maaf anda tidak bisa mengakses menu ini");
        } 
    }//GEN-LAST:event_bmemberActionPerformed

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
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbarangmasuk;
    private javax.swing.JButton bdashboard;
    private javax.swing.JButton blaporan;
    private javax.swing.JButton bmember;
    private javax.swing.JButton bpegawai;
    private javax.swing.JButton bpenjualan;
    private javax.swing.JButton bproduk;
    private javax.swing.JButton bsuplier;
    private javax.swing.JLabel jj;
    private javax.swing.JPanel jpanel;
    private javax.swing.JButton logout;
    private javax.swing.JLabel lpemasukan;
    private javax.swing.JLabel lpengeluaran;
    private javax.swing.JLabel lterjual;
    // End of variables declaration//GEN-END:variables

}
