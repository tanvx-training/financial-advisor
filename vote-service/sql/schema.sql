-- Table: categories
CREATE TABLE categories
(
    id          UUID               DEFAULT gen_random_uuid() PRIMARY KEY,
    name        VARCHAR   NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  VARCHAR   NOT NULL,
    updated_at  TIMESTAMP,
    updated_by  VARCHAR
);

-- Table: budgets
CREATE TABLE budgets
(
    id          UUID               DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id     UUID,
    category_id UUID      NOT NULL,
    amount      NUMERIC(19, 2),
    month       INT,
    year        INT,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  VARCHAR   NOT NULL,
    updated_at  TIMESTAMP,
    updated_by  VARCHAR,
    CONSTRAINT fk_budget_category FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

-- Table: transactions
CREATE TABLE transactions
(
    id               UUID               DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id          UUID      NOT NULL,
    category_id      UUID      NOT NULL,
    amount           NUMERIC(19, 2),
    note             TEXT,
    transaction_date TIMESTAMP,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by       VARCHAR   NOT NULL,
    updated_at       TIMESTAMP,
    updated_by       VARCHAR,
    CONSTRAINT fk_transaction_category FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE SET NULL
);
