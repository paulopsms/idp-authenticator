CREATE TABLE public."users"
(
    id uuid NOT NULL,
    name character varying(100),
    email character varying(100),
    password character varying(100),
    role character varying(40),
    PRIMARY KEY (id)
);