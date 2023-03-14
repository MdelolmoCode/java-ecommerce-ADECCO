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
       (6, 'Paris','France','Liberte','Ile de France','Avenue','12896'),
       (7, 'Trmice','República Checa','Dinamo libery','Moravia','Street','89837'),
       (9, 'Tokyo', 'Japón', 'Kanamura', 'Arigato', 'Street', '837362')
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
		(8, 'bisutería',false),
		(9, 'juegos', false)
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
        (3, 'X55472064', 'Armeria Josefina', 5, '098088156'),
        (4, 'L88787787', 'De Walt', 7, '676637328'),
        (5, 'X87363672', 'Nintendo', 9, '97363728')
;

TRUNCATE TABLE product;
INSERT INTO product (id, name, description, price, stock, available, manufacturer_id, image_url)
VALUES  (1, 'Pincel','Pincel 3/9 negro.',10.99, 10,true,1, 'db_artiom-vallat-v6n0iUuD4M0-unsplash.jpg'),
        (2, 'Acuarelas', 'Juego de pintura de acuarela sólida de 36 colores.', 20.99, 20,true, 1, 'db_elena-mozhvilo-gp4hlZC2aFs-unsplash.jpg'),
        (3, 'Pincel de acuarelas', 'Pluma de pincel para colorear con depósito de agua.', 30.99, 30,true, 1, 'db_denise-johnson-3DkouQeZjp4-unsplash.jpg'),
        (4, 'Rodillo de pintura', 'Rodillo de pintura de 6 pulgadas.', 60.99, 60,true, 2, 'db_theme-photos-Cl-OpYWFFm0-unsplash.jpg'),
        (5, 'Pincel sintético', 'Pincel sintético ergonómico para acrílicos y óleos.', 60.95, 60, true, 2, 'db_andres-perez-se0GXEsgtPk-unsplash.jpg'),
        (6, 'Ropa de noche blanca', 'Ropa de noche blanca.', 40.99, 40,false, 2, 'db_katy-duclos-M5_gg2SdZfI-unsplash.jpg'),
        (7, 'Set camisetas', 'Set de 10 camisetas de diverso color.', 100.90, 40,false, 2, 'db_parker-burchfield-tvG4WvjgsEY-unsplash.jpg'),
        (8, 'Collar de noche', 'Collar de plata.', 50.99, 50,true, 2, 'db_eve-maier-7uQQHORcTpc-unsplash.jpg'),
        (9, 'Escopeta', 'Escopeta semiautomática.', 70.99, 70,false, 3, 'db_thomas-tucker-VJRk64F2nhs-unsplash.jpg'),
        (10, 'Pistola', 'Pistola.', 80.99, 0,false, 3, 'db_jay-rembert-e0kgA5otj0Q-unsplash.jpg'),
        (11, 'Cuchillo', 'Cuchillo de cocina, hoja de acero inoxidable.', 90.99, 90,true, 3, 'db_marios-gkortsilas-aAiy88ytl0c-unsplash.jpg'),
        (12, 'Machete', 'Cuchillo de supervivencia para exteriores.', 100.99, 100,true, 3, 'db_taylor-daugherty-K0GO2Hd1lRw-unsplash.jpg'),
        (13, 'AK-47', 'Arma AK-47 a estrenar.', 300.94, 10,true, 3, 'ak47.jpg'),
        (14, 'Taladro', 'Taladro De Walt 18 vatios.', 150.00, 30, true,4, 'taladro.jpg'),
        (15, 'Anillo', 'Anillo de boda.', 100.00, 30, true,2, 'anillo.jpg'),
        (16, 'Francotirador', 'Francotirador de precisión', 878.50, 20, true, 4, 'francotirador.jpg'),
        (17, 'Alicates', 'Alicate corte diagonal 160MM', 10.65, 20, true, 4, 'alicates.jpg')
;

TRUNCATE TABLE product_categories;
INSERT INTO product_categories (product_id, categories_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (4, 2),
       (5, 1),
       (6, 7),
       (6, 3),
       (7, 7),
       (8, 8),
       (9, 4),
       (9, 6),
       (10, 4),
       (10, 6),
       (11, 5),
       (11, 6),
       (12, 5),
       (12, 6),
       (13, 6),
       (13, 4),
       (14, 2),
       (15, 8),
       (16, 4),
       (17, 2)
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

TRUNCATE TABLE users;
INSERT INTO users (id, customer_id, username, password)
VALUES (1, 1, 'user1', '$2a$10$wWR17vqMMwXZbk.Lj2IEguDQzRJF9BpY2no/W1o8ZR4ZK8slysLsK'), --pass1
       (2, 2, 'user2', '$2a$10$c6D7.y9IXbEksCUh1PKjv.vAglBy49rR0haaXgxDdhU2qA5arAhmm'), --pass2
       (3, 3, 'user3', '$2a$10$Gx8gTReYFXnL4CGnRvksgemvfjWG0CGKaQYaVmIgtNWrhhjTX9ZdC'); --pass3

SET FOREIGN_KEY_CHECKS = 1;