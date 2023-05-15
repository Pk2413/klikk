package umkmOmahOmben;

import com.onbarcode.barcode.jasper.JasperRenderer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import umkmOmahOmben.login;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Lenovo
 */
public class transaksi extends javax.swing.JFrame {

    private String idTransaksi;
    private String idPegawai;

//public void cetak(Connection conn, String idTransaksi) {
//    
//        try {
//            // menyiapkan id transaksi
//            HashMap parameter = new HashMap();
//            parameter.put("kode_transaksi", idTransaksi);
//
//            // meyiapkan jasper report
//            InputStream file = getClass().getResourceAsStream("report/report1.jrxml");
//            
//            JasperDesign desain = JRXmlLoader.load(file);
//            JasperReport report = JasperCompileManager.compileReport(desain);
//            JasperPrint print = JasperFillManager.fillReport(report, parameter, conn);
//
//            // membuka jasper report
//            JasperViewer jview = new JasperViewer(print, false);
//            jview.setTitle("Cetak Struk " + idTransaksi);
//            jview.setVisible(true);
//            jview.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//            jview.setLocationRelativeTo(null);
//            jview.setFitPageZoomRatio();
//
//            // solved bug jasper report tiba-tiba minimaze
//            jview.addWindowListener(new java.awt.event.WindowAdapter() {
//
//                // menutup jasper report saat user menekan tombol close
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    System.out.println("JASPER CLOSING");
//                    jview.dispose();
//                }
//
//                // menutup jasper report saat user menekan tombol close
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    System.out.println("JASPER CLOSED");
//                    jview.dispose();
//                }
//
//                // memaksa jasper report untuk tetap aktif (tidak minimaze)
//                @Override
//                public void windowDeactivated(WindowEvent e) {
//                    System.out.println("JASPER ACTIVATED");
//                    jview.setVisible(true);
//                }
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();           
////            Message.showException(null, e);
//        }
//}
    public void cetak(String idTransaksi) {
        try {
            Connection conn = (Connection) koneksi.getKoneksi();
            // Menyiapkan id transaksi
            HashMap parameter = new HashMap();
            parameter.put("kode_transaksi", idTransaksi);

            // Membaca file JasperDesign dari JRXML
            InputStream file = getClass().getResourceAsStream("/report/report1.jrxml");

            JasperDesign desain = JRXmlLoader.load(file);

            // Mengompilasi report dari JasperDesign
            JasperReport report = JasperCompileManager.compileReport(desain);

            // Mengisi report dengan data dan parameter
            JasperPrint print = JasperFillManager.fillReport(report, parameter, conn);

            // Menampilkan jasper viewer
            JasperViewer jview = new JasperViewer(print, false);
            jview.setTitle("Cetak Struk " + idTransaksi);
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

    private static void setid() {

    }

    private int jumlahBarang() {
        int row = tblhasil.getRowCount();
        int jumlahBarang = 0;
        for (int i = 0; i < row; i++) {
            String a = tblhasil.getValueAt(i, 2).toString();
            int b = Integer.valueOf(a);
            jumlahBarang += b;
        }
        return jumlahBarang;
    }

    private int hargaTotal() {
        int row = tblhasil.getRowCount();
        int hargaTotal = 0;
        for (int i = 0; i < row; i++) {
            String a = tblhasil.getValueAt(i, 3).toString();
            int b = Integer.valueOf(a);
            hargaTotal += b;
        }
        return hargaTotal;
    }

//    private void hitung(){
//         int row = tblhasil.getRowCount();
//        int jumlahBarang = 0;
//        for (int i = 0; i < row; i++) {
//            String a = tblhasil.getValueAt(i, 4).toString();
//            int b = Integer.valueOf(a);
//            jumlahBarang += b;
//        }
//        
//        
//        int hargaTotal = 0;
//        for (int i = 0; i < row; i++) {
//            String a = tblhasil.getValueAt(i, 4).toString();
//            int b = Integer.valueOf(a);
//            hargaTotal += b;
//        }
//    }
    private void harga() {
        DefaultTableModel model = (DefaultTableModel) tblhasil.getModel();
        int row = model.getRowCount();
        int jumlah = 0;
        for (int i = 0; i < row; i++) {
            String a = model.getValueAt(i, 3).toString();
            if (a.equals("")) {
                total.setText("0");
            } else {
                int b = Integer.valueOf(a);
                jumlah += b;
            }
        }

        total.setText(String.valueOf(jumlah));
    }

    private void hapusTabel() {

        DefaultTableModel model = (DefaultTableModel) tblhasil.getModel();
        int colom = tblhasil.getSelectedRow();

        model.setRowCount(0);

        tblhasil.revalidate();
        tblhasil.repaint();

    }

    private void kalender() {
        Date ys = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

        ttanggal.setText(fm.format(ys));

    }

    private void ID_TRANSAKSI() {
        try {
            String sql = "SELECT id_transaksi FROM tbl_transaksi order by id_transaksi desc";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String Idno = rs.getString("id_transaksi").substring(1);
                String AN = "" + (Integer.parseInt(Idno) + 1);
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "00000000";
                } else if (AN.length() == 2) {
                    Nol = "0000000";
                } else if (AN.length() == 3) {
                    Nol = "000000";
                } else if (AN.length() == 4) {
                    Nol = "00000";
                } else if (AN.length() == 5) {
                    Nol = "0000";
                } else if (AN.length() == 6) {
                    Nol = "000";
                } else if (AN.length() == 7) {
                    Nol = "00";
                } else if (AN.length() == 8) {
                    Nol = "0";
                } else if (AN.length() == 9) {
                    Nol = "";
                }
                idTransaksi = "T" + Nol + AN;
                tidtransaksi.setText("T" + Nol + AN);
            } else {
                tidtransaksi.setText("T000000001");
                idTransaksi = "T000000001";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " pada ID_TRANSAKSI");
        }
        System.out.println(idTransaksi);
    }

    private void load_table() {
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
            tblproduk.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + "tabelProduk");

        }
    }

    private void tblMember() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Member");
        model.addColumn("Nama");
        model.addColumn("Diskon");

        try {
            String id = "SELECT * FROM tbl_member";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(id);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(5)});
            }
            tblmember.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + ", ERROR PADA tblMember");
        }
    }

//    private void listgay() {
//        try {
//            String sql = "SELECT * From tbl_pegawai ";
//            Connection conn = (Connection) koneksi.getKoneksi();
//            java.sql.Statement pst = conn.createStatement();
//            java.sql.ResultSet res = pst.executeQuery(sql);
//
//            while (res.next()) {
//                pilgay.addItem(res.getString("id_pegawai") + " " + res.getString("nama_pegawai"));
//
//            }
//
//        } catch (SQLException | ClassNotFoundException ex) {
//            JOptionPane.showMessageDialog(this, ex.getMessage());
//
//        }
//    }
    private void tabelHasil(DefaultTableModel model) {
        //membuat tampilan
        model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Nama Pegawai");
        model.addColumn("Id Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Total Barang");
        model.addColumn("Total Harga");
        model.addColumn("tanggal");
        tblhasil.setModel(model);

        //menampilkan data database kedalam tabel
//        try {
//            String sql = "SELECT *, tbl_pegawai.nama_pegawai, tbl_produk.nama_produk "
//                    + "FROM tbl_transaksi "
//                    + "join tbl_pegawai "
//                    + "on tbl_transaksi.id_pegawai = tbl_pegawai.id_pegawai "
//                    + "join tbl_produk "
//                    + "on tbl_transaksi.id_produk = tbl_produk.id_produk "
//                    + "order by id_transaksi desc";
//            Connection conn = (Connection) koneksi.getKoneksi();
//            java.sql.Statement st = conn.createStatement();
//            java.sql.ResultSet res = st.executeQuery(sql);
//            while (res.next()) {
//                model.addRow(new Object[]{res.getString("id_transaksi"), res.getString("nama_pegawai"),
//                    res.getString("id_produk"), res.getString("nama_produk"),
//                    res.getString("total_barang"), res.getString("total_harga"), res.getString("tanggal")});
//            }
//            tblhasil.setModel(model);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage() + "pada tabelHasil1");
//        }
    }

    private void clear() {
        tidtransaksi.setText("");
        ttotalbarang.setText("");
        thrgbrg.setText("");
        tdiskon.setText("0");
        kalender();
        ID_TRANSAKSI();
        harga();
//        btambah.setVisible(true);
//        bhapus.setVisible(false);
//        bedit.setVisible(false);

    }

    public transaksi() {
        initComponents();
        load_table();
//        tabelHasil();
//        listgay();
        ID_TRANSAKSI();
        kalender();
        clear();
        tblMember();
        setid();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        info = new javax.swing.JDialog();
        bloghapus = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ttotalharga = new javax.swing.JTextField();
        bhitung1 = new javax.swing.JButton();
        bbayar = new javax.swing.JButton();
        bhitung = new javax.swing.JButton();
        total = new javax.swing.JTextField();
        tkembalian = new javax.swing.JTextField();
        tpembayaran = new javax.swing.JTextField();
        tidmember = new javax.swing.JTextField();
        tnamamember = new javax.swing.JTextField();
        tdiskon = new javax.swing.JTextField();
        thrgbrg = new javax.swing.JTextField();
        tidtransaksi = new javax.swing.JTextField();
        tidbarang = new javax.swing.JTextField();
        ttotalbarang = new javax.swing.JTextField();
        bhapus = new javax.swing.JButton();
        btambah = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblmember = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblproduk = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblhasil = new javax.swing.JTable();
        tnamabarang1 = new javax.swing.JTextField();
        ttanggal = new javax.swing.JTextField();
        bdashboard = new javax.swing.JButton();
        bpegawai = new javax.swing.JButton();
        bproduk = new javax.swing.JButton();
        bsuplier = new javax.swing.JButton();
        bmember = new javax.swing.JButton();
        bbarangmasuk = new javax.swing.JButton();
        bpenjualan = new javax.swing.JButton();
        blaporan = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Lid = new javax.swing.JLabel();

        info.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        info.setLocation(new java.awt.Point(400, 150));
        info.setResizable(false);
        info.setSize(new java.awt.Dimension(500, 500));
        info.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bloghapus.setContentAreaFilled(false);
        bloghapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloghapusActionPerformed(evt);
            }
        });
        info.getContentPane().add(bloghapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, 250, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design4/informasi penjualan.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(5010, 510));
        info.getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TRANSAKSI PENJUALAN");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ttotalharga.setBackground(new java.awt.Color(217, 217, 217));
        ttotalharga.setBorder(null);
        ttotalharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttotalhargaActionPerformed(evt);
            }
        });
        getContentPane().add(ttotalharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 190, 30));

        bhitung1.setText("print");
        bhitung1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhitung1ActionPerformed(evt);
            }
        });
        getContentPane().add(bhitung1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, 80, -1));

        bbayar.setContentAreaFilled(false);
        bbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbayarActionPerformed(evt);
            }
        });
        getContentPane().add(bbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 380, 130, 60));

        bhitung.setContentAreaFilled(false);
        bhitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhitungActionPerformed(evt);
            }
        });
        getContentPane().add(bhitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 370, 140, 70));

        total.setBackground(new java.awt.Color(217, 217, 217));
        total.setBorder(null);
        total.setEnabled(false);
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 390, 200, 40));

        tkembalian.setBackground(new java.awt.Color(217, 217, 217));
        tkembalian.setBorder(null);
        tkembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkembalianActionPerformed(evt);
            }
        });
        getContentPane().add(tkembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 290, 190, 30));

        tpembayaran.setBackground(new java.awt.Color(217, 217, 217));
        tpembayaran.setBorder(null);
        tpembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpembayaranActionPerformed(evt);
            }
        });
        tpembayaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tpembayaranKeyReleased(evt);
            }
        });
        getContentPane().add(tpembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 190, 30));

        tidmember.setBackground(new java.awt.Color(217, 217, 217));
        tidmember.setBorder(null);
        tidmember.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tidmemberFocusGained(evt);
            }
        });
        tidmember.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tidmemberInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        tidmember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidmemberActionPerformed(evt);
            }
        });
        getContentPane().add(tidmember, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 183, 190, 30));

        tnamamember.setBackground(new java.awt.Color(217, 217, 217));
        tnamamember.setBorder(null);
        tnamamember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamamemberActionPerformed(evt);
            }
        });
        getContentPane().add(tnamamember, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, 190, 30));

        tdiskon.setBackground(new java.awt.Color(217, 217, 217));
        tdiskon.setBorder(null);
        tdiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdiskonActionPerformed(evt);
            }
        });
        tdiskon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tdiskonKeyTyped(evt);
            }
        });
        getContentPane().add(tdiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 280, 190, 30));

        thrgbrg.setBackground(new java.awt.Color(217, 217, 217));
        thrgbrg.setBorder(null);
        thrgbrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thrgbrgActionPerformed(evt);
            }
        });
        getContentPane().add(thrgbrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 190, 30));

        tidtransaksi.setBackground(new java.awt.Color(217, 217, 217));
        tidtransaksi.setBorder(null);
        tidtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidtransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(tidtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 135, 190, 30));

        tidbarang.setBackground(new java.awt.Color(217, 217, 217));
        tidbarang.setBorder(null);
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
        getContentPane().add(tidbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 184, 190, 30));

        ttotalbarang.setBackground(new java.awt.Color(217, 217, 217));
        ttotalbarang.setBorder(null);
        ttotalbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttotalbarangActionPerformed(evt);
            }
        });
        ttotalbarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ttotalbarangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ttotalbarangKeyReleased(evt);
            }
        });
        getContentPane().add(ttotalbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 190, 30));

        bhapus.setBorder(null);
        bhapus.setContentAreaFilled(false);
        bhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });
        getContentPane().add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 120, 50));

        btambah.setBorder(null);
        btambah.setContentAreaFilled(false);
        btambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });
        getContentPane().add(btambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 120, 50));

        bedit.setBorder(null);
        bedit.setContentAreaFilled(false);
        bedit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });
        getContentPane().add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, 130, 50));

        tblmember.setModel(new javax.swing.table.DefaultTableModel(
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
        tblmember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmemberMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblmember);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 260, 240, 120));

        tblproduk.setModel(new javax.swing.table.DefaultTableModel(
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
        tblproduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblprodukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblproduk);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 130, 240, 130));

        tblhasil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Produk", "Nama Barang", "Jumlah Barang", "Total Harga", "Tanggal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        jScrollPane2.setViewportView(tblhasil);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 1240, 260));

        tnamabarang1.setBackground(new java.awt.Color(217, 217, 217));
        tnamabarang1.setBorder(null);
        tnamabarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamabarang1ActionPerformed(evt);
            }
        });
        getContentPane().add(tnamabarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 190, 30));

        ttanggal.setBackground(new java.awt.Color(217, 217, 217));
        ttanggal.setBorder(null);
        ttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttanggalActionPerformed(evt);
            }
        });
        getContentPane().add(ttanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 137, 190, 30));

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

        jButton1.setText("?");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 120, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design5/transaksi penjualan.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Lid.setText("jLabel3");
        getContentPane().add(Lid, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
//        if (tidtransaksi.getText().equals("") || tidbarang.getText().equals("")
//                || tnamabarang1.getText().equals("") || ttanggal.getText().equals("")
//                || ttotalbarang.getText().equals("") || thrgbrg.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "ISI SEMUA DATA TERLEBIH DAHULU");
//        } else {
//
//            String id = null; 
//            String pd = null;
//            String tanggal = null;
//            int tbarang = 0;
//            int tharga =0;
//            try {
//                String sql1 = "select id_transaksi as id, id_produk, total_barang, "
//                        + "total_harga, tanggal "
//                        + "from tbl_transaksi "
//                        + "group by id "
//                        + "order by id desc;";
//                Connection con = (Connection) koneksi.getKoneksi();
//                
//                java.sql.Statement st = con.createStatement();
//                java.sql.ResultSet rs = st.executeQuery(sql1);
//
//                if (rs.next()) {
//
//                    id = rs.getString("id");
//                    pd = rs.getString("id_produk");
//                    tanggal = rs.getString("tanggal");
//                    tbarang = Integer.parseInt(rs.getString("total_barang"));
//                    tharga = Integer.parseInt(rs.getString("total_harga"));
//                    
//                    
//                    rs.close();
//                    st.close();
//                    
//                }
//            } catch (SQLException | ClassNotFoundException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage());
//                
//            }
//            String idbarang=tidbarang.getText().toString();
//            String tgl = ttanggal.getText().toString();
//            
//            
//            if (idbarang.equals(pd) && tgl.equals(tanggal)) {
//
//                try {
//                    int total = Integer.parseInt(ttotalbarang.getText());
//                    int harga = Integer.parseInt(thrgbrg.getText());
//                    String idPegawai = pilgay.getSelectedItem().toString().substring(0, 6);
//                    String update = "update tbl_transaksi SET id_pegawai ='" + idPegawai + "',total_barang ='" + (total + tbarang) 
//                            + "', total_harga ='" + (harga + tharga) + "' where id_transaksi ='" + id + "'";
//                    java.sql.Connection con = (Connection) koneksi.getKoneksi();
//                    java.sql.PreparedStatement pst1 = con.prepareStatement(update);
//                    pst1.execute();
//                    JOptionPane.showMessageDialog(null, "Penyimpanan Berhasil");
//                } catch (SQLException | ClassNotFoundException e) {
//                    JOptionPane.showMessageDialog(null, e.getMessage());
//
//                }
//            } else  {
//
//                try {
//                    String idPegawai = pilgay.getSelectedItem().toString().substring(0, 6);
//                    String sql = "INSERT INTO tbl_transaksi VALUES ('" + tidtransaksi.getText() + "','"
//                            + idPegawai + "','" + tidbarang.getText() + "','"
//                            + ttotalbarang.getText() + "','" + thrgbrg.getText() + "','" + ttanggal.getText() + "')";
//                    java.sql.Connection conn = (Connection) koneksi.getKoneksi();
//                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//                    pst.execute();
//                    JOptionPane.showMessageDialog(null, "Penyimpanan Berhasil");
//                    clear();
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(this, e.getMessage());
//                }
//            
//            }
//
//        }
        DefaultTableModel model = (DefaultTableModel) tblhasil.getModel();
//        model.addColumn("Id Transaksi");
//        model.addColumn("Nama Pegawai");
//        model.addColumn("Id Produk");
//        model.addColumn("Nama Produk");
//        model.addColumn("Total Barang");
//        model.addColumn("Total Harga");
//        model.addColumn("tanggal");

//        int baris = tblhasil.getRowCount();
        model.addRow(new Object[]{tidbarang.getText(),
            tnamabarang1.getText(), ttotalbarang.getText(),
            thrgbrg.getText(), ttanggal.getText()});

        clear();

    }//GEN-LAST:event_btambahActionPerformed

    private void ttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttanggalActionPerformed
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Nama Pegawai");
        model.addColumn("Id Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Total Barang");
        model.addColumn("Total Harga");
        model.addColumn("tanggal");

        try {
            String tgl = ttanggal.getText();
            String sql = "SELECT *, tbl_pegawai.nama_pegawai, tbl_produk.nama_produk "
                    + "FROM tbl_transaksi "
                    + "join tbl_pegawai "
                    + "on tbl_transaksi.id_pegawai = tbl_pegawai.id_pegawai "
                    + "join tbl_produk "
                    + "on tbl_transaksi.id_produk = tbl_produk.id_produk "
                    + "where tbl_transaksi.tanggal = '" + tgl + "' "
                    + "order by id_transaksi desc";
            System.out.println(sql);
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString("id_transaksi"), res.getString("nama_pegawai"),
                    res.getString("id_produk"), res.getString("nama_produk"),
                    res.getString("total_barang"), res.getString("total_harga"), res.getString("tanggal")});
                tblhasil.setModel(model);
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_ttanggalActionPerformed

    private void tblprodukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblprodukMouseClicked
        try {
            String idBarang = tblproduk.getValueAt(tblproduk.getSelectedRow(), 0).toString();
            String sql = "SELECT * FROM tbl_produk where id_produk ='" + idBarang + "'";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                this.tidbarang.setText(res.getString("id_produk"));
                this.tnamabarang1.setText(res.getString("nama_produk"));

            }

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tblprodukMouseClicked

    private void thrgbrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thrgbrgActionPerformed


    }//GEN-LAST:event_thrgbrgActionPerformed

    private void tblhasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhasilMouseClicked
//        try {
        String idBarang = tblhasil.getValueAt(tblhasil.getSelectedRow(), 0).toString();
        String nama = tblhasil.getValueAt(tblhasil.getSelectedRow(), 1).toString();
        String jumlah = tblhasil.getValueAt(tblhasil.getSelectedRow(), 2).toString();
        String total = tblhasil.getValueAt(tblhasil.getSelectedRow(), 3).toString();
        String tanggal = tblhasil.getValueAt(tblhasil.getSelectedRow(), 4).toString();
        tidbarang.setText(idBarang);
        tnamabarang1.setText(nama);
        ttotalbarang.setText(jumlah);
        thrgbrg.setText(total);
        ttanggal.setText(tanggal);

//            String sql = "SELECT *, tbl_pegawai.nama_pegawai, tbl_produk.nama_produk "
//                    + "FROM tbl_transaksi "
//                    + "join tbl_pegawai "
//                    + "on tbl_transaksi.id_pegawai = tbl_pegawai.id_pegawai "
//                    + "join tbl_produk "
//                    + "on tbl_transaksi.id_produk = tbl_produk.id_produk "
//                    + "where tbl_transaksi.id_transaksi ='" + idBarang + "'"
//                    + "order by id_transaksi asc";
//            Connection conn = (Connection) koneksi.getKoneksi();
//            java.sql.Statement st = conn.createStatement();
//            java.sql.ResultSet res = st.executeQuery(sql);
//            if (res.next()) {
//                this.tidtransaksi.setText(res.getString("id_transaksi"));
//                this.ttanggal.setText(res.getString("tanggal"));
//                this.pilgay.setSelectedItem(res.getString("tbl_pegawai.id_pegawai") + " " + res.getString("tbl_pegawai.nama_pegawai"));
//                this.tidbarang.setText(res.getString("id_produk"));
//                this.tnamabarang1.setText(res.getString("nama_produk"));
//
//                this.ttotalbarang.setText(res.getString("total_barang"));
//                this.thrgbrg.setText(res.getString("total_harga"));
//
//            }
//
//        } catch (SQLException | ClassNotFoundException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
////        tabelHasil();
//
//        btambah.setVisible(false);
//        bhapus.setVisible(true);
//        bedit.setVisible(true);

    }//GEN-LAST:event_tblhasilMouseClicked

    private void ttotalbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttotalbarangActionPerformed

        try {
            String total = "SELECT * FROM tbl_produk where id_produk='" + tidbarang.getText() + "'";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(total);
            if (rs.next()) {

                int jumlah = Integer.parseInt(ttotalbarang.getText());
                int harga = Integer.parseInt(rs.getString("harga"));
                this.thrgbrg.setText(String.valueOf(harga * jumlah));

            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());

        }

    }//GEN-LAST:event_ttotalbarangActionPerformed

    private void tidtransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidtransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tidtransaksiActionPerformed

    private void tnamabarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamabarang1ActionPerformed
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Harga");

        try {
            String nama = tnamabarang1.getText();
            String sql = "SELECT * FROM tbl_produk WHERE nama_produk LIKE '%" + nama + "%'";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            System.out.println(sql);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            tblproduk.setModel(model);

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tnamabarang1ActionPerformed

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed

//        try {
////            String idPegawai = pilgay.getSelectedItem().toString().substring(0, 6);
//            String sql = "UPDATE  tbl_transaksi SET id_pegawai ='" + idPegawai + "', id_produk='" + tidbarang.getText()
//                    + "', total_barang='" + ttotalbarang.getText()
//                    + "', total_harga='" + thrgbrg.getText() + "',tanggal='" + ttanggal.getText() + "'"
//                    + "WHERE id_transaksi ='" + tidtransaksi.getText() + "'";
//            Connection conn = (Connection) koneksi.getKoneksi();
//            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "berhasil merubah data");
//
//        } catch (SQLException | ClassNotFoundException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
////        tabelHasil();
//        clear();
        DefaultTableModel model = (DefaultTableModel) tblhasil.getModel();
        model.removeRow(tblhasil.getSelectedRow());
        model.addRow(new Object[]{tidbarang.getText(),
            tnamabarang1.getText(), ttotalbarang.getText(),
            thrgbrg.getText(), ttanggal.getText()});

        clear();


    }//GEN-LAST:event_beditActionPerformed

    private void tidbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidbarangActionPerformed
        try {
            String sql = "select * from tbl_produk where id_produk = '" + tidbarang.getText() + "'";
            Connection con = (Connection) koneksi.getKoneksi();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                tnamabarang1.setText(rs.getString("nama_produk"));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage() + ", pada id barang");
        }
    }//GEN-LAST:event_tidbarangActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
//        int jawab = JOptionPane.showConfirmDialog(null, "APAKAH ANDA INGIN MENGAHPUS", "HAPUS", JOptionPane.YES_NO_OPTION);
//
//        switch (jawab) {
//            case JOptionPane.YES_OPTION:
//                try {
//                    String sql = "DELETE from tbl_transaksi where id_transaksi ='" + tidtransaksi.getText() + "'";
//                    java.sql.Connection conn = (Connection) koneksi.getKoneksi();
//                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//                    pst.execute();
//                    JOptionPane.showMessageDialog(this, "Berhasil Menghapus Data");
//                    clear();
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(this, e.getMessage());
//                }
//                tabelHasil();
        DefaultTableModel model = (DefaultTableModel) tblhasil.getModel();
        int colom = tblhasil.getSelectedRow();

        System.out.println(colom);
        model.removeRow(tblhasil.getSelectedRow());
        clear();
//                break;
//        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        info.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tdiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdiskonActionPerformed
        bhitungActionPerformed(evt);
    }//GEN-LAST:event_tdiskonActionPerformed

    private void tnamamemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamamemberActionPerformed
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Member");
        model.addColumn("Nama");
        model.addColumn("Diskon");

        try {
            String nama = tnamamember.getText();
            String sql = "SELECT * FROM tbl_member WHERE nama_member LIKE '%" + nama + "%'";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            System.out.println(sql);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(5)});
            }
            tblmember.setModel(model);

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tnamamemberActionPerformed

    private void tidmemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidmemberActionPerformed
        try {
            String id = tidmember.getText();
            if (tidmember.getText().length() > 10) {
                tidmember.setText("");
//                tidmember.setText(id);
            }

            String sql = "select * from tbl_member where id_member = '" + tidmember.getText() + "'";
            Connection con = (Connection) koneksi.getKoneksi();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                tnamamember.setText(rs.getString("nama_member"));
                tdiskon.setText(rs.getString("diskon"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e + " ERROR PADA TEXT MEMBER");
        }
    }//GEN-LAST:event_tidmemberActionPerformed

    private void tpembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpembayaranActionPerformed
        int jumlah = Integer.valueOf(tpembayaran.getText()) - Integer.valueOf(ttotalharga.getText());
        tkembalian.setText(String.valueOf(jumlah));
    }//GEN-LAST:event_tpembayaranActionPerformed

    private void tkembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkembalianActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void tdiskonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdiskonKeyTyped

    }//GEN-LAST:event_tdiskonKeyTyped

    private void tblmemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmemberMouseClicked
        int baris = tblmember.rowAtPoint(evt.getPoint());

        String idpegawai = tblmember.getValueAt(baris, 0).toString();
        tidmember.setText(idpegawai);

        String nama = tblmember.getValueAt(baris, 1).toString();
        tnamamember.setText(nama);

        String nohp = tblmember.getValueAt(baris, 2).toString();
        tdiskon.setText(nohp);
    }//GEN-LAST:event_tblmemberMouseClicked

    private void bhitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhitungActionPerformed
        double diskon = Integer.valueOf(tdiskon.getText());
        double harga = Integer.valueOf(total.getText());
        System.out.println(diskon);
        System.out.println(harga);

        double dis = diskon / 100;
        System.out.println(dis);
        int total = (int) Math.round(harga * dis);
        System.out.println(total);
        int total1 = (int) Math.round(harga - total);
        ttotalharga.setText(String.valueOf(total1));

        System.out.println(login.idPegawai + " " + jumlahBarang() + " " + hargaTotal());
    }//GEN-LAST:event_bhitungActionPerformed

    private void bbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbayarActionPerformed
        int kembalian = Integer.parseInt(tkembalian.getText());
        if (kembalian >= 0) {
            String sql = "Insert into `tbl_transaksi` VALUES ('" + idTransaksi + "','" + login.idPegawai + "','" + tidmember.getText() + "','" + jumlahBarang() + "','" + hargaTotal() + "','"
                    + ttanggal.getText() + "','" + ttotalharga.getText() + "','" + tpembayaran.getText() + "','" + tkembalian.getText() + "')";
            try {
                login lg = new login();

                java.sql.Connection conn = (Connection) koneksi.getKoneksi();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                System.out.println(sql);
                System.out.println(idPegawai + " " + jumlahBarang() + " " + hargaTotal());

                int row = tblhasil.getRowCount();
                for (int i = 0; i < row; i++) {
                    String sql1 = "insert into `tbl_transaksi_detail` VALUES ('" + idTransaksi + "','" + tblhasil.getValueAt(i, 0).toString() + "','"
                            + tblhasil.getValueAt(i, 2).toString() + "','" + tblhasil.getValueAt(i, 3).toString() + "')";
                    java.sql.Connection con = (Connection) koneksi.getKoneksi();
                    java.sql.PreparedStatement ps = conn.prepareStatement(sql1);
                    ps.execute();
                    System.out.println(sql1);

                }
                JOptionPane.showMessageDialog(null, "Penympanan berhasil!");
                cetak(tidtransaksi.getText());
                clear();
                hapusTabel();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage() + " insert pegawai");
            }
            System.out.println(sql);
        } else {
            JOptionPane.showMessageDialog(this, "MAAF UANG KURANG!!!");
        }
    }//GEN-LAST:event_bbayarActionPerformed

    private void ttotalhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttotalhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttotalhargaActionPerformed

    private void bloghapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloghapusActionPerformed
        new hapusPenjualan().setVisible(true);
        info.setVisible(false);
    }//GEN-LAST:event_bloghapusActionPerformed

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

    private void tidmemberInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tidmemberInputMethodTextChanged

    }//GEN-LAST:event_tidmemberInputMethodTextChanged

    private void tidmemberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tidmemberFocusGained

    }//GEN-LAST:event_tidmemberFocusGained

    private void tidbarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tidbarangKeyReleased
        try {
            String total = "SELECT * FROM tbl_produk where id_produk='" + tidbarang.getText() + "'";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(total);
            if (rs.next()) {
                tnamabarang1.setText(rs.getString("nama_produk"));
                ttotalbarang.setText("1");
                int jumlah = Integer.parseInt(ttotalbarang.getText());
                int harga = Integer.parseInt(rs.getString("harga"));
                this.thrgbrg.setText(String.valueOf(harga * jumlah));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error pada id Barang!");
        }
    }//GEN-LAST:event_tidbarangKeyReleased

    private void bhitung1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhitung1ActionPerformed

        cetak(tidtransaksi.getText());
//        hapusTabel();

    }//GEN-LAST:event_bhitung1ActionPerformed

    private void ttotalbarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ttotalbarangKeyPressed

    }//GEN-LAST:event_ttotalbarangKeyPressed

    private void ttotalbarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ttotalbarangKeyReleased
        try {
            String total = "SELECT * FROM tbl_produk where id_produk='" + tidbarang.getText() + "'";
            Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(total);
            if (rs.next()) {

                int jumlah = Integer.parseInt(ttotalbarang.getText());
                int harga = Integer.parseInt(rs.getString("harga"));
                this.thrgbrg.setText(String.valueOf(harga * jumlah));

            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());

        }
    }//GEN-LAST:event_ttotalbarangKeyReleased

    private void tpembayaranKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpembayaranKeyReleased
       
            int jumlah = Integer.valueOf(tpembayaran.getText()) - Integer.valueOf(ttotalharga.getText());
            tkembalian.setText(String.valueOf(jumlah));
        
    }//GEN-LAST:event_tpembayaranKeyReleased

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
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Lid;
    private javax.swing.JButton bbarangmasuk;
    private javax.swing.JButton bbayar;
    private javax.swing.JButton bdashboard;
    private javax.swing.JButton bedit;
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bhitung;
    private javax.swing.JButton bhitung1;
    private javax.swing.JButton blaporan;
    private javax.swing.JButton bloghapus;
    private javax.swing.JButton bmember;
    private javax.swing.JButton bpegawai;
    private javax.swing.JButton bpenjualan;
    private javax.swing.JButton bproduk;
    private javax.swing.JButton bsuplier;
    private javax.swing.JButton btambah;
    private javax.swing.JDialog info;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton logout;
    private javax.swing.JTable tblhasil;
    private javax.swing.JTable tblmember;
    private javax.swing.JTable tblproduk;
    public static javax.swing.JTextField tdiskon;
    public static javax.swing.JTextField thrgbrg;
    public static javax.swing.JTextField tidbarang;
    public static javax.swing.JTextField tidmember;
    private javax.swing.JTextField tidtransaksi;
    public static javax.swing.JTextField tkembalian;
    public static javax.swing.JTextField tnamabarang1;
    public static javax.swing.JTextField tnamamember;
    public static javax.swing.JTextField total;
    public static javax.swing.JTextField tpembayaran;
    public static javax.swing.JTextField ttanggal;
    public static javax.swing.JTextField ttotalbarang;
    public static javax.swing.JTextField ttotalharga;
    // End of variables declaration//GEN-END:variables
}
