package umkmOmahOmben;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Lenovo
 */
public class barangmasuk extends javax.swing.JFrame {

    private String idTransaksi;

    private int jumlahBarang() {
        int row = tblhasil.getRowCount();
        int jumlahBarang = 0;
        try {
            for (int i = 0; i < row; i++) {
                String a = tblhasil.getValueAt(i, 3).toString();
                int b = Integer.valueOf(a);
                jumlahBarang += b;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + " jumlah barang");
        }
        return jumlahBarang;
    }

    private int hargaTotal() {
        int row = tblhasil.getRowCount();
        int hargaTotal = 0;
        try {
            for (int i = 0; i < row; i++) {
                String a = tblhasil.getValueAt(i, 4).toString();
                int b = Integer.valueOf(a);
                hargaTotal += b;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + " total barang");
        }
        return hargaTotal;
    }

    private void listgay() {
        try {
            String sql = "SELECT * From tbl_pegawai ";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement pst = conn.createStatement();
            java.sql.ResultSet res = pst.executeQuery(sql);

            while (res.next()) {
                //menambahkan pilihan
//                pilgay.addItem(res.getString("id_pegawai") + " " + res.getString("nama_pegawai"));

            }

        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());

        }
    }

    private void ID_TRANSAKSI() {
        try {
            String sql = "SELECT id_transaksi FROM tbl_brgmsk order by id_transaksi desc";
            //memanggil id transaksi dengan nilai paling besar di atas
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {//karena maka hanya 1 yang diambil
                //yang diambil yang nilainya terbesar
                String Idno = rs.getString("id_transaksi").substring(3);
                String AN = "" + (Integer.parseInt(Idno) + 1);
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "000000";
                } else if (AN.length() == 2) {
                    Nol = "00000";
                } else if (AN.length() == 3) {
                    Nol = "0000";
                } else if (AN.length() == 4) {
                    Nol = "000";
                } else if (AN.length() == 5) {
                    Nol = "00";
                } else if (AN.length() == 6) {
                    Nol = "0";
                } else if (AN.length() == 7) {
                    Nol = "";
                }

                tidtransaksi.setText("BRG" + Nol + AN);
                idTransaksi = ("BRG" + Nol + AN);
            } else {
                tidtransaksi.setText("BRG0000001");
                idTransaksi = "BRG0000001";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " pada ID_TRANSAKSI");
        }
    }

    public void kalender() {
        Date ys = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

        ttanggal.setText(fm.format(ys));

    }

    

//    private void ID_BARANG() {
//        try {
//            String sql = "SELECT id_barang FROM tbl_brgmsk order by id_barang desc";
//            Connection conn = (Connection) koneksi.getKoneksi();
//            java.sql.Statement stm = conn.createStatement();
//            java.sql.ResultSet rs = stm.executeQuery(sql);
//
//            if (rs.next()) {
//                String Idno = rs.getString("id_barang").substring(1);
//                String AN = "" + (Integer.parseInt(Idno) + 1);
//                String Nol = "";
//                if (AN.length() == 1) {
//                    Nol = "00000";
//                } else if (AN.length() == 2) {
//                    Nol = "0000";
//                } else if (AN.length() == 3) {
//                    Nol = "000";
//                } else if (AN.length() == 4) {
//                    Nol = "00";
//                } else if (AN.length() == 4) {
//                    Nol = "0";
//                } else if (AN.length() == 5) {
//                    Nol = "";
//                }
//
//                tidbarang.setText("B" + Nol + AN);
//            } else {
//                tidbarang.setText("B000001");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
    private void clear() {
        ID_TRANSAKSI();
//        ID_BARANG();
        tnamabarang.setText(null);
        ttotalharga.setText(null);
        tharga.setText(null);
        ttanggal.setText(null);
        tjumlahbarang.setText(null);
        kalender();
    }

//    private void load_table() {
//        //membuat tampilan
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("Id Transaksi");
//        model.addColumn("Id Pegawai");
//        model.addColumn("Id Suplier");
//        model.addColumn("Id Barang");
//        model.addColumn("Nama Barang");
//        model.addColumn("Harga Barang");
//        model.addColumn("Jumlah Barang");
//        model.addColumn("Total Harga");
//        model.addColumn("Tanggal");
//
//        //menampilkan data database kedalam tabel
//        try {
//            String sql = "SELECT * FROM tbl_brgmsk order by id_transaksi desc";
//            Connection conn = (Connection) koneksi.getKoneksi();
//            java.sql.Statement st = conn.createStatement();
//            java.sql.ResultSet res = st.executeQuery(sql);
//            while (res.next()) {
//                //mengambil data dan menaruh ke tabel sesuai urutan 
//                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3),
//                    res.getString(4), res.getString(5), res.getString(6), res.getString(7),
//                    res.getString(8), res.getString(9)});
//            }
//            tblhasil.setModel(model);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        bhapus.setVisible(false);
//        bedit.setVisible(false);
//        btambah.setVisible(true);
//    }
    private void tabelSuplier() {
        //membuat model tabel
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("ID Suplier");
        model1.addColumn("Nama Suplier");
        model1.addColumn("NO. TELP");
        model1.addColumn("Alamat");

        //memanggil data dalam tabel mysql
        try {
            String sql = "Select *FROM tbl_suplier";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                model1.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4)});
            }
            tblsp.setModel(model1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    private void hapusTabel() {

        DefaultTableModel model = (DefaultTableModel) tblhasil.getModel();
        int colom = tblhasil.getSelectedRow();

        model.setRowCount(0);

        tblhasil.revalidate();
        tblhasil.repaint();

    }

    /**
     * Creates new form barangmasuk
     */
    public barangmasuk() {
        initComponents();
        ID_TRANSAKSI();
//        ID_BARANG();
//        this.load_table();
        kalender();
        tabelSuplier();
        listgay();
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
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bsimpan = new javax.swing.JButton();
        tnamabarang = new javax.swing.JTextField();
        tidtransaksi = new javax.swing.JTextField();
        tidbarang = new javax.swing.JTextField();
        ttotalharga = new javax.swing.JTextField();
        tjumlahbarang = new javax.swing.JTextField();
        tidsuplier = new javax.swing.JTextField();
        ttanggal = new javax.swing.JTextField();
        btambah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblhasil = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblsp = new javax.swing.JTable();
        tharga = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        bmember = new javax.swing.JButton();
        bdashboard = new javax.swing.JButton();
        bpegawai = new javax.swing.JButton();
        bproduk = new javax.swing.JButton();
        bsuplier = new javax.swing.JButton();
        bmember1 = new javax.swing.JButton();
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
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 240, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design4/informasi Pembelian.png"))); // NOI18N
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        popup.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TRANSAKSI PENJUALAN");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bsimpan.setAlignmentY(0.0F);
        bsimpan.setContentAreaFilled(false);
        bsimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
            }
        });
        getContentPane().add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 370, 120, 50));

        tnamabarang.setBackground(new java.awt.Color(217, 217, 217));
        tnamabarang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tnamabarang.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tnamabarang.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamabarangActionPerformed(evt);
            }
        });
        getContentPane().add(tnamabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, 180, 30));

        tidtransaksi.setBackground(new java.awt.Color(217, 217, 217));
        tidtransaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tidtransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tidtransaksi.setDoubleBuffered(true);
        tidtransaksi.setMargin(new java.awt.Insets(2, 10, 2, 10));
        tidtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidtransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(tidtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 180, 30));

        tidbarang.setBackground(new java.awt.Color(217, 217, 217));
        tidbarang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tidbarang.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tidbarang.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tidbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidbarangActionPerformed(evt);
            }
        });
        tidbarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tidbarangKeyReleased(evt);
            }
        });
        getContentPane().add(tidbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 180, 30));

        ttotalharga.setBackground(new java.awt.Color(217, 217, 217));
        ttotalharga.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ttotalharga.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ttotalharga.setMargin(new java.awt.Insets(2, 10, 2, 6));
        ttotalharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttotalhargaActionPerformed(evt);
            }
        });
        ttotalharga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ttotalhargaKeyTyped(evt);
            }
        });
        getContentPane().add(ttotalharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, 180, 30));

        tjumlahbarang.setBackground(new java.awt.Color(217, 217, 217));
        tjumlahbarang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tjumlahbarang.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tjumlahbarang.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tjumlahbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tjumlahbarangActionPerformed(evt);
            }
        });
        tjumlahbarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tjumlahbarangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tjumlahbarangKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tjumlahbarangKeyTyped(evt);
            }
        });
        getContentPane().add(tjumlahbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 270, 180, 30));

        tidsuplier.setBackground(new java.awt.Color(217, 217, 217));
        tidsuplier.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tidsuplier.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tidsuplier.setDoubleBuffered(true);
        tidsuplier.setMargin(new java.awt.Insets(2, 10, 2, 10));
        tidsuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidsuplierActionPerformed(evt);
            }
        });
        getContentPane().add(tidsuplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 180, 30));

        ttanggal.setBackground(new java.awt.Color(217, 217, 217));
        ttanggal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ttanggal.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ttanggal.setDoubleBuffered(true);
        ttanggal.setMargin(new java.awt.Insets(2, 10, 2, 10));
        ttanggal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ttanggalMouseClicked(evt);
            }
        });
        ttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttanggalActionPerformed(evt);
            }
        });
        getContentPane().add(ttanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 180, 30));

        btambah.setContentAreaFilled(false);
        btambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });
        getContentPane().add(btambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 130, 50));

        bhapus.setContentAreaFilled(false);
        bhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        getContentPane().add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 120, 50));

        bedit.setContentAreaFilled(false);
        bedit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        getContentPane().add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 120, 50));

        tblhasil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Barang", "Nama Barang", "Harga Barang", "Jumlah Barang", "Total Harga", "Tanggal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 1260, 280));

        tblsp.setModel(new javax.swing.table.DefaultTableModel(
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
        tblsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblspMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblsp);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 140, 370, 210));

        tharga.setBackground(new java.awt.Color(217, 217, 217));
        tharga.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tharga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                thargaKeyTyped(evt);
            }
        });
        getContentPane().add(tharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, 180, 30));

        jButton1.setText("?");
        jButton1.setAlignmentY(0.0F);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, -1, -1));

        bmember.setContentAreaFilled(false);
        bmember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmemberActionPerformed(evt);
            }
        });
        getContentPane().add(bmember, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 110, 100));

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

        bmember1.setContentAreaFilled(false);
        bmember1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bmember1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmember1ActionPerformed(evt);
            }
        });
        getContentPane().add(bmember1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 110, 100));

        bbarangmasuk.setContentAreaFilled(false);
        bbarangmasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bbarangmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbarangmasukActionPerformed(evt);
            }
        });
        getContentPane().add(bbarangmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 110, 100));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design5/Transaksi pembelian.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tnamabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamabarangActionPerformed
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("Id Transaksi");
//        model.addColumn("Id Pegawai");
//        model.addColumn("Id Suplier");
//        model.addColumn("Id Barang");
//        model.addColumn("Nama Barang");
//        model.addColumn("Harga Barang");
//        model.addColumn("Jumlah Barang");
//        model.addColumn("Total Harga");
//        model.addColumn("Tanggal");
//
//        try {
//            String sql = "Select * FROM tbl_brgmsk  where nama_barang LIKE '%" + tnamabarang.getText() + "%' "
//                    + "group by id_transaksi ";
//            Connection conn = (Connection) koneksi.getKoneksi();
//            java.sql.Statement st = conn.createStatement();
//            java.sql.ResultSet res = st.executeQuery(sql);
//
//            while (res.next()) {
//                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9)});
//            }
//            tblhasil.setModel(model);//menampilkan atau menempelkan data ke tabel yang sudah kita buat
//
//        } catch (SQLException | ClassNotFoundException ex) {
//            JOptionPane.showMessageDialog(this, ex.getMessage());
//
//        }
    }//GEN-LAST:event_tnamabarangActionPerformed

    private void tidsuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidsuplierActionPerformed
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("ID Suplier");
        model1.addColumn("Nama Suplier");
        model1.addColumn("NO. TELP");
        model1.addColumn("Alamat");

        try {
            String sql = "Select * FROM tbl_suplier WHERE nama_suplier LIKE '%" + tidsuplier.getText() + "%'"
                    + " group by id_suplier";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);

            while (res.next()) {
                model1.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4)});
            }
            tblsp.setModel(model1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tidsuplierActionPerformed

    private void ttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttanggalActionPerformed
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Id Pegawai");
        model.addColumn("Id Suplier");
        model.addColumn("Id Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga Barang");
        model.addColumn("Jumlah Barang");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");

        try {
            String tanggal = ttanggal.getText();
            String sql = "select * from tbl_brgmsk where tanggal = '" + tanggal + "' "
                    + "group by id_transaksi";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                    rs.getString(8), rs.getString(9)});
            }
            tblhasil.setModel(model);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }//GEN-LAST:event_ttanggalActionPerformed

    private void tidbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tidbarangActionPerformed

    private void ttotalhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttotalhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttotalhargaActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
//        try {
//            String idpegawai = pilgay.getSelectedItem().toString().substring(0, 6);
//            String sql = "UPDATE `tbl_brgmsk` SET `id_transaksi`='" + tidtransaksi.getText() + "',`id_pegawai`='" + idpegawai
//                    + "',`id_suplier`='" + tidsuplier.getText()
//                    + "',`id_barang`='" + tidbarang.getText() + "',`nama_barang`='" + tnamabarang.getText() + "', `harga` ='" + tharga.getText()
//                    + "',`jumlah_barang`='" + tjumlahbarang.getText() + "',`harga_total`='" + ttotalharga.getText()
//                    + "',`tanggal`='" + ttanggal.getText() + "' WHERE id_transaksi ='" + tidtransaksi.getText() + "'";
//            java.sql.Connection conn = (Connection) koneksi.getKoneksi();
//            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Data Berhasil Diedit");
//            clear();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Perubahan data gagal" + e.getMessage());
//        }
//        load_table();
//        btambah.setVisible(true);
//        clear();
        DefaultTableModel model = (DefaultTableModel) tblhasil.getModel();
        model.removeRow(tblhasil.getSelectedRow());
        model.addRow(new Object[]{tidbarang.getText(),
            tnamabarang.getText(), tharga.getText(),
            tjumlahbarang.getText(), ttotalharga.getText(), ttanggal.getText()});

        clear();
    }//GEN-LAST:event_beditActionPerformed

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
//        if (tidbarang.getText().equals("") || tnamabarang.getText().equals("") || ttanggal.getText().equals("") || tjumlahbarang.getText().equals("") || ttotalharga.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "ISI SEMUA DATA TERLEBIH DAHULU");
//        } else {
//            String namabarang = tnamabarang.getText().toString();
//            String nama = null;
//            String tgl = ttanggal.getText().toString();
//            String harga = this.tharga.getText();
//            int total = Integer.parseInt(tjumlahbarang.getText());
//            String tgl1 = null;
//            String id = null;
//            String pd = null;
//            String tanggal = null;
//            int tbarang = 0;
//            int tharga = 0;
//            String harga1 = null;
//            
//            
//
//            try {
//                String sql = "SELECT *, max(id_transaksi) as id from tbl_brgmsk "
//                             +"group by id_transaksi  "
//                             +"order by id desc";
//                            
//                Connection con = (Connection) koneksi.getKoneksi();
//                Statement st = con.createStatement();
//                ResultSet rs = st.executeQuery(sql);
//
//                if (rs.next()) {
//                    id = rs.getString("id");
//                    nama = rs.getString("nama_barang");
//                    tgl1 = rs.getString("tanggal");
//                    tbarang = Integer.parseInt(rs.getString("jumlah_barang"));
//                    tharga = Integer.parseInt(rs.getString("harga_total"));
//                    harga1 = rs.getString("harga");
//
//                    
//
//                    rs.close();
//                    st.close();
//
//                }
//            } catch (SQLException | ClassNotFoundException e) {
//                
//            }
//           
//           
//           
//           
//            if ( harga.equals(harga1) && namabarang.equals(nama) && tgl.equals(tgl1)) {

//                try {
//                    String idPegawai = pilgay.getSelectedItem().toString().substring(0, 6);
//                    String query1 = "UPDATE tbl_brgmsk SET id_pegawai ='" + idPegawai + "', jumlah_barang ='" + (total + tbarang)
//                            + "', harga_total ='" + (Integer.parseInt(harga) + tharga) + "', tanggal = '"+tgl+"' where id_transaksi ='" + id + "'";
//                    Connection conn = (Connection) koneksi.getKoneksi();
//                    java.sql.PreparedStatement pst = conn.prepareStatement(query1);
//                    pst.execute();
//                    
//                    JOptionPane.showMessageDialog(null, "penyimpanan Berhasil");
//                } catch (SQLException | ClassNotFoundException e) {
//                    JOptionPane.showMessageDialog(null, e.getMessage());
//                }
//            } else {
//                try {
//                    String idpegawai = pilgay.getSelectedItem().toString().substring(0, 6);
//                    String query2 = "INSERT INTO tbl_brgmsk VALUES ('" + tidtransaksi.getText() + "','" + idpegawai + "','"
//                            + tidsuplier.getText() + "','" + tidbarang.getText() + "','"
//                            + tnamabarang.getText() + "','" + this.tharga.getText() + "','" + tjumlahbarang.getText()
//                            + "','" + ttotalharga.getText() + "','" + ttanggal.getText() + "')";
//                    
//                    Connection conn = (Connection) koneksi.getKoneksi();
//                    java.sql.PreparedStatement pst = conn.prepareStatement(query2);
//                    pst.execute();
//                    
//                    JOptionPane.showMessageDialog(null, "penyimpanan Berhasil");
//                    
//                } catch (SQLException | ClassNotFoundException e) {
//                    JOptionPane.showMessageDialog(this, e.getMessage());
//                }
//            }
//        }
        DefaultTableModel model = (DefaultTableModel) tblhasil.getModel();
        model.addRow(new Object[]{tidbarang.getText(),
            tnamabarang.getText(), tharga.getText(),
            tjumlahbarang.getText(), ttotalharga.getText(), ttanggal.getText()});

        clear();
//        load_table();
//        clear();
    }//GEN-LAST:event_btambahActionPerformed

    private void tblhasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhasilMouseClicked
        String idBarang = tblhasil.getValueAt(tblhasil.getSelectedRow(), 0).toString();
        String nama = tblhasil.getValueAt(tblhasil.getSelectedRow(), 1).toString();
        String harga = tblhasil.getValueAt(tblhasil.getSelectedRow(), 2).toString();
        String jumlah = tblhasil.getValueAt(tblhasil.getSelectedRow(), 3).toString();
        String total = tblhasil.getValueAt(tblhasil.getSelectedRow(), 4).toString();
        String tanggal = tblhasil.getValueAt(tblhasil.getSelectedRow(), 5).toString();
        tidbarang.setText(idBarang);
        tnamabarang.setText(nama);
        tharga.setText(harga);
        tjumlahbarang.setText(jumlah);
        ttotalharga.setText(total);
        ttanggal.setText(tanggal);
//        try {
//            String idBarang = tblhasil.getValueAt(tblhasil.getSelectedRow(), 0).toString();
//            String sql = "SELECT *, tbl_pegawai.id_pegawai, tbl_pegawai.nama_pegawai, tbl_suplier.id_suplier "
//                    + "FROM tbl_brgmsk "
//                    + "JOIN tbl_pegawai "
//                    + "ON tbl_pegawai.id_pegawai = tbl_brgmsk.id_pegawai "
//                    + "JOIN tbl_suplier "
//                    + "ON tbl_suplier.id_suplier = tbl_brgmsk.id_suplier "
//                    + "WHERE id_transaksi = '" + idBarang + "'";
//            Connection conn = (Connection) koneksi.getKoneksi();
//            java.sql.Statement st = conn.createStatement();
//            java.sql.ResultSet res = st.executeQuery(sql);
//            if (res.next()) {
//
////                this.pilgay.setSelectedItem(res.getString("tbl_pegawai.id_pegawai") + " " + res.getString("tbl_pegawai.nama_pegawai"));
//                this.tidtransaksi.setText(res.getString("id_transaksi"));
//                this.tnamabarang.setText(res.getString("nama_barang"));
//                this.ttanggal.setText(res.getString("tanggal"));
//                this.tjumlahbarang.setText(res.getString("jumlah_barang"));
//                this.tharga.setText(res.getString("harga"));
//                tidsuplier.setText(res.getString("id_suplier"));
//                this.ttotalharga.setText(res.getString("harga_total"));
//                tidbarang.setText(res.getString("id_barang"));
//            }
//
//        } catch (SQLException | ClassNotFoundException ex) {
//            JOptionPane.showMessageDialog(this, ex.getMessage());
//        }
//        load_table();
//        btambah.setVisible(false);
//        bhapus.setVisible(true);
//        bedit.setVisible(true);
//

    }//GEN-LAST:event_tblhasilMouseClicked

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
//int jawab = JOptionPane.showConfirmDialog(null, "APAKAH ANDA INGIN MENGAHPUS", "HAPUS", JOptionPane.YES_NO_OPTION);
//
//        switch (jawab) {
//            case JOptionPane.YES_OPTION:
//                
//                try {
//            String sql = "DELETE FROM tbl_brgmsk WHERE id_transaksi ='" + tidtransaksi.getText() + "'";
//            java.sql.Connection conn = (Connection) koneksi.getKoneksi();
//            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//            pst.execute();
//            JOptionPane.showMessageDialog(this, "Berhasil Menghapus Data");
//            clear();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//        load_table();
//        break;
//        }
        DefaultTableModel model = (DefaultTableModel) tblhasil.getModel();
        int colom = tblhasil.getSelectedRow();

        System.out.println(colom);
        model.removeRow(tblhasil.getSelectedRow());
        clear();
    }//GEN-LAST:event_bhapusActionPerformed

    private void ttanggalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ttanggalMouseClicked

    }//GEN-LAST:event_ttanggalMouseClicked

    private void tblspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblspMouseClicked
        try {
            String idBarang = tblsp.getValueAt(tblsp.getSelectedRow(), 0).toString();
            String sql = "SELECT * FROM tbl_suplier where id_suplier ='" + idBarang + "'";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                this.tidsuplier.setText(res.getString("id_suplier"));

            }

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tblspMouseClicked

    private void tidtransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidtransaksiActionPerformed

    }//GEN-LAST:event_tidtransaksiActionPerformed

    private void tjumlahbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tjumlahbarangActionPerformed
        int harga = Integer.parseInt(tharga.getText());
        int total = Integer.parseInt(tjumlahbarang.getText());

        this.ttotalharga.setText(String.valueOf(harga * total));
    }//GEN-LAST:event_tjumlahbarangActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        popup.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void bmemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmemberActionPerformed
        new member().setVisible(true);
        dispose();
    }//GEN-LAST:event_bmemberActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        try {

            String sql = "Insert into `tbl_brgmsk` VALUES ('" + idTransaksi + "','" + login.idPegawai + "','" + tidsuplier.getText() + "','" + jumlahBarang() + "','" + hargaTotal() + "','"
                    + ttanggal.getText() + "')";
            java.sql.Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            System.out.println(sql);
            System.out.println(login.idPegawai + " " + jumlahBarang() + " " + hargaTotal());

            for (int i = 0; i < tblhasil.getRowCount(); i++) {
                String sql1 = "INSERT INTO `tbl_brgmsk_detail`(`id_transaksi`, `id_barang`, `nama_barang`, `harga_barang`, `jumlah_barang`, `total_harga`) VALUES"
                        + "('" + idTransaksi + "','" + tblhasil.getValueAt(i, 0).toString() + "','"
                        + tblhasil.getValueAt(i, 1).toString() + "','" + tblhasil.getValueAt(i, 2).toString() + "','" + tblhasil.getValueAt(i, 3).toString() + "','"
                        + tblhasil.getValueAt(i, 4).toString() + "')";
                System.out.println(sql1);
                java.sql.PreparedStatement ps = conn.prepareStatement(sql1);
                ps.execute();

            }
            JOptionPane.showMessageDialog(null, "Penympanan berhasil!");
            hapusTabel();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        DefaultTableModel model = (DefaultTableModel) tblhasil.getModel();

        clear();

    }//GEN-LAST:event_bsimpanActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new hapuspembelian().setVisible(true);
        popup.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void bmember1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmember1ActionPerformed
        new member().setVisible(true);
        dispose();
    }//GEN-LAST:event_bmember1ActionPerformed

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

    private void tidbarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tidbarangKeyReleased
        try {
            String id = tidbarang.getText();
            String sql = "select * from tbl_brgmsk_detail where id_barang = '" + id + "' ";
                    
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                tnamabarang.setText(rs.getString("nama_barang"));
                tharga.setText(rs.getString("harga_barang"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "error pada JtextFiled id barang!");

        }
    }//GEN-LAST:event_tidbarangKeyReleased

    private void tjumlahbarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahbarangKeyReleased
        // TODO add your handling code here:
      int harga = Integer.parseInt(tharga.getText());
        int total = Integer.parseInt(tjumlahbarang.getText());

        this.ttotalharga.setText(String.valueOf(harga * total));
    }//GEN-LAST:event_tjumlahbarangKeyReleased

    private void tjumlahbarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahbarangKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tjumlahbarangKeyPressed

    private void thargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_thargaKeyTyped
 if(Character.isAlphabetic(evt.getKeyChar())){
evt.consume();
 }        // TODO add your handling code here:
    }//GEN-LAST:event_thargaKeyTyped

    private void tjumlahbarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahbarangKeyTyped
 if(Character.isAlphabetic(evt.getKeyChar())){
evt.consume();
 }        // TODO add your handling code here:
    }//GEN-LAST:event_tjumlahbarangKeyTyped

    private void ttotalhargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ttotalhargaKeyTyped
 if(Character.isAlphabetic(evt.getKeyChar())){
evt.consume();
 }        // TODO add your handling code here:
    }//GEN-LAST:event_ttotalhargaKeyTyped

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
            java.util.logging.Logger.getLogger(barangmasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barangmasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barangmasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barangmasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new barangmasuk().setVisible(true);
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
    private javax.swing.JButton bmember1;
    private javax.swing.JButton bpegawai;
    private javax.swing.JButton bpenjualan;
    private javax.swing.JButton bproduk;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton bsuplier;
    private javax.swing.JButton btambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton logout;
    private javax.swing.JDialog popup;
    private javax.swing.JTable tblhasil;
    private javax.swing.JTable tblsp;
    private javax.swing.JTextField tharga;
    private javax.swing.JTextField tidbarang;
    private javax.swing.JTextField tidsuplier;
    private javax.swing.JTextField tidtransaksi;
    private javax.swing.JTextField tjumlahbarang;
    private javax.swing.JTextField tnamabarang;
    private javax.swing.JTextField ttanggal;
    private javax.swing.JTextField ttotalharga;
    // End of variables declaration//GEN-END:variables
}
