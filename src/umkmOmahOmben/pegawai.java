package umkmOmahOmben;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

public class pegawai extends javax.swing.JFrame {

    private void ID_PEGAWAI() {
        List logBarang = new ArrayList();
        String sql = "SELECT max(right(id_pegawai,4)) as id FROM tbl_pegawai ";
        //mengambil nilai id pegawai dari kanan 4 huruf/angka
        try {

            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            java.sql.ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                if (rs.first() == false) {
                    tidpegawai.setText("PG0001");
                } else {
                    rs.last();
                    int autoid = rs.getInt("id");
                    String nomor = String.valueOf(autoid + 1);
                    int no = nomor.length();

                    for (int i = 0; i < 4 - no; i++) {
                        nomor = "0" + nomor;
                    }
                    tidpegawai.setText("PG" + nomor);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void load_table() {
        //membuat tampilan
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Pegawai");
        model.addColumn("Nama Pegawai");
        model.addColumn("NO_Telp");
        model.addColumn("Alamat");

        //menampilkan data database kedalam tabel
        try {
            String sql = "SELECT * FROM tbl_pegawai";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4)});
            }
            tblhasil.setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void clear() {
        ID_PEGAWAI();
        tnama.setText(null);
        talamat.setText(null);
        tnohp.setText(null);
    }

    /**
     * Creates new form pegawai
     */
    public pegawai() {
        initComponents();
        load_table();
        ID_PEGAWAI();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tnohp = new javax.swing.JTextField();
        tidpegawai = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        talamat = new javax.swing.JTextField();
        bhapus = new javax.swing.JButton();
        btambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblhasil = new javax.swing.JTable();
        bedit = new javax.swing.JButton();
        info = new javax.swing.JButton();
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

        popup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        popup.setAlwaysOnTop(true);
        popup.setLocation(new java.awt.Point(400, 150));
        popup.setMinimumSize(new java.awt.Dimension(500, 500));
        popup.setResizable(false);
        popup.setSize(new java.awt.Dimension(500, 500));

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design3/popup pegawai.png"))); // NOI18N
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3);

        popup.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PEGAWAI");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tnohp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tnohp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(tnohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, 510, 40));

        tidpegawai.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tidpegawai.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tidpegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidpegawaiActionPerformed(evt);
            }
        });
        getContentPane().add(tidpegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 510, 50));

        tnama.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tnama.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamaActionPerformed(evt);
            }
        });
        getContentPane().add(tnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 510, 40));

        talamat.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        talamat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(talamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 510, 40));

        bhapus.setBorder(null);
        bhapus.setContentAreaFilled(false);
        bhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        getContentPane().add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 560, 130, 60));

        btambah.setBorder(null);
        btambah.setContentAreaFilled(false);
        btambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });
        getContentPane().add(btambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 560, 130, 60));

        tblhasil.setModel(new javax.swing.table.DefaultTableModel(
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
        tblhasil.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblhasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhasilMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblhasil);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, 520, 520));

        bedit.setFont(new java.awt.Font("Segoe Print", 1, 24)); // NOI18N
        bedit.setBorder(null);
        bedit.setContentAreaFilled(false);
        bedit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        getContentPane().add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 560, 120, 60));

        info.setText("?");
        info.setAlignmentY(0.0F);
        info.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoActionPerformed(evt);
            }
        });
        getContentPane().add(info, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, -1, -1));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design5/pegawai.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed

        if (tidpegawai.getText().equals("") || tnama.getText().equals("") || tnohp.getText().equals("") || talamat.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "ISI SEMUA DATA TERLEBIH DAHULU");
        } else {
           
            try { int nomer = Integer.parseInt(tnohp.getText());
                if (tnohp.getText().length() > 13 || tnohp.getText().length() <= 8) {
                    JOptionPane.showMessageDialog(null, "Masukuan Nomer Telepon Dengan Benar", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
//                int nomer = Integer.parseInt(tnohp.getText());
                        String sql = "INSERT INTO tbl_pegawai VALUES ('" + tidpegawai.getText() + "','"
                                + tnama.getText() + "','" + tnohp.getText() + "','" + talamat.getText() + "')";

                        java.sql.Connection conn = (Connection) koneksi.getKoneksi();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "penyimpanan Berhasil");
                        clear();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Periksa ID Pegawai dan Nomer Telepon!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                load_table();
                ID_PEGAWAI();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Masukuan Nomer Telepon Dengan Benar", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btambahActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        // delete
        int jawab = JOptionPane.showConfirmDialog(null, "APAKAH ANDA INGIN MENGAHPUS", "HAPUS", JOptionPane.YES_NO_OPTION);

        switch (jawab) {
            case JOptionPane.YES_OPTION:
                if (tidpegawai.getText().equals("") || tnama.getText().equals("") || tnohp.getText().equals("") || talamat.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "PILIH DATA TERLEBIH DAHULU");
                } else {
                    try {
                        String sql = "DELETE from tbl_pegawai where id_pegawai ='" + tidpegawai.getText() + "'";
                        java.sql.Connection conn = (Connection) koneksi.getKoneksi();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(this, "Berhasil Menghapus Data");
                        clear();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "TIDAK DAPAT MENGHAPUS PEGAWAI");
                    }
                    load_table();
                    ID_PEGAWAI();
                    btambah.setVisible(true);
                }
                break;
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void tblhasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhasilMouseClicked
        int baris = tblhasil.rowAtPoint(evt.getPoint());

        String idpegawai = tblhasil.getValueAt(baris, 0).toString();
        tidpegawai.setText(idpegawai);

        String nama = tblhasil.getValueAt(baris, 1).toString();
        tnama.setText(nama);

        String nohp = tblhasil.getValueAt(baris, 2).toString();
        tnohp.setText(nohp);

        String alamat = tblhasil.getValueAt(baris, 3).toString();
        talamat.setText(alamat);

        btambah.setVisible(false);
        load_table();

    }//GEN-LAST:event_tblhasilMouseClicked

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        //edit data
        if (tidpegawai.getText().equals("") || tnama.getText().equals("") || tnohp.getText().equals("") || talamat.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "PILIH DATA TERLEBIH DAHULU");
        } else {
            try {
                String sql = "Update tbl_pegawai SET nama_pegawai='" + tnama.getText() + "',no_telp='" + tnohp.getText()
                        + "',alamat_pegawai='" + talamat.getText() + "' where id_pegawai='" + tidpegawai.getText() + "'";
                java.sql.Connection conn = (Connection) koneksi.getKoneksi();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diedit");
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Perubahan data gagal" + e.getMessage());
            }
            load_table();
            btambah.setVisible(true);

            ID_PEGAWAI();
        }
    }//GEN-LAST:event_beditActionPerformed

    private void tidpegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidpegawaiActionPerformed

    }//GEN-LAST:event_tidpegawaiActionPerformed

    private void tnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamaActionPerformed
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Pegawai");
        model.addColumn("Nama Pegawai");
        model.addColumn("NO_Telp");
        model.addColumn("Alamat");

        //menampilkan data database kedalam tabel
        try {
            String nama = tnama.getText();
            String sql = "SELECT * FROM tbl_pegawai where nama_pegawai LIKE  '%" + nama + "%'";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4)});
            }
            tblhasil.setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_tnamaActionPerformed

    private void infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoActionPerformed
        popup.setVisible(true);
    }//GEN-LAST:event_infoActionPerformed

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
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pegawai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbarangmasuk;
    private javax.swing.JButton bdashboard;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton blaporan;
    private javax.swing.JButton bmember;
    private javax.swing.JButton bpegawai;
    private javax.swing.JButton bpenjualan;
    private javax.swing.JButton bproduk;
    private javax.swing.JButton bsuplier;
    private javax.swing.JButton btambah;
    private javax.swing.JButton info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout;
    private javax.swing.JDialog popup;
    private javax.swing.JTextField talamat;
    private javax.swing.JTable tblhasil;
    private javax.swing.JTextField tidpegawai;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tnohp;
    // End of variables declaration//GEN-END:variables
}