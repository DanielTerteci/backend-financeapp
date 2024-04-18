CREATE TABLE IF NOT EXISTS income (
    id SERIAL PRIMARY KEY,
    is_recurrent BOOLEAN DEFAULT false,
    currency VARCHAR(15) NOT NULL,
    price NUMERIC(10, 3),
    category_id INT,
    income_date DATE,
    user_id BIGINT,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (user_id) REFERENCES users_table(id)
);