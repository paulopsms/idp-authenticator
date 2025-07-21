CREATE TABLE public.users_roles
(
    id bigserial,
    user_id uuid NOT NULL,
    role_id bigint,

    CONSTRAINT fk_users_roles_user_id FOREIGN KEY (user_id) REFERENCES public.users (id),
    CONSTRAINT fk_users_roles_role_id FOREIGN KEY (role_id) REFERENCES public.roles (id)
);

ALTER TABLE IF EXISTS public.users_roles
    OWNER to idpuser;