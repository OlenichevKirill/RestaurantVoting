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