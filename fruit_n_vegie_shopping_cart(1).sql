-- phpMyAdmin SQL Dump
-- version 5.0.0-alpha1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 07, 2020 at 08:41 AM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fruit_n_vegie_shopping_cart`
--

-- --------------------------------------------------------

--
-- Table structure for table `available_countries`
--

CREATE TABLE `available_countries` (
  `id` bigint(11) NOT NULL,
  `country` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `available_countries`
--

INSERT INTO `available_countries` (`id`, `country`) VALUES
(1, 'Sierra Leone');

-- --------------------------------------------------------

--
-- Table structure for table `cart_items`
--

CREATE TABLE `cart_items` (
  `id` bigint(11) NOT NULL,
  `cart_id` bigint(11) NOT NULL,
  `product_id` bigint(11) NOT NULL,
  `quantity` int(6) NOT NULL,
  `product_price` double NOT NULL,
  `product_size` enum('SMALL','MEDIUM','LARGE','') DEFAULT NULL,
  `sub_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart_items`
--

INSERT INTO `cart_items` (`id`, `cart_id`, `product_id`, `quantity`, `product_price`, `product_size`, `sub_total`) VALUES
(1, 1, 1, 5, 6000, NULL, 30000),
(2, 1, 2, 4, 7000, NULL, 28000),
(3, 2, 2, 7, 7000, NULL, 49000),
(4, 2, 1, 7, 6000, NULL, 42000),
(5, 3, 2, 4, 7000, NULL, 28000),
(6, 3, 1, 3, 6000, NULL, 18000),
(7, 4, 1, 7, 6000, NULL, 42000),
(8, 4, 2, 8, 7000, NULL, 56000),
(9, 5, 2, 10, 7000, NULL, 70000),
(10, 5, 1, 10, 6000, NULL, 60000);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` bigint(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `contact_email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `shipping_details_id` bigint(11) DEFAULT NULL,
  `shopping_cart_id` bigint(11) DEFAULT NULL,
  `is_active` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `user_id`, `first_name`, `last_name`, `contact_email`, `password`, `phone_number`, `shipping_details_id`, `shopping_cart_id`, `is_active`) VALUES
(1, 3, 'Ruvimbo', 'Chikuruwo', 'z40f64@gmail.com', '$2a$10$ikWwdPOxhACBCP3kRZ1u7uMB6pJSZ.p.q4C5p5z/RUzeBClsY2LsW', '0772800282', 3, 5, b'1'),
(2, 2, 'Nigel', 'Chikuruwo', 'mchikuruwo@hotmail.com', '$2a$10$MluVWBJdUUabazeCXJyMYuJ4gQaRIJpMoLSoTG5gxChR60WOYEhwy', '0772800281', 2, 4, b'1'),
(3, 1, 'Munyaradzi', 'Chikuruwo', 'roxnmugo@gmail.com', '$2a$10$YfY1u5PJPz6QhkpeACdH0urNSuG5eswlOgD4Nt1CAwy7flAoJ9ed6', '0772800280', 1, 1, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `delivery_methods`
--

CREATE TABLE `delivery_methods` (
  `id` bigint(11) NOT NULL,
  `delivery_method` varchar(50) NOT NULL,
  `shipping_cost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `delivery_methods`
--

INSERT INTO `delivery_methods` (`id`, `delivery_method`, `shipping_cost`) VALUES
(1, 'Normal Delivery', 6000);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `user_id`, `date`) VALUES
(1, 1, '2020-07-27 17:51:19'),
(2, 1, '2020-08-13 10:00:39'),
(3, 1, '2020-08-16 12:12:29'),
(4, 2, '2020-08-18 08:55:05'),
(5, 2, '2020-08-28 14:52:29');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(11) NOT NULL,
  `order_num` varchar(8) NOT NULL,
  `customer_id` bigint(11) NOT NULL,
  `shopping_cart_id` bigint(11) NOT NULL,
  `shipping_details_id` bigint(11) NOT NULL,
  `total` double NOT NULL,
  `date_ordered` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `order_num`, `customer_id`, `shopping_cart_id`, `shipping_details_id`, `total`, `date_ordered`) VALUES
(1, '99999', 1, 5, 3, 122000, '2020-09-01'),
(3, '99998', 1, 5, 3, 122000, '2020-09-01'),
(6, '10000', 1, 5, 3, 122000, '2020-09-02'),
(8, '69838', 1, 5, 3, 122000, '2020-09-02'),
(9, '94598', 1, 5, 3, 122000, '2020-09-03'),
(10, '85517', 1, 5, 3, 122000, '2020-09-03'),
(11, '25550', 1, 5, 3, 122000, '2020-09-03'),
(12, '54304', 1, 5, 3, 122000, '2020-09-03'),
(13, '32987', 1, 5, 3, 122000, '2020-09-03'),
(14, '64419', 1, 5, 3, 122000, '2020-09-03'),
(15, '93481', 1, 5, 3, 122000, '2020-09-03'),
(16, '24206', 1, 5, 3, 122000, '2020-09-03'),
(17, '36152', 1, 5, 3, 122000, '2020-09-03'),
(18, '87186', 1, 5, 3, 136000, '2020-09-04'),
(19, '58805', 1, 5, 3, 136000, '2020-09-04'),
(20, '84727', 1, 5, 3, 136000, '2020-09-04'),
(21, '76455', 1, 5, 3, 136000, '2020-09-04');

-- --------------------------------------------------------

--
-- Table structure for table `payment_methods`
--

CREATE TABLE `payment_methods` (
  `id` bigint(11) NOT NULL,
  `payment_method` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment_methods`
--

INSERT INTO `payment_methods` (`id`, `payment_method`) VALUES
(1, 'Payment on Delivery'),
(2, 'PayPal');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `product_price` double NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `size` enum('SMALL','MEDIUM','LARGE','') DEFAULT NULL,
  `quantity` int(6) NOT NULL,
  `in_stock` bit(1) NOT NULL DEFAULT b'0',
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `product_name`, `product_price`, `image_url`, `size`, `quantity`, `in_stock`, `date_created`, `date_updated`) VALUES
(1, 'Apple', 6000, NULL, '', 25000, b'1', '2020-07-27 18:01:08', '2020-07-27 18:01:59'),
(2, 'Banana', 7000, NULL, '', 30000, b'1', '2020-07-27 18:01:08', '2020-07-27 18:02:12');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'CUSTOMER'),
(3, 'GUEST');

-- --------------------------------------------------------

--
-- Table structure for table `shipping_details`
--

CREATE TABLE `shipping_details` (
  `id` bigint(11) NOT NULL,
  `customer_id` bigint(11) NOT NULL,
  `country_id` bigint(11) NOT NULL,
  `payment_method_id` bigint(11) NOT NULL,
  `delivery_method_id` bigint(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(50) NOT NULL,
  `postal_code` varchar(4) NOT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shipping_details`
--

INSERT INTO `shipping_details` (`id`, `customer_id`, `country_id`, `payment_method_id`, `delivery_method_id`, `address`, `city`, `postal_code`, `date_created`, `date_updated`) VALUES
(1, 3, 1, 1, 1, '667 VineWood Road, Sunway City', 'Harare', '2002', '2020-08-26 09:11:57', '2020-08-26 09:11:57'),
(2, 2, 1, 1, 1, '668 PineWood Road, Sunway City', 'Harare', '2002', '2020-08-26 09:12:22', '2020-08-26 09:12:22'),
(3, 1, 1, 1, 1, '668 LanceWood Road, Sunway City', 'Harare', '2002', '2020-08-26 09:12:35', '2020-08-26 09:12:35');

-- --------------------------------------------------------

--
-- Table structure for table `shopping_cart`
--

CREATE TABLE `shopping_cart` (
  `id` bigint(11) NOT NULL,
  `customer_id` bigint(11) NOT NULL,
  `sub_total` double DEFAULT NULL,
  `is_confirmed` bit(1) NOT NULL DEFAULT b'0',
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shopping_cart`
--

INSERT INTO `shopping_cart` (`id`, `customer_id`, `sub_total`, `is_confirmed`, `date_created`, `date_updated`) VALUES
(1, 3, 58000, b'1', '2020-08-26 09:15:51', '2020-08-26 09:27:09'),
(2, 2, 91000, b'1', '2020-08-26 09:15:57', '2020-08-26 09:44:13'),
(3, 1, 46000, b'1', '2020-08-26 09:16:04', '2020-08-26 09:42:59'),
(4, 2, 98000, b'1', '2020-08-26 09:54:25', '2020-08-26 09:58:26'),
(5, 1, 130000, b'1', '2020-08-27 07:22:59', '2020-09-03 20:09:03');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email_address` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL DEFAULT b'1',
  `customer_id` bigint(11) DEFAULT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp(),
  `date_updated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email_address`, `password`, `is_active`, `customer_id`, `date_created`, `date_updated`) VALUES
(1, 'roxnmugo@gmail.com', '$2a$10$YfY1u5PJPz6QhkpeACdH0urNSuG5eswlOgD4Nt1CAwy7flAoJ9ed6', b'1', NULL, '2020-07-27 17:49:10', '2020-07-27 17:49:10'),
(2, 'mchikuruwo@hotmail.com', '$2a$10$MluVWBJdUUabazeCXJyMYuJ4gQaRIJpMoLSoTG5gxChR60WOYEhwy', b'1', NULL, '2020-08-18 08:53:37', '2020-08-18 08:53:37'),
(3, 'z40f64@gmail.com', '$2a$10$ikWwdPOxhACBCP3kRZ1u7uMB6pJSZ.p.q4C5p5z/RUzeBClsY2LsW', b'1', NULL, '2020-08-18 11:21:09', '2020-08-18 11:21:09'),
(4, 'z040f63@gmail.com', '$2a$10$L1iLoqAU5cDYSmkXwJUkNuZCNXmxlbSNxFMTKyFsqIP9vBysdmucO', b'1', NULL, '2020-08-27 10:35:49', '2020-08-27 10:35:49'),
(5, 'z400f63@gmail.com', '$2a$10$r2MsCP1mse9ul/3mxBhnxOP2PQaWLsTlBlbtyl0xdrKQ4/jRUVEwi', b'1', NULL, '2020-08-27 10:50:12', '2020-08-27 10:50:12');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 2),
(4, 4, 2),
(5, 5, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `available_countries`
--
ALTER TABLE `available_countries`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cart_id` (`cart_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `contact_email` (`contact_email`),
  ADD UNIQUE KEY `phone_number` (`phone_number`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `shipping_details_id` (`shipping_details_id`),
  ADD KEY `shopping_cart_id` (`shopping_cart_id`);

--
-- Indexes for table `delivery_methods`
--
ALTER TABLE `delivery_methods`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `order_num` (`order_num`),
  ADD KEY `shopping_cart_id` (`shopping_cart_id`),
  ADD KEY `shipping_details_id` (`shipping_details_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `payment_methods`
--
ALTER TABLE `payment_methods`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shipping_details`
--
ALTER TABLE `shipping_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `country_id` (`country_id`),
  ADD KEY `delivery_method_id` (`delivery_method_id`),
  ADD KEY `FKdwqugp12wq823fs2xo8rg70uq` (`payment_method_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_address` (`email_address`),
  ADD UNIQUE KEY `customer_id` (`customer_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `available_countries`
--
ALTER TABLE `available_countries`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cart_items`
--
ALTER TABLE `cart_items`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `delivery_methods`
--
ALTER TABLE `delivery_methods`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `payment_methods`
--
ALTER TABLE `payment_methods`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `shipping_details`
--
ALTER TABLE `shipping_details`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `shopping_cart`
--
ALTER TABLE `shopping_cart`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`cart_id`) REFERENCES `shopping_cart` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_items_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `customer_ibfk_2` FOREIGN KEY (`shipping_details_id`) REFERENCES `shipping_details` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `customer_ibfk_3` FOREIGN KEY (`shopping_cart_id`) REFERENCES `shopping_cart` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`shopping_cart_id`) REFERENCES `shopping_cart` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`shipping_details_id`) REFERENCES `shipping_details` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_ibfk_4` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `shipping_details`
--
ALTER TABLE `shipping_details`
  ADD CONSTRAINT `FKdwqugp12wq823fs2xo8rg70uq` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_methods` (`id`),
  ADD CONSTRAINT `shipping_details_ibfk_2` FOREIGN KEY (`country_id`) REFERENCES `available_countries` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `shipping_details_ibfk_3` FOREIGN KEY (`delivery_method_id`) REFERENCES `delivery_methods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `shipping_details_ibfk_4` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD CONSTRAINT `shopping_cart_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

