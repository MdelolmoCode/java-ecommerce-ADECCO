-- spring.sql.init.mode=always
-- spring.jpa.defer-datasource-initialization=true
-- spring.jpa.hibernate.ddl-auto=create


USE magpie;
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE  address;
INSERT INTO address (id, city, country, name, state, street_type, zipcode)
VALUES (1, 'London','England','Deninton','Big London','Road','98230'),
       (2, 'Madrid','Spain','Recoletos','Comunidad de Madrid','Paseo','68249'),
       (3, 'Pamplona','Spain','San Nicolas','Navarra','Calle','31000'),
       (4, 'Zaragoza','Spain','Cristobal Colon','Zaragoza','Plaza','58423'),
       (5, 'Sevilla','Spain','España','Sevilla','Plaza','32975'),
       (6, 'Paris','France','Liberte','Ile de France','Avenue','12896')
;

TRUNCATE TABLE category;
INSERT INTO category(id,name, mature)
VALUES  (1, 'arte',false),
        (2, 'bricolaje',false),
		(3, 'lencería',true),
		(4, 'armas de fuego',true),
		(5, 'armas blancas',true),
		(6, 'armas',true),
		(7, 'ropa',false),
		(8, 'bisutería',false)
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
VALUES  (1, 'L16516165', 'Pinturas Jaime', 1, '570278613'),
        (2, 'Q13274406', 'Merceria Evaristo', 3, '940888411'),
        (3, 'X55472064', 'Armeria Josefina', 5, '098088156')
;

TRUNCATE TABLE product;
INSERT INTO product (id, name, description, price, stock, available, manufacturer_id)
VALUES  (1, 'pincel','desc pincel',10.99, 10,true,1),
        (2, 'acuarelas', 'desc acuarelas', 20.99, 20,true, 1),
        (3, 'pincel de acuarelas', 'desc pincel de acuarelas', 30.99, 30,true, 1),
        (4, 'ropa de noche blanca', 'desc ropa de noche blanco', 40.99, 40,false, 2),
        (5, 'collar de noche', 'desc collar de noche', 50.99, 50,true, 2),
        (6, 'rodillo de pintura', 'desc rodillo de pintura', 60.99, 60,true, 2),
        (7, 'escopeta', 'desc escopeta', 70.99, 70,false, 3),
        (8, 'pistola', 'desc pistola', 80.99, 0,false, 3),
        (9, 'cuchillo', 'desc cuchillo', 90.99, 90,true, 3),
        (10, 'machete', 'desc machete', 100.99, 100,true, 3)
;

TRUNCATE TABLE product_categories;
INSERT INTO product_categories (product_id, categories_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (1, 2),
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
INSERT INTO shopping_cart (id, customer_id, total_price)
VALUES (1, 1, 0.0),
       (2, 1, 0.0),
       (3, 2, 0.0),
       (4, 2, 0.0),
       (5, 2, 0.0),
       (6, 3, 0.0)
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
       (12, 4, 4, 6),
       (13, 5, 5, 3),
       (14, 5, 6, 4),
       (15, 5, 7, 5),
       (16, 5, 8, 6),
       (17, 5, 1, 7),
       (18, 5, 2, 8),
       (19, 5, 3, 9),
       (20, 6, 4, 6),
       (21, 6, 5, 7),
       (22, 6, 6, 8),
       (23, 6, 7, 9),
       (24, 6, 8, 10)
;

TRUNCATE TABLE orders;
INSERT INTO orders (id, order_number, payment_method, address_id, shopping_cart_id)
VALUES (1, 1, 1, 1, 1),
       (2, 2, 2, 2, 2),
       (3, 3, 3, 3, 3),
       (4, 4, 1, 4, 4),
       (5, 5, 2, 5, 5),
       (6, 6, 3, 6, 6)
;

SET FOREIGN_KEY_CHECKS = 1;