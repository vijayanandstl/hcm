--liquibase formatted sql

--changeset hcm:001
--comment: Create initial schema and audit columns table

-- Create schema if not exists
CREATE SCHEMA IF NOT EXISTS hcm;

-- Create audit columns table
CREATE TABLE hcm.audit_columns (
    id BIGSERIAL PRIMARY KEY,
    created_by VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    last_modified_by VARCHAR(255) NOT NULL,
    last_modified_date TIMESTAMP NOT NULL,
    version BIGINT NOT NULL
);

-- Create indexes
CREATE INDEX idx_audit_columns_created_date ON hcm.audit_columns(created_date);
CREATE INDEX idx_audit_columns_last_modified_date ON hcm.audit_columns(last_modified_date);

--rollback DROP TABLE IF EXISTS hcm.audit_columns;
--rollback DROP SCHEMA IF EXISTS hcm; 