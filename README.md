# Cupcake
BEGIN;


CREATE TABLE IF NOT EXISTS public."ordre"
(
    ordrer_nr serial NOT NULL,
    bruger_id integer NOT NULL,
    PRIMARY KEY (ordrer_nr)
);

CREATE TABLE IF NOT EXISTS public.bruger
(
    bruger_id serial NOT NULL,
    navn character varying NOT NULL,
    password character varying NOT NULL,
    addresse character varying[],
    rolle character varying NOT NULL DEFAULT 'USER',
    balance bigint,
    PRIMARY KEY (bruger_id)
);

CREATE TABLE IF NOT EXISTS public.ordrerlinje
(
    ordrer_nr integer NOT NULL,
    produkt_id integer NOT NULL,
    antal integer NOT NULL,
    linje_id serial NOT NULL,
    PRIMARY KEY (linje_id)
);

CREATE TABLE IF NOT EXISTS public.produkt
(
    produkt_id serial NOT NULL,
    kategori_id integer NOT NULL,
    bottom_id integer NOT NULL,
    top_id integer NOT NULL,
    PRIMARY KEY (produkt_id)
);

CREATE TABLE IF NOT EXISTS public.kategori
(
    kategori_id serial NOT NULL,
    beskrivelse character varying,
    navn character varying NOT NULL,
    PRIMARY KEY (kategori_id)
);

CREATE TABLE IF NOT EXISTS public.top
(
    top_id serial NOT NULL,
    top_navn character varying NOT NULL,
    top_beskrivelse character varying,
    PRIMARY KEY (top_id)
);

CREATE TABLE IF NOT EXISTS public.bottom
(
    bottom_id serial NOT NULL,
    bottom_navn character varying NOT NULL,
    bottom_beskrivelse character varying,
    PRIMARY KEY (bottom_id)
);

ALTER TABLE IF EXISTS public."ordre"
    ADD FOREIGN KEY (bruger_id)
    REFERENCES public.bruger (bruger_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.ordrerlinje
    ADD FOREIGN KEY (ordrer_nr)
    REFERENCES public."ordre" (ordrer_nr) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.ordrerlinje
    ADD FOREIGN KEY (produkt_id)
    REFERENCES public.produkt (produkt_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.produkt
    ADD FOREIGN KEY (kategori_id)
    REFERENCES public.kategori (kategori_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.produkt
    ADD FOREIGN KEY (bottom_id)
    REFERENCES public.bottom (bottom_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.produkt
    ADD FOREIGN KEY (top_id)
    REFERENCES public.top (top_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;
