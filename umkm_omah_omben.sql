-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 26 Bulan Mei 2023 pada 16.18
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `umkm_omah_omben`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `history_ganti_password`
--

CREATE TABLE `history_ganti_password` (
  `id_pegawai` char(10) DEFAULT NULL,
  `Password_lama` char(20) DEFAULT NULL,
  `password_baru` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_akun`
--

CREATE TABLE `tbl_akun` (
  `id_pegawai` char(10) NOT NULL,
  `username` char(14) NOT NULL,
  `password` char(20) NOT NULL,
  `level` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_akun`
--

INSERT INTO `tbl_akun` (`id_pegawai`, `username`, `password`, `level`) VALUES
('PG0001', 'pg', 'pg', 2),
('PG0002', 'admin', '12345678', 0),
('PG0003', 'supri', '1234', 2),
('PG0006', 'aripin', 'aripin', 2);

--
-- Trigger `tbl_akun`
--
DELIMITER $$
CREATE TRIGGER `ganti_password` BEFORE UPDATE ON `tbl_akun` FOR EACH ROW BEGIN 
insert history_ganti_password
set id_pegawai = old.id_pegawai,
password_lama = old.password,
password_baru = new.password;
End
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_brgmsk`
--

CREATE TABLE `tbl_brgmsk` (
  `id_transaksi` char(10) NOT NULL,
  `id_pegawai` char(10) NOT NULL,
  `id_suplier` char(10) NOT NULL,
  `jumlah_barang` int(5) DEFAULT NULL,
  `total_harga` int(10) DEFAULT NULL,
  `tanggal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_brgmsk`
--

INSERT INTO `tbl_brgmsk` (`id_transaksi`, `id_pegawai`, `id_suplier`, `jumlah_barang`, `total_harga`, `tanggal`) VALUES
('BRG0000001', 'PG0002', 'SP0002', 2, 30000, '2023-05-23'),
('BRG0000002', 'PG0002', 'SP0002', 10, 15000, '2023-05-24'),
('BRG0000003', 'PG0001', 'SP0001', 10, 10000, '2023-05-26'),
('BRG0000004', 'PG0001', 'SP0002', 10, 10000, '2023-05-26');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_brgmsk_detail`
--

CREATE TABLE `tbl_brgmsk_detail` (
  `id_transaksi` char(10) DEFAULT NULL,
  `id_barang` char(12) NOT NULL,
  `nama_barang` varchar(30) DEFAULT NULL,
  `harga_barang` int(10) DEFAULT NULL,
  `jumlah_barang` int(5) DEFAULT NULL,
  `total_harga` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_brgmsk_detail`
--

INSERT INTO `tbl_brgmsk_detail` (`id_transaksi`, `id_barang`, `nama_barang`, `harga_barang`, `jumlah_barang`, `total_harga`) VALUES
('BRG0000001', '12345', 'susu', 15000, 2, 30000),
('BRG0000002', '0981', 'milo ', 1500, 10, 15000),
('BRG0000003', 'asd', 'susu', 1000, 10, 10000),
('BRG0000004', 'asd', 'susu', 1000, 10, 10000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_member`
--

CREATE TABLE `tbl_member` (
  `id_member` char(16) NOT NULL,
  `nama_member` varchar(60) DEFAULT NULL,
  `no_telp` char(13) DEFAULT NULL,
  `alamat` char(70) DEFAULT NULL,
  `diskon` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_member`
--

INSERT INTO `tbl_member` (`id_member`, `nama_member`, `no_telp`, `alamat`, `diskon`) VALUES
('0', '0', '000000000000', '0', 0),
('0008486238', 'SUGENG', '082345678910', 'KEDIRI', 5),
('0008486291', 'prayoga kusdiana ikhsani', '081357743268', 'nganjuk', 10),
('0008486343', 'johanes ricaldo saputra', '081234567890', 'kediri', 10),
('12345678', 'ikhsan', '012345678901', 'nganjuk', 10);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_pegawai`
--

CREATE TABLE `tbl_pegawai` (
  `id_pegawai` char(10) NOT NULL,
  `nama_pegawai` varchar(60) NOT NULL,
  `no_telp` char(14) NOT NULL,
  `alamat_pegawai` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_pegawai`
--

INSERT INTO `tbl_pegawai` (`id_pegawai`, `nama_pegawai`, `no_telp`, `alamat_pegawai`) VALUES
('PG0001', 'Prayoga Kusdiana Ikhsani', '081357743268', 'Bagor, Nganjuk'),
('PG0002', 'Renaldi Endrawan', '0123456789', 'Nganjuk'),
('PG0003', 'Suprianto', '01234567890', 'jombang'),
('PG0004', 'Refvi Eka Wardani', '0123456789', 'Nganjuk'),
('PG0005', 'Eva Yuliana', '0812345678901', 'Nganjuk'),
('PG0006', 'aripin', '012345678', 'nganjuk');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_produk`
--

CREATE TABLE `tbl_produk` (
  `id_produk` char(10) NOT NULL,
  `nama_produk` varchar(30) DEFAULT NULL,
  `Harga` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_produk`
--

INSERT INTO `tbl_produk` (`id_produk`, `nama_produk`, `Harga`) VALUES
('P00001', 'Ori Green Tea ', 6000),
('P00002', 'Taro', 7000),
('P00003', 'Coklat Milo', 9000),
('P00004', 'Red Velvet', 7000),
('P00005', 'Caramel Coffe', 11000),
('P00006', 'Boba Fresh Milk', 10000),
('P00007', 'Mie Ngoweh', 11000),
('P00008', 'Pentol Ngoweh', 7000),
('P00009', 'Seblak Ngoweh', 12000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_suplier`
--

CREATE TABLE `tbl_suplier` (
  `id_suplier` char(10) NOT NULL,
  `nama_suplier` varchar(50) NOT NULL,
  `no_tlp` varchar(13) DEFAULT NULL,
  `alamat_suplier` varchar(75) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_suplier`
--

INSERT INTO `tbl_suplier` (`id_suplier`, `nama_suplier`, `no_tlp`, `alamat_suplier`) VALUES
('SP0001', 'sugeng', '123456789', 'kadiri'),
('SP0002', 'FARID', '08696969696', 'nggondang'),
('SP0003', 'ardi', '0812345678', 'nganjuk');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_transaksi`
--

CREATE TABLE `tbl_transaksi` (
  `id_transaksi` char(15) NOT NULL,
  `id_pegawai` char(10) NOT NULL,
  `id_member` char(16) DEFAULT NULL,
  `jumlah_barang` int(4) DEFAULT NULL,
  `total_harga` int(10) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `harga_diskon` int(10) NOT NULL,
  `pembayaran` int(10) NOT NULL,
  `kembalian` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_transaksi`
--

INSERT INTO `tbl_transaksi` (`id_transaksi`, `id_pegawai`, `id_member`, `jumlah_barang`, `total_harga`, `tanggal`, `harga_diskon`, `pembayaran`, `kembalian`) VALUES
('T000000001', 'PG0002', '0008486343', 4, 39000, '2023-05-23', 35100, 37000, 1900),
('T000000002', 'PG0001', '0008486291', 1, 6000, '2023-05-23', 5400, 6000, 600),
('T000000003', 'PG0002', '0008486291', 1, 7000, '2023-05-24', 6300, 7000, 700),
('T000000004', 'PG0002', '0008486238', 1, 10000, '2023-05-24', 9500, 10000, 500),
('T000000005', 'PG0002', '0008486291', 2, 14000, '2023-05-24', 12600, 15000, 2400),
('T000000006', 'PG0002', '0', 1, 9000, '2023-05-26', 9000, 10000, 1000),
('T000000007', 'PG0002', '0', 1, 9000, '2023-05-26', 9000, 10000, 1000),
('T000000008', 'PG0001', '0', 1, 6000, '2023-05-24', 6000, 6000, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_transaksi_detail`
--

CREATE TABLE `tbl_transaksi_detail` (
  `id_transaksi` char(10) DEFAULT NULL,
  `id_produk` char(10) DEFAULT NULL,
  `jumlah_barang` int(4) DEFAULT NULL,
  `total_harga` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_transaksi_detail`
--

INSERT INTO `tbl_transaksi_detail` (`id_transaksi`, `id_produk`, `jumlah_barang`, `total_harga`) VALUES
('T000000001', 'P00005', 1, 11000),
('T000000001', 'P00006', 1, 10000),
('T000000001', 'P00001', 1, 6000),
('T000000001', 'P00009', 1, 12000),
('T000000002', 'P00001', 1, 6000),
('T000000003', 'P00002', 1, 7000),
('T000000004', 'P00006', 1, 10000),
('T000000005', 'P00004', 2, 14000),
('T000000006', 'P00003', 1, 9000),
('T000000007', 'P00003', 1, 9000),
('T000000008', 'P00001', 1, 6000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `history_ganti_password`
--
ALTER TABLE `history_ganti_password`
  ADD KEY `id_pegawai` (`id_pegawai`);

--
-- Indeks untuk tabel `tbl_akun`
--
ALTER TABLE `tbl_akun`
  ADD UNIQUE KEY `tbl_akun_ibfk_1` (`id_pegawai`) USING BTREE;

--
-- Indeks untuk tabel `tbl_brgmsk`
--
ALTER TABLE `tbl_brgmsk`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `tbl_brgmsk_ibfk_1` (`id_suplier`),
  ADD KEY `tbl_brgmsk_ibfk_2` (`id_pegawai`);

--
-- Indeks untuk tabel `tbl_brgmsk_detail`
--
ALTER TABLE `tbl_brgmsk_detail`
  ADD KEY `id_barang` (`id_barang`) USING BTREE,
  ADD KEY `id_transaksi` (`id_transaksi`) USING BTREE;

--
-- Indeks untuk tabel `tbl_member`
--
ALTER TABLE `tbl_member`
  ADD PRIMARY KEY (`id_member`);

--
-- Indeks untuk tabel `tbl_pegawai`
--
ALTER TABLE `tbl_pegawai`
  ADD PRIMARY KEY (`id_pegawai`);

--
-- Indeks untuk tabel `tbl_produk`
--
ALTER TABLE `tbl_produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indeks untuk tabel `tbl_suplier`
--
ALTER TABLE `tbl_suplier`
  ADD PRIMARY KEY (`id_suplier`);

--
-- Indeks untuk tabel `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `tbl_transaksi_ibfk_1` (`id_pegawai`),
  ADD KEY `id_member` (`id_member`);

--
-- Indeks untuk tabel `tbl_transaksi_detail`
--
ALTER TABLE `tbl_transaksi_detail`
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_produk` (`id_produk`) USING BTREE;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `history_ganti_password`
--
ALTER TABLE `history_ganti_password`
  ADD CONSTRAINT `history_ganti_password_ibfk_1` FOREIGN KEY (`id_pegawai`) REFERENCES `tbl_pegawai` (`id_pegawai`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tbl_akun`
--
ALTER TABLE `tbl_akun`
  ADD CONSTRAINT `tbl_akun_ibfk_1` FOREIGN KEY (`id_pegawai`) REFERENCES `tbl_pegawai` (`id_pegawai`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tbl_brgmsk`
--
ALTER TABLE `tbl_brgmsk`
  ADD CONSTRAINT `tbl_brgmsk_ibfk_1` FOREIGN KEY (`id_suplier`) REFERENCES `tbl_suplier` (`id_suplier`) ON DELETE NO ACTION,
  ADD CONSTRAINT `tbl_brgmsk_ibfk_2` FOREIGN KEY (`id_pegawai`) REFERENCES `tbl_pegawai` (`id_pegawai`) ON DELETE NO ACTION;

--
-- Ketidakleluasaan untuk tabel `tbl_brgmsk_detail`
--
ALTER TABLE `tbl_brgmsk_detail`
  ADD CONSTRAINT `tbl_brgmsk_detail_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `tbl_brgmsk` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD CONSTRAINT `tbl_transaksi_ibfk_1` FOREIGN KEY (`id_pegawai`) REFERENCES `tbl_pegawai` (`id_pegawai`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tbl_transaksi_ibfk_2` FOREIGN KEY (`id_member`) REFERENCES `tbl_member` (`id_member`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tbl_transaksi_detail`
--
ALTER TABLE `tbl_transaksi_detail`
  ADD CONSTRAINT `tbl_transaksi_detail_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `tbl_transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tbl_transaksi_detail_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `tbl_produk` (`id_produk`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
