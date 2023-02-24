INSERT INTO category(name, mature)
VALUES  ("arte",false),
        ("bricolaje",false),
		("lencería",true),
		("armas de fuego",true),
		("armas blancas",true),
		("armas",true),
		("ropa",false),
		("bisutería",false)
;

INSERT INTO product (name, description, price, stock, available, manufacturer_id)
VALUES  ('pincel','desc pincel',10.99,10L,true,null),
        ('acuarelas', 'desc acuarelas', 20.99, 20L,true, null),
        ('pincel de acuarelas', 'desc pincel de acuarelas', 30.99, 30L,true, null),
        ('ropa de noche blanca', 'desc ropa de noche blanco', 40.99, 40L,true, null),
        ('collar de noche', 'desc collar de noche', 50.99, 50L,true, null),
        ('rodillo de pintura', 'desc rodillo de pintura', 60.99, 60L,true, null),
        ('escopeta', 'desc escopeta', 70.99, 70L,false, null),
        ('pistola', 'desc pistola', 80.99, 0L,false, null),
        ('cuchillo', 'desc cuchillo', 90.99, 90L,true, null),
        ('machete', 'desc machete', 100.99, 100L,true, null)
;

INSERT INTO shopping_cart (customer_id, total_price)
VALUES
                         (null, 1.0),
                         (null, 2.0),
                         (null, 3.0),
                         (null, 4.0),
                         (null, 5.0),
                         (null, 6.0)
;

INSERT INTO cart_item (shopping_cart_id, product_id, amount)
VALUES
    -- shoppingCart 1:
                      (        1       ,      1    ,   1   ),
                      (        1       ,      2    ,   1   ),
                      (        1       ,      3    ,   2   ),
    -- shoppingCart 2:
                      (        2       ,      1    ,   1   ),
                      (        2       ,      2    ,   6   ),
                      (        2       ,      3    ,   2   ),
    -- shoppingCart 3:
                      (        3       ,      1    ,   1   ),
                      (        3       ,      2    ,   1   ),
                      (        3       ,      3    ,   6   )
;