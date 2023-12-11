-- Create database
CREATE DATABASE bamboo;

-- Create user and grant permissions
CREATE USER postgres WITH PASSWORD 'admin';
ALTER ROLE postgres SET client_encoding TO 'utf8';
ALTER ROLE postgres SET default_transaction_isolation TO 'read committed';
ALTER ROLE postgres SET timezone TO 'UTC';
GRANT ALL PRIVILEGES ON DATABASE bamboo TO postgres;
