alter table users
  ALTER COLUMN role TYPE user_roles using role::user_roles;