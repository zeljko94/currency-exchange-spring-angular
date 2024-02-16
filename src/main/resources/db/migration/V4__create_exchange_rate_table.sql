CREATE TABLE exchange_rates (
  id SERIAL PRIMARY KEY,
  source_currency_id BIGINT REFERENCES Currencies(id),
  target_currency_id BIGINT REFERENCES Currencies(id),
  rate DOUBLE PRECISION,
  inserted_at TIMESTAMP
);
