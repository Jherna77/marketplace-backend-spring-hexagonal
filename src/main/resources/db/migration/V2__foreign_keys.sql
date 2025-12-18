--
-- Foreign Key Constraints
--

/* USERS → ROLES */
ALTER TABLE users
  ADD CONSTRAINT fk_users_role
  FOREIGN KEY (role_id)
  REFERENCES roles(id);

/* CATEGORIES ↔ USERS (favourites) */
ALTER TABLE user_favourite_categories
  ADD CONSTRAINT fk_ufc_user
  FOREIGN KEY (user_id)
  REFERENCES users(id);

ALTER TABLE user_favourite_categories
  ADD CONSTRAINT fk_ufc_category
  FOREIGN KEY (category_id)
  REFERENCES categories(id);

/* PRODUCTS → USERS (shop) */
ALTER TABLE products
  ADD CONSTRAINT fk_products_shop
  FOREIGN KEY (shop_id)
  REFERENCES users(id);

/* PRODUCTS ↔ CATEGORIES */
ALTER TABLE products_categories
  ADD CONSTRAINT fk_pc_product
  FOREIGN KEY (product_id)
  REFERENCES products(id);

ALTER TABLE products_categories
  ADD CONSTRAINT fk_pc_category
  FOREIGN KEY (category_id)
  REFERENCES categories(id);

/* PRODUCT IMAGES → PRODUCTS */
ALTER TABLE product_images
  ADD CONSTRAINT fk_product_images_product
  FOREIGN KEY (product_id)
  REFERENCES products(id);

/* ITEMS → PRODUCTS */
ALTER TABLE items
  ADD CONSTRAINT fk_items_product
  FOREIGN KEY (product_id)
  REFERENCES products(id);

/* ITEMS → USERS (cart) */
ALTER TABLE items
  ADD CONSTRAINT fk_items_cart_user
  FOREIGN KEY (cart_user_id)
  REFERENCES users(id);

/* ORDERS → ITEMS */
ALTER TABLE orders
  ADD CONSTRAINT fk_orders_item
  FOREIGN KEY (item_id)
  REFERENCES items(id);

/* ORDERS → USERS (customer) */
ALTER TABLE orders
  ADD CONSTRAINT fk_orders_customer
  FOREIGN KEY (customer_id)
  REFERENCES users(id);

/* ORDERS → USERS (shop) */
ALTER TABLE orders
  ADD CONSTRAINT fk_orders_shop
  FOREIGN KEY (shop_id)
  REFERENCES users(id);

/* REVIEWS → ORDERS */
ALTER TABLE reviews
  ADD CONSTRAINT fk_reviews_order
  FOREIGN KEY (order_id)
  REFERENCES orders(id);

/* MESSAGES → USERS */
ALTER TABLE messages
  ADD CONSTRAINT fk_messages_recipient
  FOREIGN KEY (recipient_id)
  REFERENCES users(id);

/* USER FAVOURITE PRODUCTS */
ALTER TABLE user_favourite_products
  ADD CONSTRAINT fk_ufp_user
  FOREIGN KEY (user_id)
  REFERENCES users(id);

ALTER TABLE user_favourite_products
  ADD CONSTRAINT fk_ufp_product
  FOREIGN KEY (product_id)
  REFERENCES products(id);
