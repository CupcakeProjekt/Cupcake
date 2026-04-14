-- Dropper hele schema
DROP SCHEMA test CASCADE;
CREATE SCHEMA test;

-- Opret tabeller i den rigtige rækkefølge (først dem der ikke afhænger af andre)
CREATE TABLE test.users
(
    user_id  SERIAL PRIMARY KEY,
    email     TEXT NOT NULL,
    password TEXT NOT NULL,
    address  TEXT[],
    role     TEXT DEFAULT 'USER',
    balance  BIGINT
);

CREATE TABLE test.bottom
(
    bottom_id          SERIAL PRIMARY KEY,
    bottom_name        TEXT NOT NULL,
    bottom_description TEXT,
    price              NUMERIC
);

CREATE TABLE test.top
(
    top_id          SERIAL PRIMARY KEY,
    top_name        TEXT NOT NULL,
    top_description TEXT,
    price           NUMERIC
);

CREATE TABLE test.category
(
    category_id SERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    description TEXT
);

CREATE TABLE test.orders
(
    order_number SERIAL PRIMARY KEY,
    user_id      INTEGER NOT NULL REFERENCES test.users (user_id)
);

CREATE TABLE test.order_line
(
    line_id      SERIAL PRIMARY KEY,
    order_number INTEGER REFERENCES test.orders (order_number),
    top_id       INTEGER REFERENCES test.top (top_id),
    bottom_id    INTEGER REFERENCES test.bottom (bottom_id),
    quantity     INTEGER NOT NULL
);

-- Indsæt data i bottom
INSERT INTO test.bottom (bottom_name, bottom_description, price)
VALUES ('Chocolate', 'Classic chocolate bottom', 5.00),
       ('Vanilla', 'Delicious vanilla bottom', 5.00),
       ('Nutmeg', 'Aromatic nutmeg bottom', 5.00),
       ('Pistachio', 'Green pistachio bottom', 6.00),
       ('Almond', 'Crunchy almond bottom', 7.00);

-- Indsæt data i top
INSERT INTO test.top (top_name, top_description, price)
VALUES ('Chocolate', 'Chocolate topping', 5.00),
       ('Blueberry', 'Blueberry topping', 5.00),
       ('Raspberry', 'Raspberry topping', 5.00),
       ('Crispy', 'Crispy topping', 6.00),
       ('Strawberry', 'Strawberry topping', 6.00),
       ('Rum/Raisin', 'Rum and raisin topping', 7.00),
       ('Orange', 'Orange topping', 8.00),
       ('Lemon', 'Lemon topping', 8.00),
       ('Blue cheese', 'Blue cheese topping', 9.00);