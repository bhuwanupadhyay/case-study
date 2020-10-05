CREATE USER notification WITH PASSWORD 'notification' CREATEDB;
CREATE DATABASE notification
    WITH
    OWNER = notification
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
