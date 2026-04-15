-- Dropper hele schema
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

-- Opret tabeller i den rigtige rækkefølge (først dem der ikke afhænger af andre)
CREATE TABLE users
(
    user_id  SERIAL PRIMARY KEY,
    email     TEXT NOT NULL,
    password TEXT NOT NULL,
    role     TEXT DEFAULT 'USER',
    balance  BIGINT
);

CREATE TABLE bottom
(
    bottom_id          SERIAL PRIMARY KEY,
    bottom_name        TEXT NOT NULL,
    price              NUMERIC
);

CREATE TABLE top
(
    top_id          SERIAL PRIMARY KEY,
    top_name        TEXT NOT NULL,
    price           NUMERIC
);

CREATE TABLE orders
(
    order_number SERIAL PRIMARY KEY,
    user_id      INTEGER NOT NULL REFERENCES users (user_id)
);

CREATE TABLE order_line
(
    line_id      SERIAL PRIMARY KEY,
    order_number INTEGER REFERENCES orders (order_number),
    top_id       INTEGER REFERENCES top (top_id),
    bottom_id    INTEGER REFERENCES bottom (bottom_id),
    quantity     INTEGER NOT NULL
);

-- Indsæt data i bottom
INSERT INTO bottom (bottom_name, price)
VALUES ('Chocolate', 5.00),
       ('Vanilla', 5.00),
       ('Nutmeg', 5.00),
       ('Pistachio', 6.00),
       ('Almond', 7.00);

-- Indsæt data i top
INSERT INTO top (top_name, price)
VALUES ('Chocolate', 5.00),
       ('Blueberry', 5.00),
       ('Raspberry', 5.00),
       ('Crispy', 6.00),
       ('Strawberry', 6.00),
       ('Rum/Raisin', 7.00),
       ('Orange', 8.00),
       ('Lemon', 8.00),
       ('Blue cheese', 9.00);