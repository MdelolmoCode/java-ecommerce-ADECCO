INSERT INTO customer (id, email, name, phone, surname)
VALUES ('1', 'email1@email', 'Name1', '11111111', 'Surname1'),
       ('2', 'email2@email', 'Name2', '22222222', 'Surname2'),
       ('3', 'email3@email', 'Name3', '33333333', 'Surname3');

INSERT INTO orders (id, order_number, payment_method, address_id)
VALUES ('1', '1000', '0', null),
       ('2', '2000', '1', null),
       ('3', '3000', '2', null),
       ('4', '4000', '2', null),
       ('5', '5000', '2', null),
       ('6', '6000', '1', null);