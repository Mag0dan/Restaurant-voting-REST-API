INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (NAME)
VALUES  ('Столовая №1'),
        ('Ресторан на Арбате'),
        ('Кафе у дома'),
        ('Макдональдс');

INSERT INTO VOTE (USER_ID, RESTAURANT_ID)
VALUES  (1, 1),
        (2, 1);

INSERT INTO DISH (NAME, PRICE, RESTAURANT_ID)
VALUES ('Картофельное пюре', 350, 1),
       ('Котлета', 555, 1),
       ('Ризотто с трюфельным соусом', 99999, 2),
       ('Стейк Рибай 100гр', 999999, 2),
       ('Пицца', 5000, 3),
       ('Бигмак', 4500, 4);
