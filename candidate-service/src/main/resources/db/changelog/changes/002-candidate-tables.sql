--liquibase formatted sql

--changeset hcm:002
--comment: Create candidate table

-- Create candidate table
CREATE TABLE hcm.candidate (
    candidate_id UUID PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address VARCHAR(500),
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    postal_code VARCHAR(20),
    created_by VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    last_modified_by VARCHAR(255) NOT NULL,
    last_modified_date TIMESTAMP NOT NULL,
    version BIGINT NOT NULL
);

-- Create indexes
CREATE INDEX idx_candidate_email ON hcm.candidate(email);
CREATE INDEX idx_candidate_last_name ON hcm.candidate(last_name);
CREATE INDEX idx_candidate_created_date ON hcm.candidate(created_date);

--rollback DROP TABLE IF EXISTS hcm.candidate; 