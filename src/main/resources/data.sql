INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT(NAME)
VALUES ('RESTAURANT1'),
       ('RESTAURANT2'),
       ('RESTAURANT3');

INSERT INTO MENU(date_menu, restaurant_id, name)
VALUES ('2077-01-01', 1, 'MENU1'),
       ('2025-01-10', 1, 'MENU2'),
       ('2023-01-01', 1, 'MENU3'),

       ('2077-01-01', 2, 'MENU4'),
       ('2025-01-10', 2, 'MENU5'),

       ('2076-01-01', 3, 'MENU6'),
       ('2024-12-01', 3, 'MENU7');

INSERT INTO DISH(menu_id, price, name)
VALUES (1, 100, 'DISH1'),
       (1, 250.5, 'DISH2'),
       (1, 167.1, 'DISH3'),

       (2, 150, 'DISH4'),
       (3, 200, 'DISH5'),
       (4, 367.55, 'DISH6'),
       (5, 44, 'DISH7'),
       (6, 77, 'DISH8'),
       (7, 99, 'DISH9');

INSERT INTO VOTE(date, restaurant_id, user_id)
VALUES ('2077-01-01', 1, 2)

