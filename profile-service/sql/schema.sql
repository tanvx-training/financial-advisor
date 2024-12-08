CREATE TABLE users
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username   VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_at TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100)
);

CREATE TABLE roles
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(500),
    created_at  TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    created_by  VARCHAR(100),
    updated_at  TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    updated_by  VARCHAR(100)
);

CREATE TABLE permissions
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(500),
    created_at  TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    created_by  VARCHAR(100),
    updated_at  TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    updated_by  VARCHAR(100)
);

CREATE TABLE user_roles
(
    user_id    UUID NOT NULL,
    role_id    UUID NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

CREATE TABLE role_permissions
(
    role_id       UUID NOT NULL,
    permission_id UUID NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by    VARCHAR(100),
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by    VARCHAR(100),
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    CONSTRAINT fk_permission FOREIGN KEY (permission_id) REFERENCES permissions (id) ON DELETE CASCADE
);

