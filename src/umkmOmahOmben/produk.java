package umkmOmahOmben;

import com.barcodelib.barcode.Linear;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.onbarcode.barcode.Code128;
import com.onbarcode.barcode.jasper.BarcodeGenerator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Lenovo
 */
public class produk extends javax.swing.JFrame {
    private static Linear bc = new Linear();

    private void barcode() {

        String barcode_text = tidproduk.getText(); // ganti dengan ID produk yang diambil dari database
        int width = barcode.getWidth();
        int height = barcode.getHeight();

        try {
           
            bc.setType(Linear.CODE128B);
            bc.setData(barcode_text);
            bc.setI(12.0f);

            BufferedImage barcode_image_buffered = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
            barcode_image_buffered = bc.renderBarcode();
            ImageIcon barcode_icon = new ImageIcon(barcode_image_buffered);

            JLabel barcode_label = new JLabel();
            barcode_label.setIcon(barcode_icon); // Set gambar barcode pada JLabel

            this.barcode.removeAll(); // Hapus semua komponen di panel barcodePanel
            this.barcode.add(barcode_label, BorderLayout.CENTER); // Tambahkan komponen barcode_label pada panel
            this.barcode.revalidate(); // Lakukan validasi ulang pada panel untuk menampilkan perubahan
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ID_PRODUK() {

        try {
            String sql = "SELECT id_produk FROM tbl_produk order by id_produk desc";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String Idno = rs.getString("id_produk").substring(1);
                String AN = "" + (Integer.parseInt(Idno) + 1);
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "0000";
                } else if (AN.length() == 2) {
                    Nol = "000";
                } else if (AN.length() == 3) {
                    Nol = "00";
                } else if (AN.length() == 4) {
                    Nol = "0";
                }

                tidproduk.setText("P" + Nol + AN);
            } else {
                tidproduk.setText("P00001");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void clear() {
        ID_PRODUK();
        tnama.setText("");
        tharga.setText("");
    }

    private void tabelProduk() {
        //membuat tampilan
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Harga");

        //menampilkan data database kedalam tabel
        try {
            String sql = "SELECT * FROM tbl_produk";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3)});
            }
            tblhasil.setModel(model);
        } catch (Exception e) {

        }
    }

    /**
     * Creates new form produk
     */
    public produk() {
        initComponents();
        tabelProduk();
        ID_PRODUK();
        barcode();
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
        bedit = new javax.swing.JButton();
        btambah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        tharga = new javax.swing.JTextField();
        tidproduk = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblhasil = new javax.swing.JTable();
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
        barcode = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        popup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        popup.setAlwaysOnTop(true);
        popup.setLocation(new java.awt.Point(400, 150));
        popup.setMinimumSize(new java.awt.Dimension(500, 500));
        popup.setResizable(false);
        popup.setSize(new java.awt.Dimension(500, 500));

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design3/popup produk.png"))); // NOI18N
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3);

        popup.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PRODUK");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bedit.setBorder(null);
        bedit.setContentAreaFilled(false);
        bedit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        getContentPane().add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 530, 120, 50));

        btambah.setBorder(null);
        btambah.setContentAreaFilled(false);
        btambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });
        getContentPane().add(btambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 530, 130, 50));

        bhapus.setBorder(null);
        bhapus.setContentAreaFilled(false);
        bhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        getContentPane().add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 530, 120, 50));

        tharga.setBackground(new java.awt.Color(217, 217, 217));
        tharga.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tharga.setToolTipText("");
        tharga.setAlignmentX(2.0F);
        tharga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tharga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                thargaKeyTyped(evt);
            }
        });
        getContentPane().add(tharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 510, 50));

        tidproduk.setBackground(new java.awt.Color(217, 217, 217));
        tidproduk.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tidproduk.setToolTipText("");
        tidproduk.setAlignmentX(2.0F);
        tidproduk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(tidproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 510, 40));

        tnama.setBackground(new java.awt.Color(217, 217, 217));
        tnama.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tnama.setToolTipText("");
        tnama.setAlignmentX(2.0F);
        tnama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamaActionPerformed(evt);
            }
        });
        getContentPane().add(tnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 510, 40));

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
        tblhasil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblhasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhasilMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblhasil);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, 550, 420));

        info.setText("?");
        info.setAlignmentY(0.0F);
        info.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoActionPerformed(evt);
            }
        });
        getContentPane().add(info, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, -1));

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

        barcode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barcodeMouseClicked(evt);
            }
        });
        getContentPane().add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, 400, 100));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design5/Produk.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        if (tidproduk.getText().equals("") || tnama.getText().equals("") || tharga.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "ISI SEMUA DATA TERLEBIH DAHULU");
        } else {
            try {
                String sql = "INSERT INTO tbl_produk VALUES ('" + tidproduk.getText() + "','"
                        + tnama.getText() + "','" + tharga.getText() + "')";
                java.sql.Connection conn = (Connection) koneksi.getKoneksi();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "penyimpanan Berhasil");
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            tabelProduk();
        }

    }//GEN-LAST:event_btambahActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        int jawab = JOptionPane.showConfirmDialog(null, "APAKAH ANDA INGIN MENGAHPUS", "HAPUS", JOptionPane.YES_NO_OPTION);

        switch (jawab) {
            case JOptionPane.YES_OPTION:
                if (tidproduk.getText().equals("") || tnama.getText().equals("") || tharga.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "ISI SEMUA DATA TERLEBIH DAHULU");
                } else {
                    try {
                        String sql = "DELETE from tbl_produk where id_produk ='" + tidproduk.getText() + "'";
                        java.sql.Connection conn = (Connection) koneksi.getKoneksi();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(this, "Berhasil Menghapus Data");
                        clear();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "TIDAK DAPAT MENGHAPUS PRODUK");
                    }
                    tabelProduk();
                    btambah.setVisible(true);
                }
                break;
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if (tidproduk.getText().equals("") || tnama.getText().equals("") || tharga.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "ISI SEMUA DATA TERLEBIH DAHULU");
        } else {
            try {
                String sql = "Update tbl_produk SET nama_produk='" + tnama.getText()
                        + "',harga='" + tharga.getText() + "' where id_produk='" + tidproduk.getText() + "'";
                java.sql.Connection conn = (Connection) koneksi.getKoneksi();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diedit");
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Perubahan data gagal" + e.getMessage());
            }
            tabelProduk();
            btambah.setVisible(true);
        }
    }//GEN-LAST:event_beditActionPerformed

    private void tblhasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhasilMouseClicked
        try {
            String idProduk = tblhasil.getValueAt(tblhasil.getSelectedRow(), 0).toString();
            String sql = "SELECT * FROM tbl_produk where id_produk='" + idProduk + "'";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                tidproduk.setText(rs.getString("id_produk"));
                tnama.setText(rs.getString("nama_produk"));
                tharga.setText(rs.getString("harga"));

            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());

        }
        tabelProduk();
        barcode();
    }//GEN-LAST:event_tblhasilMouseClicked

    private void tnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamaActionPerformed
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Harga");

        try {
            String nama = tnama.getText();
            String sql = "SELECT * FROM tbl_produk WHERE nama_produk LIKE '%" + nama + "%'";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            System.out.println(sql);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            tblhasil.setModel(model);

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
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

    private void barcodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barcodeMouseClicked
        try {
            bc.renderBarcode("E:\\barcode\\"+this.tnama.getText()+".png");
            JOptionPane.showMessageDialog(this, "berhasil menyimpan");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "gagal menyimpan");
        }
    }//GEN-LAST:event_barcodeMouseClicked

    private void thargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_thargaKeyTyped
 if(Character.isAlphabetic(evt.getKeyChar())){
evt.consume();
 }        // TODO add your handling code here:
    }//GEN-LAST:event_thargaKeyTyped

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
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new produk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barcode;
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
    private javax.swing.JTable tblhasil;
    private javax.swing.JTextField tharga;
    private javax.swing.JTextField tidproduk;
    private javax.swing.JTextField tnama;
    // End of variables declaration//GEN-END:variables
}
