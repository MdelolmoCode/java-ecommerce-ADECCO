INSERT INTO address (id, city, country, name, state, street_type, zipcode)
VALUES (1L, 'London','England','Deninton','Big London','Road','98230'),
       (2L, 'Madrid','Spain','Recoletos','Comunidad de Madrid','Paseo','68249'),
       (3L, 'Pamplona','Spain','San Nicolas','Navarra','Calle','31000'),
       (4L, 'Zaragoza','Spain','Cristobal Colon','Zaragoza','Plaza','58423'),
       (5L, 'Sevilla','Spain','España','Sevilla','Plaza','32975'),
       (6L, 'Paris','France','Liberte','Ile de France','Avenue','12896')
;

INSERT INTO category(id,name, mature)
VALUES  (1L, 'arte',false),
        (2L, 'bricolaje',false),
		(3L, 'lencería',true),
		(4L, 'armas de fuego',true),
		(5L, 'armas blancas',true),
		(6L, 'armas',true),
		(7L, 'ropa',false),
		(8L, 'bisutería',false)
;

INSERT INTO customer (id, email, name, phone, surname)
VALUES (1L, 'mdelpilar@correo.com', 'Maria', 123456789, 'Del Pilar'),
       (2L, 'pbotero@email.com', 'Pepe', 987654321, 'Botero'),
       (3L, 'ogeremias@lalala.com', 'Otilio', 87123456, 'Geremias')
;


-- ALTER TABLE customer_addresses
--     ADD PRIMARY KEY (customer_id, addresses_id);
--
-- INSERT INTO customer_addresses (customer_id, addresses_id)
-- VALUES (1L, 1L),
--        (1L, 2L),
--        (1L, 3L),
--        (2L, 2L),
--        (2L, 5L),
--        (2L, 6L),
--        (3L, 2L),
--        (3L, 4L)
-- ;

INSERT INTO manufacturer (id, cif, name, address_id, phone_number)
VALUES  (1L, 'L16516165', 'Pinturas Jaime', 1L, '570278613'),
        (2L, 'Q13274406', 'Merceria Evaristo', 3L, '940888411'),
        (3L, 'X55472064', 'Armeria Josefina', 5L, '098088156')
;

INSERT INTO product (id, name, description, price, stock, available, manufacturer_id)
VALUES  (1L, 'pincel','desc pincel',10.99, 10L,true,1L),
        (2L, 'acuarelas', 'desc acuarelas', 20.99, 20L,true, 1L),
        (3L, 'pincel de acuarelas', 'desc pincel de acuarelas', 30.99, 30L,true, 1L),
        (4L, 'ropa de noche blanca', 'desc ropa de noche blanco', 40.99, 40L,false, 2L),
        (5L, 'collar de noche', 'desc collar de noche', 50.99, 50L,true, 2L),
        (6L, 'rodillo de pintura', 'desc rodillo de pintura', 60.99, 60L,true, 2L),
        (7L, 'escopeta', 'desc escopeta', 70.99, 70L,false, 3L),
        (8L, 'pistola', 'desc pistola', 80.99, 0L,false, 3L),
        (9L, 'cuchillo', 'desc cuchillo', 90.99, 90L,true, 3L),
        (10L, 'machete', 'desc machete', 100.99, 100L,true, 3L)
;

INSERT INTO product_categories (product_id, categories_id)
VALUES (1L, 1L),
       (1L, 2L),
       (2L, 1L),
       (1L, 2L),
       (3L, 1L),
       (3L, 2L),
       (4L, 7L),
       (4L, 3L),
       (5L, 7L),
       (5L, 8L),
       (6L, 1L),
       (6L, 2L),
       (7L, 4L),
       (7L, 6L),
       (8L, 4L),
       (8L, 6L),
       (9L, 5L),
       (9L, 6L),
       (10L, 5L),
       (10L, 6L)
;

INSERT INTO shopping_cart (id, customer_id, total_price)
VALUES (1L, 1L, 0.0),
       (2L, 1L, 0.0),
       (3L, 2L, 0.0),
       (4L, 2L, 0.0),
       (5L, 2L, 0.0),
       (6L, 3L, 0.0)
;

INSERT INTO cart_item (cart_item_id, shopping_cart_id, amount, product_id)
VALUES (1L, 1L, 1, 1L),
       (2L, 1L, 2, 2L),
       (3L, 1L, 3, 3L),
       (4L, 2L, 4, 1L),
       (5L, 2L, 5, 2L),
       (6L, 3L, 6, 1L),
       (7L, 3L, 7, 2L),
       (8L, 3L, 8, 3L),
       (9L, 3L, 1, 4L),
       (10L, 3L, 2, 5L),
       (11L, 3L, 3, 6L),
       (12L, 4L, 4, 6L),
       (13L, 5L, 5, 3L),
       (14L, 5L, 6, 4L),
       (15L, 5L, 7, 5L),
       (16L, 5L, 8, 6L),
       (17L, 5L, 1, 7L),
       (18L, 5L, 2, 8L),
       (19L, 5L, 3, 9L),
       (20L, 6L, 4, 6L),
       (21L, 6L, 5, 7L),
       (22L, 6L, 6, 8L),
       (23L, 6L, 7, 9L),
       (24L, 6L, 8, 10L)
;

INSERT INTO orders (id, order_number, address_id, shopping_cart_id)
VALUES (1L, 1L, 1L, 1L),
       (2L, 2L, 2L, 2L),
       (3L, 3L, 3L, 3L),
       (4L, 4L, 4L, 4L),
       (5L, 5L, 5L, 5L),
       (6L, 6L, 6L, 6L)
;