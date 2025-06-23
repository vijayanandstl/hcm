--liquibase formatted sql

--changeset hcm:005
--comment: Create certification tables and related functions

-- Create candidate_certification table
CREATE TABLE hcm.candidate_certification (
    certification_id BIGSERIAL PRIMARY KEY,
    candidate_id UUID NOT NULL,
    certification_name VARCHAR(255) NOT NULL,
    issuing_organization VARCHAR(255) NOT NULL,
    issue_date DATE NOT NULL,
    expiry_date DATE,
    credential_id VARCHAR(255),
    credential_url VARCHAR(512),
    description TEXT,
    created_by VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    last_modified_by VARCHAR(255) NOT NULL,
    last_modified_date TIMESTAMP NOT NULL,
    version BIGINT NOT NULL,
    CONSTRAINT fk_certification_candidate FOREIGN KEY (candidate_id) 
        REFERENCES hcm.candidate(candidate_id) ON DELETE CASCADE
);

-- Create indexes
CREATE INDEX idx_certification_candidate_id ON hcm.candidate_certification(candidate_id);
CREATE INDEX idx_certification_name ON hcm.candidate_certification(certification_name);
CREATE INDEX idx_certification_org ON hcm.candidate_certification(issuing_organization);
CREATE INDEX idx_certification_expiry ON hcm.candidate_certification(expiry_date);

-- Create function to get certifications
CREATE OR REPLACE FUNCTION hcm.get_candidate_certifications(p_candidate_id UUID)
RETURNS TABLE (
    certification_id BIGINT,
    certification_name VARCHAR,
    issuing_organization VARCHAR,
    issue_date DATE,
    expiry_date DATE,
    credential_id VARCHAR,
    credential_url VARCHAR,
    description TEXT,
    days_until_expiry INTEGER
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        cc.certification_id,
        cc.certification_name,
        cc.issuing_organization,
        cc.issue_date,
        cc.expiry_date,
        cc.credential_id,
        cc.credential_url,
        cc.description,
        CASE 
            WHEN cc.expiry_date IS NOT NULL THEN 
                (cc.expiry_date - CURRENT_DATE)
            ELSE NULL
        END as days_until_expiry
    FROM hcm.candidate_certification cc
    WHERE cc.candidate_id = p_candidate_id
    ORDER BY 
        CASE 
            WHEN cc.expiry_date IS NULL THEN 1
            WHEN cc.expiry_date < CURRENT_DATE THEN 2
            ELSE 3
        END,
        cc.expiry_date ASC;
END;
$$ LANGUAGE plpgsql;

-- Create function to get expiring certifications
CREATE OR REPLACE FUNCTION hcm.get_expiring_certifications(p_days_threshold INTEGER DEFAULT 30)
RETURNS TABLE (
    candidate_id UUID,
    certification_id BIGINT,
    certification_name VARCHAR,
    issuing_organization VARCHAR,
    expiry_date DATE,
    days_until_expiry INTEGER
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        cc.candidate_id,
        cc.certification_id,
        cc.certification_name,
        cc.issuing_organization,
        cc.expiry_date,
        (cc.expiry_date - CURRENT_DATE) as days_until_expiry
    FROM hcm.candidate_certification cc
    WHERE cc.expiry_date IS NOT NULL
    AND cc.expiry_date > CURRENT_DATE
    AND (cc.expiry_date - CURRENT_DATE) <= p_days_threshold
    ORDER BY cc.expiry_date ASC;
END;
$$ LANGUAGE plpgsql;

-- Create function to validate certification dates
CREATE OR REPLACE FUNCTION hcm.validate_certification_dates()
RETURNS TRIGGER AS $$
BEGIN
    -- Validate issue date is not in the future
    IF NEW.issue_date > CURRENT_DATE THEN
        RAISE EXCEPTION 'Issue date cannot be in the future';
    END IF;
    
    -- Validate expiry date is after issue date
    IF NEW.expiry_date IS NOT NULL AND NEW.issue_date >= NEW.expiry_date THEN
        RAISE EXCEPTION 'Expiry date must be after issue date';
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create trigger for date validation
CREATE TRIGGER trg_validate_certification_dates
    BEFORE INSERT OR UPDATE ON hcm.candidate_certification
    FOR EACH ROW
    EXECUTE FUNCTION hcm.validate_certification_dates();

-- Environment-specific data (dev only)
--changeset hcm:005.1
--comment: Add test data for development environment
--context: dev
INSERT INTO hcm.candidate_certification (
    candidate_id,
    certification_name,
    issuing_organization,
    issue_date,
    expiry_date,
    credential_id,
    credential_url,
    description,
    created_by,
    created_date,
    last_modified_by,
    last_modified_date,
    version
) VALUES 
(
    '00000000-0000-0000-0000-000000000001',
    'AWS Certified Solutions Architect',
    'Amazon Web Services',
    '2023-01-15',
    '2026-01-15',
    'AWS-123456',
    'https://aws.amazon.com/certification/',
    'Test certification record',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP,
    1
),
(
    '00000000-0000-0000-0000-000000000001',
    'Oracle Certified Professional',
    'Oracle',
    '2022-06-01',
    NULL,
    'OCP-789012',
    'https://education.oracle.com/',
    'Test certification without expiry',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP,
    1
);

--rollback DROP TRIGGER IF EXISTS trg_validate_certification_dates ON hcm.candidate_certification;
--rollback DROP FUNCTION IF EXISTS hcm.validate_certification_dates();
--rollback DROP FUNCTION IF EXISTS hcm.get_expiring_certifications(INTEGER);
--rollback DROP FUNCTION IF EXISTS hcm.get_candidate_certifications(UUID);
--rollback DROP TABLE IF EXISTS hcm.candidate_certification; 