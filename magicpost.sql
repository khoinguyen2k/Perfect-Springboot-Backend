-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2024 at 01:04 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `magicpost`
--
CREATE DATABASE IF NOT EXISTS `magicpost` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `magicpost`;

-- --------------------------------------------------------

--
-- Table structure for table `don_hang`
--

CREATE TABLE `don_hang` (
  `gia_tien` int(11) NOT NULL DEFAULT 20000,
  `ma_don_hang` int(11) NOT NULL,
  `ma_kien_hang` int(11) NOT NULL,
  `thoi_gian_gui` datetime(6) NOT NULL DEFAULT current_timestamp(),
  `thoi_gian_nhan` datetime(6) DEFAULT NULL,
  `trang_thai` varchar(255) DEFAULT NULL,
  `username_nguoi_gui` varchar(255) NOT NULL,
  `username_nguoi_nhan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `don_hang`
--

INSERT INTO `don_hang` (`gia_tien`, `ma_don_hang`, `ma_kien_hang`, `thoi_gian_gui`, `thoi_gian_nhan`, `trang_thai`, `username_nguoi_gui`, `username_nguoi_nhan`) VALUES
(20000, 5, 10, '2024-01-02 14:56:35.000000', '2024-01-02 19:56:35.000000', 'vận chuyển từ điểm giao dịch đến khách hàng thành công', 'abc', 'customer3'),
(20000, 7, 1, '2024-01-02 16:33:43.000000', NULL, 'đang trên đường từ điểm giao dịch đến khách hàng', 'abc', 'customer2'),
(20000, 8, 2, '2024-01-02 16:35:16.000000', NULL, 'vận chuyển từ điểm giao dịch đến khách hàng thất bại', 'customer1', 'abc'),
(20000, 9, 3, '2024-01-02 16:39:31.000000', NULL, 'vận chuyển đến điểm tập kết thứ hai thất bại', 'customer2', 'abc'),
(20000, 10, 4, '2024-01-02 16:43:14.000000', NULL, 'đang trên đường đến điểm tập kết thứ hai', 'abc', 'customer3'),
(20000, 11, 6, '2024-01-02 16:43:58.000000', NULL, 'đang trên đường đến điểm tập kết thứ hai', 'customer3', 'abc');

-- --------------------------------------------------------

--
-- Table structure for table `khach_hang`
--

CREATE TABLE `khach_hang` (
  `ma_buu_chinh` int(11) DEFAULT 10179,
  `dia_chi` text DEFAULT NULL,
  `ho_ten` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khach_hang`
--

INSERT INTO `khach_hang` (`ma_buu_chinh`, `dia_chi`, `ho_ten`, `password`, `sdt`, `username`) VALUES
(10238, 'Yen Hoa, Cau Giay, Ha Noi', 'Le Dinh T', '11', '039495349', 'abc'),
(10179, 'Cau Giay, Ha Noi, Viet Nam', 'Tran N', '1212', '0923591231', 'customer1'),
(10179, 'Bac Tu Liem, Ha Noi, Viet Nam', 'Nguyen M', '2121', '0899734123', 'customer2'),
(10179, 'Dich Vong Hau, Ha Noi', 'Tran M N', '111', '09237235234', 'customer3');

-- --------------------------------------------------------

--
-- Table structure for table `kien_hang`
--

CREATE TABLE `kien_hang` (
  `khoi_luong` int(11) DEFAULT NULL,
  `ma_kien_hang` int(11) NOT NULL,
  `image_url` text DEFAULT 'https://cdn.vectorstock.com/i/preview-1x/65/30/default-image-icon-missing-picture-page-vector-40546530.jpg',
  `loai_hang` varchar(255) DEFAULT NULL,
  `mo_ta` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kien_hang`
--

INSERT INTO `kien_hang` (`khoi_luong`, `ma_kien_hang`, `image_url`, `loai_hang`, `mo_ta`) VALUES
(3, 1, 'https://cdn.vectorstock.com/i/preview-1x/65/30/default-image-icon-missing-picture-page-vector-40546530.jpg', 'tài liệu', 'test1'),
(4, 2, 'https://media.istockphoto.com/id/1308840409/photo/scanning-parcel-barcode-before-shipment.jpg?s=612x612&w=0&k=20&c=R7h1XCbjdXKXhngzsg0CYfzAZ3k_7yguHR8t9LNYwY8=', 'hàng hóa', 'test2'),
(69, 3, 'https://media.istockphoto.com/id/1308840409/photo/scanning-parcel-barcode-before-shipment.jpg?s=612x612&w=0&k=20&c=R7h1XCbjdXKXhngzsg0CYfzAZ3k_7yguHR8t9LNYwY8=', 'hàng hóa', 'test tet'),
(6, 4, 'https://www.shutterstock.com/image-illustration/packages-delivery-packaging-service-parcels-260nw-625771727.jpg', 'tài liệu', 'đây là mô tả'),
(13, 5, 'https://st2.depositphotos.com/1001877/7125/i/450/depositphotos_71255577-stock-photo-delivery-concept-boxes-on-pallet.jpg', 'tài liệu', 'test1111'),
(21, 6, 'https://media.istockphoto.com/id/1248789819/photo/front-door-with-delivery-boxes.jpg?s=612x612&w=0&k=20&c=Arc0bwOKwRECMw9xqHso95wkeSByc_SJSwI6S4ANfOQ=', 'tài liệu', 'test112312'),
(9, 7, 'https://media.istockphoto.com/id/1282219840/photo/cardboard-box-isolated-on-white-background-with-clipping-path.jpg?s=612x612&w=0&k=20&c=1pBj9xJxvRblZ6_B4_d1BW-WosAS6-ouYShB46Fpusc=', 'tài liệu', 'Stock image'),
(100, 8, 'https://media.istockphoto.com/id/1308840409/photo/scanning-parcel-barcode-before-shipment.jpg?s=612x612&w=0&k=20&c=R7h1XCbjdXKXhngzsg0CYfzAZ3k_7yguHR8t9LNYwY8=', 'tài liệu', 'fake image'),
(10, 9, 'https://cdn.vectorstock.com/i/preview-1x/65/30/default-image-icon-missing-picture-page-vector-40546530.jpg', 'hàng hóa', 'Chỉ có 2 loại hàng: tài liệu và hàng hóa'),
(8, 10, 'https://cdn.vectorstock.com/i/preview-1x/65/30/default-image-icon-missing-picture-page-vector-40546530.jpg', 'hàng hóa', 'test des');

-- --------------------------------------------------------

--
-- Table structure for table `lanh_dao_cong_ty`
--

CREATE TABLE `lanh_dao_cong_ty` (
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lanh_dao_cong_ty`
--

INSERT INTO `lanh_dao_cong_ty` (`password`, `username`) VALUES
('1234', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `nhan_vien_giao_dich`
--

CREATE TABLE `nhan_vien_giao_dich` (
  `ho_ten` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `username_truong_diem_giao_dich` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhan_vien_giao_dich`
--

INSERT INTO `nhan_vien_giao_dich` (`ho_ten`, `password`, `sdt`, `username`, `username_truong_diem_giao_dich`) VALUES
('Nhan vien Giao dich 123', '11', '039495349', 'transact-employee-1', 'transactionleader2'),
('Giao dich vien 1', '1233', '0987987987', 'transaction1', 'transactionleader1'),
('Giao dich vien 2', '123333', '0987345812', 'transaction2', 'transactionleader1');

-- --------------------------------------------------------

--
-- Table structure for table `nhan_vien_giao_hang`
--

CREATE TABLE `nhan_vien_giao_hang` (
  `ho_ten` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `username_truong_diem_giao_dich` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhan_vien_giao_hang`
--

INSERT INTO `nhan_vien_giao_hang` (`ho_ten`, `password`, `sdt`, `username`, `username_truong_diem_giao_dich`) VALUES
('Shipper 1', '231231', '0989898922', 'shipper1', 'transactionleader1'),
('Shipper 2', '231231', '0989348922', 'shipper2', 'transactionleader2');

-- --------------------------------------------------------

--
-- Table structure for table `nhan_vien_tap_ket`
--

CREATE TABLE `nhan_vien_tap_ket` (
  `ho_ten` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `username_truong_diem_tap_ket` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhan_vien_tap_ket`
--

INSERT INTO `nhan_vien_tap_ket` (`ho_ten`, `password`, `sdt`, `username`, `username_truong_diem_tap_ket`) VALUES
('Truong Thi K', '1', '0345912094', 'gather-employee-1', 'admintapket4'),
('Nguyen Thi B', '333', '093459239', 'gather1', 'gatherleader1'),
('Tran Van C', '444', '0899823481', 'gather2', 'gatherleader2'),
('Nguyen Van V', '123', '09238232341', 'gather@employee', 'gatherleader1');

-- --------------------------------------------------------

--
-- Table structure for table `truong_diem_giao_dich`
--

CREATE TABLE `truong_diem_giao_dich` (
  `dia_chi` text DEFAULT NULL,
  `ho_ten` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `username_truong_diem_tap_ket` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `truong_diem_giao_dich`
--

INSERT INTO `truong_diem_giao_dich` (`dia_chi`, `ho_ten`, `password`, `username`, `username_truong_diem_tap_ket`) VALUES
('Bach Kinh Xay', 'Truong diem Giao dich 69', '122111', 'admingiaodich6', 'gatherleader2'),
('Ha Noi, Viet Nam', 'Nguyen Van A', '222', 'transactionleader1', 'gatherleader1'),
('Bac Ninh, Viet Nam', 'Nguyen Thi L', '2222', 'transactionleader2', 'gatherleader1');

-- --------------------------------------------------------

--
-- Table structure for table `truong_diem_tap_ket`
--

CREATE TABLE `truong_diem_tap_ket` (
  `dia_chi` text DEFAULT NULL,
  `ho_ten` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `truong_diem_tap_ket`
--

INSERT INTO `truong_diem_tap_ket` (`dia_chi`, `ho_ten`, `password`, `username`) VALUES
('Ha Noi, Viet Nam', 'Truong diem Tap Ket 1', '123', 'admintapket4'),
('Viet Nam', 'Person', '123', 'gatherleader1'),
('Đông Lào', 'mrBeast', '111', 'gatherleader2');

-- --------------------------------------------------------

--
-- Table structure for table `van_chuyen_giao_dich_tap_ket`
--

CREATE TABLE `van_chuyen_giao_dich_tap_ket` (
  `ma_don_van_chuyen` int(11) NOT NULL,
  `ma_kien_hang` int(11) NOT NULL,
  `thoi_gian_gui` datetime(6) NOT NULL DEFAULT current_timestamp(),
  `thoi_gian_nhan` datetime(6) DEFAULT NULL,
  `trang_thai` varchar(255) DEFAULT NULL,
  `username_truong_diem_giao_dich` varchar(255) NOT NULL,
  `username_truong_diem_tap_ket` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `van_chuyen_giao_dich_tap_ket`
--

INSERT INTO `van_chuyen_giao_dich_tap_ket` (`ma_don_van_chuyen`, `ma_kien_hang`, `thoi_gian_gui`, `thoi_gian_nhan`, `trang_thai`, `username_truong_diem_giao_dich`, `username_truong_diem_tap_ket`) VALUES
(4, 10, '2024-01-02 15:56:35.000000', '2024-01-02 16:56:35.000000', 'vận chuyển đến kho tập kết thành công', 'admingiaodich6', 'admintapket4'),
(5, 10, '2024-01-02 17:56:35.000000', '2024-01-02 18:56:35.000000', 'vận chuyển đến kho giao dịch thành công', 'transactionleader1', 'gatherleader2'),
(8, 1, '2024-01-02 17:33:43.000000', '2024-01-02 18:33:43.000000', 'vận chuyển đến kho tập kết thành công', 'admingiaodich6', 'admintapket4'),
(9, 1, '2024-01-02 19:33:43.000000', '2024-01-02 20:33:43.000000', 'vận chuyển đến kho tập kết thành công', 'transactionleader1', 'gatherleader1'),
(10, 2, '2024-01-02 17:35:16.000000', '2024-01-02 18:35:16.000000', 'vận chuyển đến kho tập kết thành công', 'transactionleader1', 'gatherleader1'),
(11, 2, '2024-01-02 19:35:16.000000', '2024-01-02 20:35:16.000000', 'vận chuyển đến kho tập kết thành công', 'admingiaodich6', 'gatherleader2'),
(12, 3, '2024-01-02 17:39:31.000000', '2024-01-02 18:39:31.000000', 'vận chuyển đến kho tập kết thành công', 'transactionleader1', 'gatherleader1'),
(13, 4, '2024-01-02 17:43:14.000000', '2024-01-02 18:43:14.000000', 'vận chuyển đến kho tập kết thành công', 'admingiaodich6', 'gatherleader1'),
(14, 6, '2024-01-02 17:43:58.000000', '2024-01-02 18:43:58.000000', 'vận chuyển đến kho tập kết thành công', 'transactionleader1', 'gatherleader2');

-- --------------------------------------------------------

--
-- Table structure for table `van_chuyen_khach_hang_giao_dich`
--

CREATE TABLE `van_chuyen_khach_hang_giao_dich` (
  `ma_don_van_chuyen` int(11) NOT NULL,
  `ma_kien_hang` int(11) NOT NULL,
  `thoi_gian_gui` datetime(6) NOT NULL DEFAULT current_timestamp(),
  `thoi_gian_nhan` datetime(6) DEFAULT NULL,
  `trang_thai` varchar(255) DEFAULT NULL,
  `username_khach_hang` varchar(255) NOT NULL,
  `username_truong_diem_giao_dich` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `van_chuyen_khach_hang_giao_dich`
--

INSERT INTO `van_chuyen_khach_hang_giao_dich` (`ma_don_van_chuyen`, `ma_kien_hang`, `thoi_gian_gui`, `thoi_gian_nhan`, `trang_thai`, `username_khach_hang`, `username_truong_diem_giao_dich`) VALUES
(4, 10, '2024-01-02 14:56:35.000000', '2024-01-02 15:56:35.000000', 'vận chuyển đến kho giao dịch thành công', 'abc', 'admingiaodich6'),
(5, 10, '2024-01-02 18:56:35.000000', '2024-01-02 19:56:35.000000', 'vận chuyển từ điểm giao dịch đến khách hàng thành công', 'customer3', 'transactionleader1'),
(7, 1, '2024-01-02 16:33:43.000000', '2024-01-02 17:33:43.000000', 'vận chuyển đến kho giao dịch thành công', 'abc', 'admingiaodich6'),
(8, 1, '2024-01-02 20:33:43.000000', NULL, 'đang trên đường từ điểm giao dịch đến khách hàng', 'customer2', 'transactionleader1'),
(9, 2, '2024-01-02 16:35:16.000000', '2024-01-02 17:35:16.000000', 'vận chuyển đến kho giao dịch thành công', 'customer1', 'transactionleader1'),
(10, 2, '2024-01-02 20:35:16.000000', NULL, 'vận chuyển từ điểm giao dịch đến khách hàng thất bại', 'abc', 'admingiaodich6'),
(11, 3, '2024-01-02 16:39:31.000000', '2024-01-02 17:39:31.000000', 'vận chuyển đến kho giao dịch thành công', 'customer2', 'transactionleader1'),
(12, 4, '2024-01-02 16:43:14.000000', '2024-01-02 17:43:14.000000', 'vận chuyển đến kho giao dịch thành công', 'abc', 'admingiaodich6'),
(13, 6, '2024-01-02 16:43:58.000000', '2024-01-02 17:43:58.000000', 'vận chuyển đến kho giao dịch thành công', 'customer3', 'transactionleader1');

-- --------------------------------------------------------

--
-- Table structure for table `van_chuyen_tap_ket_tap_ket`
--

CREATE TABLE `van_chuyen_tap_ket_tap_ket` (
  `ma_don_van_chuyen` int(11) NOT NULL,
  `ma_kien_hang` int(11) NOT NULL,
  `thoi_gian_gui` datetime(6) NOT NULL DEFAULT current_timestamp(),
  `thoi_gian_nhan` datetime(6) DEFAULT NULL,
  `trang_thai` varchar(255) DEFAULT NULL,
  `username_truong_diem_tap_ket_gui` varchar(255) NOT NULL,
  `username_truong_diem_tap_ket_nhan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `van_chuyen_tap_ket_tap_ket`
--

INSERT INTO `van_chuyen_tap_ket_tap_ket` (`ma_don_van_chuyen`, `ma_kien_hang`, `thoi_gian_gui`, `thoi_gian_nhan`, `trang_thai`, `username_truong_diem_tap_ket_gui`, `username_truong_diem_tap_ket_nhan`) VALUES
(1, 10, '2024-01-02 16:56:35.000000', '2024-01-02 17:56:35.000000', 'vận chuyển đến kho tập kết thành công', 'admintapket4', 'gatherleader2'),
(3, 1, '2024-01-02 18:33:43.000000', '2024-01-02 19:33:43.000000', 'vận chuyển đến kho tập kết thành công', 'admintapket4', 'gatherleader1'),
(4, 2, '2024-01-02 18:35:16.000000', '2024-01-02 19:35:16.000000', 'vận chuyển đến kho tập kết thành công', 'gatherleader1', 'gatherleader2'),
(5, 3, '2024-01-02 18:39:31.000000', NULL, 'vận chuyển đến điểm tập kết thứ hai thất bại', 'gatherleader1', 'admintapket4'),
(6, 4, '2024-01-02 18:43:14.000000', NULL, 'đang trên đường đến điểm tập kết thứ hai', 'gatherleader1', 'admintapket4'),
(7, 6, '2024-01-02 18:43:58.000000', NULL, 'đang trên đường đến điểm tập kết thứ hai', 'gatherleader2', 'gatherleader1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `don_hang`
--
ALTER TABLE `don_hang`
  ADD PRIMARY KEY (`ma_don_hang`),
  ADD UNIQUE KEY `UK_b7vom0l8iw949c9rmxev2lg6v` (`ma_kien_hang`),
  ADD KEY `FKc1k7xhm3tr20uaw0o8d43hep2` (`username_nguoi_nhan`),
  ADD KEY `FKjv8eeuef8h5an533mwotw0bs` (`username_nguoi_gui`);

--
-- Indexes for table `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `kien_hang`
--
ALTER TABLE `kien_hang`
  ADD PRIMARY KEY (`ma_kien_hang`);

--
-- Indexes for table `lanh_dao_cong_ty`
--
ALTER TABLE `lanh_dao_cong_ty`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `nhan_vien_giao_dich`
--
ALTER TABLE `nhan_vien_giao_dich`
  ADD PRIMARY KEY (`username`),
  ADD KEY `FK6k7lqevkx7iuiaslxvef5dtc7` (`username_truong_diem_giao_dich`);

--
-- Indexes for table `nhan_vien_giao_hang`
--
ALTER TABLE `nhan_vien_giao_hang`
  ADD PRIMARY KEY (`username`),
  ADD KEY `FKqxv32v76jrdvd5t4mvy8sxljw` (`username_truong_diem_giao_dich`);

--
-- Indexes for table `nhan_vien_tap_ket`
--
ALTER TABLE `nhan_vien_tap_ket`
  ADD PRIMARY KEY (`username`),
  ADD KEY `FK4c90jnoc620tmiwy1t6kibxcj` (`username_truong_diem_tap_ket`);

--
-- Indexes for table `truong_diem_giao_dich`
--
ALTER TABLE `truong_diem_giao_dich`
  ADD PRIMARY KEY (`username`),
  ADD KEY `FKdq4rc22t78g5ieeculka8fwwr` (`username_truong_diem_tap_ket`);

--
-- Indexes for table `truong_diem_tap_ket`
--
ALTER TABLE `truong_diem_tap_ket`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `van_chuyen_giao_dich_tap_ket`
--
ALTER TABLE `van_chuyen_giao_dich_tap_ket`
  ADD PRIMARY KEY (`ma_don_van_chuyen`),
  ADD KEY `FKjxp2364oyxk6oxa0sgsiyfnhe` (`username_truong_diem_tap_ket`),
  ADD KEY `FKpk3of8591ptl3cjfwi5v8gjle` (`ma_kien_hang`),
  ADD KEY `FKbrhq37j6tdb36v2mgtbvrmjbe` (`username_truong_diem_giao_dich`);

--
-- Indexes for table `van_chuyen_khach_hang_giao_dich`
--
ALTER TABLE `van_chuyen_khach_hang_giao_dich`
  ADD PRIMARY KEY (`ma_don_van_chuyen`),
  ADD KEY `FKbdox89u8rpnn96a438go63hua` (`username_khach_hang`),
  ADD KEY `FKb423y2ctjm6vmsl3nw7g39pml` (`ma_kien_hang`),
  ADD KEY `FKeyiwsrmhvyrp6m5l4bvh9hkcp` (`username_truong_diem_giao_dich`);

--
-- Indexes for table `van_chuyen_tap_ket_tap_ket`
--
ALTER TABLE `van_chuyen_tap_ket_tap_ket`
  ADD PRIMARY KEY (`ma_don_van_chuyen`),
  ADD KEY `FKc0ysvfa00kivksdig4hmsbgy2` (`ma_kien_hang`),
  ADD KEY `FK7n05kjgp8by6b7n5wv1b9ichn` (`username_truong_diem_tap_ket_nhan`),
  ADD KEY `FKh9qqstgw8hsefe0i69jq04bd9` (`username_truong_diem_tap_ket_gui`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `don_hang`
--
ALTER TABLE `don_hang`
  MODIFY `ma_don_hang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `kien_hang`
--
ALTER TABLE `kien_hang`
  MODIFY `ma_kien_hang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `van_chuyen_giao_dich_tap_ket`
--
ALTER TABLE `van_chuyen_giao_dich_tap_ket`
  MODIFY `ma_don_van_chuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `van_chuyen_khach_hang_giao_dich`
--
ALTER TABLE `van_chuyen_khach_hang_giao_dich`
  MODIFY `ma_don_van_chuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `van_chuyen_tap_ket_tap_ket`
--
ALTER TABLE `van_chuyen_tap_ket_tap_ket`
  MODIFY `ma_don_van_chuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `don_hang`
--
ALTER TABLE `don_hang`
  ADD CONSTRAINT `FKc1k7xhm3tr20uaw0o8d43hep2` FOREIGN KEY (`username_nguoi_nhan`) REFERENCES `khach_hang` (`username`),
  ADD CONSTRAINT `FKiv6omnmpau4y5xnxa9re3fwrq` FOREIGN KEY (`ma_kien_hang`) REFERENCES `kien_hang` (`ma_kien_hang`),
  ADD CONSTRAINT `FKjv8eeuef8h5an533mwotw0bs` FOREIGN KEY (`username_nguoi_gui`) REFERENCES `khach_hang` (`username`);

--
-- Constraints for table `nhan_vien_giao_dich`
--
ALTER TABLE `nhan_vien_giao_dich`
  ADD CONSTRAINT `FK6k7lqevkx7iuiaslxvef5dtc7` FOREIGN KEY (`username_truong_diem_giao_dich`) REFERENCES `truong_diem_giao_dich` (`username`);

--
-- Constraints for table `nhan_vien_giao_hang`
--
ALTER TABLE `nhan_vien_giao_hang`
  ADD CONSTRAINT `FKqxv32v76jrdvd5t4mvy8sxljw` FOREIGN KEY (`username_truong_diem_giao_dich`) REFERENCES `truong_diem_giao_dich` (`username`);

--
-- Constraints for table `nhan_vien_tap_ket`
--
ALTER TABLE `nhan_vien_tap_ket`
  ADD CONSTRAINT `FK4c90jnoc620tmiwy1t6kibxcj` FOREIGN KEY (`username_truong_diem_tap_ket`) REFERENCES `truong_diem_tap_ket` (`username`);

--
-- Constraints for table `truong_diem_giao_dich`
--
ALTER TABLE `truong_diem_giao_dich`
  ADD CONSTRAINT `FKdq4rc22t78g5ieeculka8fwwr` FOREIGN KEY (`username_truong_diem_tap_ket`) REFERENCES `truong_diem_tap_ket` (`username`);

--
-- Constraints for table `van_chuyen_giao_dich_tap_ket`
--
ALTER TABLE `van_chuyen_giao_dich_tap_ket`
  ADD CONSTRAINT `FKbrhq37j6tdb36v2mgtbvrmjbe` FOREIGN KEY (`username_truong_diem_giao_dich`) REFERENCES `truong_diem_giao_dich` (`username`),
  ADD CONSTRAINT `FKjxp2364oyxk6oxa0sgsiyfnhe` FOREIGN KEY (`username_truong_diem_tap_ket`) REFERENCES `truong_diem_tap_ket` (`username`),
  ADD CONSTRAINT `FKpk3of8591ptl3cjfwi5v8gjle` FOREIGN KEY (`ma_kien_hang`) REFERENCES `kien_hang` (`ma_kien_hang`);

--
-- Constraints for table `van_chuyen_khach_hang_giao_dich`
--
ALTER TABLE `van_chuyen_khach_hang_giao_dich`
  ADD CONSTRAINT `FKb423y2ctjm6vmsl3nw7g39pml` FOREIGN KEY (`ma_kien_hang`) REFERENCES `kien_hang` (`ma_kien_hang`),
  ADD CONSTRAINT `FKbdox89u8rpnn96a438go63hua` FOREIGN KEY (`username_khach_hang`) REFERENCES `khach_hang` (`username`),
  ADD CONSTRAINT `FKeyiwsrmhvyrp6m5l4bvh9hkcp` FOREIGN KEY (`username_truong_diem_giao_dich`) REFERENCES `truong_diem_giao_dich` (`username`);

--
-- Constraints for table `van_chuyen_tap_ket_tap_ket`
--
ALTER TABLE `van_chuyen_tap_ket_tap_ket`
  ADD CONSTRAINT `FK7n05kjgp8by6b7n5wv1b9ichn` FOREIGN KEY (`username_truong_diem_tap_ket_nhan`) REFERENCES `truong_diem_tap_ket` (`username`),
  ADD CONSTRAINT `FKc0ysvfa00kivksdig4hmsbgy2` FOREIGN KEY (`ma_kien_hang`) REFERENCES `kien_hang` (`ma_kien_hang`),
  ADD CONSTRAINT `FKh9qqstgw8hsefe0i69jq04bd9` FOREIGN KEY (`username_truong_diem_tap_ket_gui`) REFERENCES `truong_diem_tap_ket` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
