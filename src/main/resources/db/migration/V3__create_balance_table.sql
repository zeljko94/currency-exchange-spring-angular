CREATE TABLE balances (
     id SERIAL PRIMARY KEY,
     name VARCHAR(255),
     user_id BIGINT REFERENCES Users(id),
     currency_id BIGINT REFERENCES Currencies(id),
     amount DOUBLE PRECISION
);
