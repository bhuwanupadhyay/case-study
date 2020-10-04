CREATE USER inventory WITH PASSWORD 'inventory' CREATEDB;
CREATE DATABASE inventory
    WITH
    OWNER = inventory
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
