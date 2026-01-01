--
-- Table structure for table `roles`
--
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `users`
--
CREATE TABLE `users` (
  `enabled` boolean NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `surnames` varchar(255) NOT NULL,
  `zip_code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`email`),
  KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `categories`
--
CREATE TABLE `categories` (
  `enabled` boolean NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `user_favourite_categories`
--
CREATE TABLE `user_favourite_categories` (
  `category_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (user_id, category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `products`
--
CREATE TABLE `products` (
  `discount_price` double DEFAULT NULL,
  `enabled` boolean NOT NULL,
  `has_discount` boolean NOT NULL,
  `price` double NOT NULL,
  `rating` double NOT NULL,
  `rating_count` int NOT NULL,
  `shipping` double NOT NULL,
  `sold` int NOT NULL,
  `stock` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `shop_id` bigint NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `products_categories`
--
CREATE TABLE `products_categories` (
  `category_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  PRIMARY KEY (`product_id`,`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `product_images`
--
CREATE TABLE `product_images` (
  `product_id` bigint NOT NULL,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (product_id, image_url)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `user_favourite_products`
--
CREATE TABLE `user_favourite_products` (
  `product_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (user_id, product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `items`
--
CREATE TABLE `items` (
  `quantity` int NOT NULL,
  `sale_price` double NOT NULL,
  `sale_shipping` double NOT NULL,
  `total_price` double NOT NULL,
  `cart_user_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY (`product_id`),
  KEY (`cart_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `orders`
--
CREATE TABLE `orders` (
  `customer_id` bigint NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_id` bigint NOT NULL,
  `order_date` datetime(6) NOT NULL,
  `shop_id` bigint NOT NULL,
  `status` enum('CANCELLED','CONFIRMED','DELIVERED','PENDING','RETURNED','SHIPPED') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`item_id`),
  KEY (`customer_id`),
  KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `messages`
--
CREATE TABLE `messages` (
  `seen` boolean NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `message_date` datetime(6) NOT NULL,
  `recipient_id` bigint NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY (`recipient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `reviews`
--
CREATE TABLE `reviews` (
  `rating` int NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `review_date` datetime(6) NOT NULL,
  `author` varchar(255) NOT NULL,
  `comment` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `backups`
--
CREATE TABLE `backups` (
  `created_at` datetime(6) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `database_file_name` varchar(255) DEFAULT NULL,
  `images_file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
