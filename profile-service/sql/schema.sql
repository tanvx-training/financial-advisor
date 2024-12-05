-- AbstractEntity fields are shared across all tables

CREATE TABLE account
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at TIMESTAMP,
    updated_by VARCHAR(255)
);

CREATE TABLE role
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at  TIMESTAMP    NOT NULL,
    created_by  VARCHAR(255) NOT NULL,
    updated_at  TIMESTAMP,
    updated_by  VARCHAR(255)
);

CREATE TABLE permission
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at  TIMESTAMP    NOT NULL,
    created_by  VARCHAR(255) NOT NULL,
    updated_at  TIMESTAMP,
    updated_by  VARCHAR(255)
);

CREATE TABLE account_role
(
    account_id UUID         NOT NULL,
    role_id    UUID         NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at TIMESTAMP,
    updated_by VARCHAR(255),
    PRIMARY KEY (account_id, role_id),
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);

CREATE TABLE role_permission
(
    role_id       UUID         NOT NULL,
    permission_id UUID         NOT NULL,
    created_at    TIMESTAMP    NOT NULL,
    created_by    VARCHAR(255) NOT NULL,
    updated_at    TIMESTAMP,
    updated_by    VARCHAR(255),
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE,
    CONSTRAINT fk_permission FOREIGN KEY (permission_id) REFERENCES permission (id) ON DELETE CASCADE
);
