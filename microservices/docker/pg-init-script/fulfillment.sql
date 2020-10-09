CREATE USER fulfillment WITH PASSWORD 'fulfillment' CREATEDB;
CREATE DATABASE fulfillment
    WITH
    OWNER = fulfillment
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
