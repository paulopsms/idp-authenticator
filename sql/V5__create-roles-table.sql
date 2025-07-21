CREATE TABLE public.roles
(
    id bigserial NOT NULL,
    role character varying,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.roles
    OWNER to idpuser;


INSERT INTO public.roles (role) values ('FRESH_USER');
INSERT INTO public.roles (role) values ('VERIFIED_USER');
INSERT INTO public.roles (role) values ('ADMIN');