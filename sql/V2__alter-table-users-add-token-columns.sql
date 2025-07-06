alter table users
    ADD COLUMN token character varying (64),
    ADD COLUMN token_expiration timestamp;