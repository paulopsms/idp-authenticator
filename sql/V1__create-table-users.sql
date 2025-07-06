CREATE TABLE public."users"
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name character varying(100),
    email character varying(100) unique,
    password character varying(128),
    role character varying(50)
);