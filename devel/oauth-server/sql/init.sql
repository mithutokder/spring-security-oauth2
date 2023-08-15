DROP DATABASE postgres;
CREATE USER "admin-user" WITH PASSWORD 'admin@123';

CREATE DATABASE "authorization-db";
\c "authorization-db";
DROP SCHEMA public;
CREATE SCHEMA catalog;

GRANT ALL PRIVILEGES ON DATABASE "authorization-db" TO "admin-user";
GRANT ALL PRIVILEGES ON SCHEMA catalog TO "admin-user";