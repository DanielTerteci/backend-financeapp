CREATE TABLE IF NOT EXISTS users_table (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    created_on TIMESTAMP,
    updated_on TIMESTAMP
);