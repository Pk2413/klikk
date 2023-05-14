/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umkmOmahOmben;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static umkmOmahOmben.transaksi.ttanggal;

/**
 *
 * @author Lenovo
 */
public class member extends javax.swing.JFrame {

    private void kalender() {
        Date ys = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

        ttanggal.setText(fm.format(ys));

    }

    void clear() {
        tidmember.setText("");
        tnama.setText("");
        talamat.setText("");
        tnotelp.setText("");
        tdiskon.setText("");
        kalender();
    }

    private void tblmember() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Member");
        model.addColumn("Nama Member");
        model.addColumn("NO_Telp");
        model.addColumn("Alamat");
        model.addColumn("Diskon");
        try {
            String sql = "Select * from tbl_member";
            Connection conn = (Connection) koneksi.getKoneksi();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
            tblhasil.setModel(model);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage() + ", ERROR PADA TABEL MEMBER");
        }
    }

    /**
     * Creates new form member
     */
    public member() {
        initComponents();
        tblmember();
        kalender();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblhasil = new javax.swing.JTable();
        tdiskon = new javax.swing.JTextField();
        tidmember = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        talamat = new javax.swing.JTextField();
        tnotelp = new javax.swing.JTextField();
        bedit = new javax.swing.JButton();
        btambah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblhasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhasilMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblhasil);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, 530, 520));

        tdiskon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tdiskon.setBorder(null);
        getContentPane().add(tdiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 480, 30));

        tidmember.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tidmember.setBorder(null);
        tidmember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidmemberActionPerformed(evt);
            }
        });
        getContentPane().add(tidmember, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 172, 480, 30));

        tnama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tnama.setBorder(null);
        tnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamaActionPerformed(evt);
            }
        });
        getContentPane().add(tnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 480, 30));

        talamat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        talamat.setBorder(null);
        getContentPane().add(talamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 480, 30));

        tnotelp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tnotelp.setBorder(null);
        getContentPane().add(tnotelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 405, 480, 30));

        bedit.setContentAreaFilled(false);
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        getContentPane().add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 620, 140, 70));

        btambah.setContentAreaFilled(false);
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });
        getContentPane().add(btambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 620, 140, 70));

        bhapus.setContentAreaFilled(false);
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        getContentPane().add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 620, 140, 70));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design5/Member.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnamaActionPerformed

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        if (tidmember.getText().equals("") || tnama.getText().equals("") || tnotelp.getText().equals("") || talamat.getText().equals("") || tdiskon.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "ISI SEMUA DATA TERLEBIH DAHULU");
        } else {

            try {
//                int nomer = Integer.parseInt(tnotelp.getText());
                if (tnotelp.getText().length() > 13 || tnotelp.getText().length() <= 8) {
                    JOptionPane.showMessageDialog(null, "Masukuan Nomer Telepon Dengan Benar", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
//                int nomer = Integer.parseInt(tnohp.getText());
                        String sql = "INSERT INTO tbl_member VALUES ('" + tidmember.getText() + "','"
                                + tnama.getText() + "','" + tnotelp.getText() + "','" + talamat.getText() + "','" 
                                + tdiskon.getText() + "')";

                        java.sql.Connection conn = (Connection) koneksi.getKoneksi();
                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "penyimpanan Berhasil");
                        clear();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Periksa ID Member dan Nomer Telepon!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                tblmember();
                clear();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Masukuan Nomer Telepon Dengan Benar", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btambahActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        int jawab = JOptionPane.showConfirmDialog(null, "APAKAH ANDA INGIN MENGAHPUS", "HAPUS", JOptionPane.YES_NO_OPTION);
        String idmember = tblhasil.getValueAt(tblhasil.getSelectedRow(), 0).toString();
        System.out.println(idmember);
        switch (jawab) {
            case JOptionPane.YES_OPTION:

                try {
                    
                    String sql = "DELETE from tbl_member where id_member ='" + idmember + "'";
                    java.sql.Connection conn = (Connection) koneksi.getKoneksi();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(this, "Berhasil Menghapus Data");
                    clear();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "TIDAK DAPAT MENGHAPUS PEGAWAI");
                }
                break;
        }
        tblmember();
    }//GEN-LAST:event_bhapusActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if (tidmember.getText().equals("") || tnama.getText().equals("") || tnotelp.getText().equals("") || talamat.getText().equals("") 
                || tdiskon.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "PILIH DATA TERLEBIH DAHULU");
        } else {
            try {
                String sql = "Update tbl_member SET nama_member='" + tnama.getText() + "',no_telp='" + tnotelp.getText()
                        + "',alamat='" + talamat.getText() + "', diskon = '"+tdiskon.getText()+"', tanggal = '"+ttanggal.getText()
                        + "' where id_member='" + tidmember.getText() + "'";
                java.sql.Connection conn = (Connection) koneksi.getKoneksi();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diedit");
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Perubahan data gagal" + e.getMessage());
            }
        }
        tblmember();
        clear();
    }//GEN-LAST:event_beditActionPerformed

    private void tblhasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhasilMouseClicked
        String id = tblhasil.getValueAt(tblhasil.getSelectedRow(), 0).toString();
        try{
            String  sql = " Select * From tbl_member where id_member = '"+id+"'";
            Connection conn = (Connection) koneksi.getKoneksi();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next()){
                tidmember.setText(rs.getString("id_member"));
                tnama.setText(rs.getString("nama_member"));
                tnotelp.setText(rs.getString("no_telp"));
                talamat.setText(rs.getString("alamat"));
                tdiskon.setText(rs.getString("diskon"));
                ttanggal.setText(rs.getString("tanggal"));
            }
        }catch(SQLException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(this, e.getMessage()+ ", ERROR PADA CLICK TABEL");
        }
        
        
    }//GEN-LAST:event_tblhasilMouseClicked

    private void tidmemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidmemberActionPerformed


//        Scanner data = new Scanner(System.in);
//        while (data.hasNextLine()) {
//            String input = data.nextLine();
//            // Replace jTextField1 with your JTextField name
//            tidmember.setText(input);
//        }
    }//GEN-LAST:event_tidmemberActionPerformed

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
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new member().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout;
    private javax.swing.JTextField talamat;
    private javax.swing.JTable tblhasil;
    private javax.swing.JTextField tdiskon;
    private javax.swing.JTextField tidmember;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tnotelp;
    // End of variables declaration//GEN-END:variables
}
