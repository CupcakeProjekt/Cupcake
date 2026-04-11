BEGIN;

CREATE TABLE IF NOT EXISTS public."order"
(
    order_number serial NOT NULL,
    user_id integer NOT NULL,
    PRIMARY KEY (order_number)
    );

CREATE TABLE IF NOT EXISTS public.user
(
    user_id serial NOT NULL,
    name character varying NOT NULL,
    password character varying NOT NULL,
    address character varying[],
    role character varying NOT NULL DEFAULT 'USER',
    balance bigint,
    PRIMARY KEY (user_id)
    );

CREATE TABLE IF NOT EXISTS public.order_line
(
    order_number integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL,
    line_id serial NOT NULL,
    PRIMARY KEY (line_id)
    );

CREATE TABLE IF NOT EXISTS public.product
(
    product_id serial NOT NULL,
    category_id integer NOT NULL,
    bottom_id integer NOT NULL,
    top_id integer NOT NULL,
    PRIMARY KEY (product_id)
    );

CREATE TABLE IF NOT EXISTS public.category
(
    category_id serial NOT NULL,
    description character varying,
    name character varying NOT NULL,
    PRIMARY KEY (category_id)
    );

CREATE TABLE IF NOT EXISTS public.top
(
    top_id serial NOT NULL,
    top_name character varying NOT NULL,
    top_description character varying,
    PRIMARY KEY (top_id)
    );

CREATE TABLE IF NOT EXISTS public.bottom
(
    bottom_id serial NOT NULL,
    bottom_name character varying NOT NULL,
    bottom_description character varying,
    PRIMARY KEY (bottom_id)
    );

ALTER TABLE IF EXISTS public."order"
    ADD FOREIGN KEY (user_id)
    REFERENCES public.user (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.order_line
    ADD FOREIGN KEY (order_number)
    REFERENCES public."order" (order_number) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.order_line
    ADD FOREIGN KEY (product_id)
    REFERENCES public.product (product_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.product
    ADD FOREIGN KEY (category_id)
    REFERENCES public.category (category_id) MATCH SIMPLE
    ON UPDATE NO ACTION
       ON DELETE NO ACTION
    NOT VALID;