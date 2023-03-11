-- spring.sql.init.mode=always
-- spring.jpa.defer-datasource-initialization=true
-- spring.jpa.hibernate.ddl-auto=create


USE magpie;
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE  address;
INSERT INTO address (id, city, country, name, state, street_type, zipcode)
VALUES (1, 'Tokyo','Japan','Deninton','Minami Ward','Road','98230'),
       (2, 'Madrid','Spain','Recoletos','Comunidad de Madrid','Paseo','68249'),
       (3, 'Tokyo','Japan','Kuramae','Asakusa','Road','31000'),
       (4, 'Zaragoza','Spain','Cristobal Colon','Zaragoza','Plaza','58423'),
       (5, 'Sevilla','Spain','España','Sevilla','Plaza','32975'),
       (6, 'Paris','France','Liberte','Ile de France','Avenue','12896')
;

TRUNCATE TABLE category;
INSERT INTO category(id,name, mature)
VALUES  (1, 'videojuegos',false),
        (2, 'juguetes',false),
		(3, 'lencería',true),
		(4, 'armas de fuego',true),
		(5, 'armas blancas',true),
		(6, 'armas',true),
		(7, 'ropa',false),
		(8, 'bisutería',false),
		(9, 'superheroes', false)
;

TRUNCATE TABLE customer;
INSERT INTO customer (id, email, name, phone, surname)
VALUES (1, 'mdelpilar@correo.com', 'Maria', 123456789, 'Del Pilar'),
       (2, 'pbotero@email.com', 'Pepe', 987654321, 'Botero'),
       (3, 'ogeremias@lalala.com', 'Otilio', 87123456, 'Geremias')
;


ALTER TABLE customer_address
    ADD PRIMARY KEY (customer_id, address_id);

ALTER TABLE customer_address
    ADD FOREIGN KEY (address_id) REFERENCES addresses(id);

ALTER TABLE customer_address
    ADD FOREIGN KEY (customer_id) REFERENCES customer(id);

TRUNCATE TABLE customer_address;
INSERT INTO customer_address (customer_id, address_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 5),
       (2, 6),
       (3, 2),
       (3, 4)
;

TRUNCATE TABLE manufacturer;
INSERT INTO manufacturer (id, cif, name, address_id, phone_number)
VALUES  (1, 'L16516165', 'Nintendo Company, Ltd.', 1, '570278613'),
        (2, 'Q13274406', 'Bandai', 3, '940888411'),
        (3, 'X55472064', 'Armeria Josefina', 5, '098088156')
;

TRUNCATE TABLE product;

INSERT INTO product (id, name, description, price, stock, available, manufacturer_id, image_url)
VALUES
    (1, 'GAME BOY', 'Nintendo Gameboy Color verde pistacho', 123.95, 10, true, 1, 'img/2.jpg'),
    (2, 'MEGAZORD', 'Power Rangers Megazord Dino', 50.95, 20, true, 2, 'img/megazord2.png'),
    (3, 'JUEGO OCA', 'Juego de mesa de la oca con dados', 9.95, 30, true, 1, 'img/oca2.jpg'),
    (4, 'JHAYBERG', 'Zapatillas Jhayber usadas (buen estado)', 12.50, 40, false, 2, 'img/Jhayber.jpg'),
    (5, 'MOTOCICLETA', 'Vespino Piaggio F9 año 1995 49cc', 450.00, 50, true, 2, 'img/vespino.jpg'),
    (6, 'rodillo de pintura', 'desc rodillo de pintura', 60.99, 60, true, 2, 'https://dummyimage.com/250x250/b8b8b8/000000.jpg&text=Rodillo+de+pintura'),
    (7, 'escopeta', 'desc escopeta', 70.99, 70, false, 3, 'https://dummyimage.com/250x250/b8b8b8/000000.jpg&text=Escopeta'),
    (8, 'pistola', 'desc pistola', 80.99, 0, false, 3, 'https://dummyimage.com/250x250/b8b8b8/000000.jpg&text=Pistola'),
    (9, 'cuchillo', 'desc cuchillo', 90.99, 90, true, 3, 'https://dummyimage.com/250x250/b8b8b8/000000.jpg&text=Cuchillo'),
    (10, 'machete', 'desc machete', 100.99, 100, true, 3, 'https://dummyimage.com/250x250/b8b8b8/000000.jpg&text=Machete');


TRUNCATE TABLE product_categories;
INSERT INTO product_categories (product_id, categories_id)
VALUES (1, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (4, 7),
       (4, 3),
       (5, 7),
       (5, 8),
       (6, 1),
       (6, 2),
       (7, 4),
       (7, 6),
       (8, 4),
       (8, 6),
       (9, 5),
       (9, 6),
       (10, 5),
       (10, 6)
;

TRUNCATE TABLE shopping_cart;
INSERT INTO shopping_cart (id, customer_id)
VALUES (1, 1),
       (2, 2),
       (3, 3)
;

TRUNCATE TABLE cart_item;
INSERT INTO cart_item (cart_item_id, shopping_cart_id, amount, product_id)
VALUES (1, 1, 1, 1),
       (2, 1, 2, 2),
       (3, 1, 3, 3),
       (4, 2, 4, 1),
       (5, 2, 5, 2),
       (6, 3, 6, 1),
       (7, 3, 7, 2),
       (8, 3, 8, 3),
       (9, 3, 1, 4),
       (10, 3, 2, 5),
       (11, 3, 3, 6),
       (12, 1, 4, 6),
       (13, 1, 5, 3),
       (14, 1, 6, 4),
       (15, 2, 7, 5),
       (16, 2, 8, 6),
       (17, 2, 1, 7),
       (18, 2, 2, 8),
       (19, 2, 3, 9),
       (20, 2, 4, 6),
       (21, 3, 5, 7),
       (22, 3, 6, 8),
       (23, 3, 7, 9),
       (24, 3, 8, 10)
;

/*
TRUNCATE TABLE orders;
INSERT INTO orders (id, order_number, payment_method, address_id, customer_id)
VALUES (1, 1, 0, 1, 1),
       (2, 2, 1, 2, 2),
       (3, 3, 2, 3, 3)
;

TRUNCATE TABLE orders_cart_items;
INSERT INTO orders_cart_items (order_id, cart_items_cart_item_id)
VALUES (1, 1),
       (1, 2),
       (1, 3)
;
*/

TRUNCATE TABLE users;
INSERT INTO users (id, customer_id, username, password)
VALUES (1, 1, 'user1', '$2a$10$wWR17vqMMwXZbk.Lj2IEguDQzRJF9BpY2no/W1o8ZR4ZK8slysLsK'), --pass1
       (2, 2, 'user2', '$2a$10$c6D7.y9IXbEksCUh1PKjv.vAglBy49rR0haaXgxDdhU2qA5arAhmm'), --pass2
       (3, 3, 'user3', '$2a$10$Gx8gTReYFXnL4CGnRvksgemvfjWG0CGKaQYaVmIgtNWrhhjTX9ZdC'); --pass3

SET FOREIGN_KEY_CHECKS = 1;