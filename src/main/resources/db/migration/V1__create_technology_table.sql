CREATE SCHEMA IF NOT EXISTS ms_technology;

CREATE TABLE IF NOT EXISTS ms_technology.technology
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(90) NOT NULL
);