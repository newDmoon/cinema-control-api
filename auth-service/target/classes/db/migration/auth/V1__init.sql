-- расширение для генерации UUID
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE SCHEMA IF NOT EXISTS auth;

CREATE TABLE IF NOT EXISTS auth.users (
                                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                          name VARCHAR(255) NOT NULL UNIQUE,
                                          email VARCHAR(255),
                                          password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS auth.user_roles (
                                               user_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
                                               role VARCHAR(100) NOT NULL
);