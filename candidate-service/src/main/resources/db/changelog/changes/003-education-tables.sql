--liquibase formatted sql

--changeset hcm:003
--comment: Create education tables and related functions

-- Create candidate_education table
CREATE TABLE hcm.candidate_education (
    education_id BIGSERIAL PRIMARY KEY,
    candidate_id UUID NOT NULL,
    institution_name VARCHAR(255) NOT NULL,
    degree VARCHAR(255) NOT NULL,
    field_of_study VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    description TEXT,
    created_by VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    last_modified_by VARCHAR(255) NOT NULL,
    last_modified_date TIMESTAMP NOT NULL,
    version BIGINT NOT NULL,
    CONSTRAINT fk_education_candidate FOREIGN KEY (candidate_id) 
        REFERENCES hcm.candidate(candidate_id) ON DELETE CASCADE
);

-- Create indexes
CREATE INDEX idx_education_candidate_id ON hcm.candidate_education(candidate_id);
CREATE INDEX idx_education_institution ON hcm.candidate_education(institution_name);
CREATE INDEX idx_education_degree ON hcm.candidate_education(degree);

-- Create function to get education history
CREATE OR REPLACE FUNCTION hcm.get_candidate_education(p_candidate_id UUID)
RETURNS TABLE (
    education_id BIGINT,
    institution_name VARCHAR,
    degree VARCHAR,
    field_of_study VARCHAR,
    start_date DATE,
    end_date DATE,
    description TEXT
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        ce.education_id,
        ce.institution_name,
        ce.degree,
        ce.field_of_study,
        ce.start_date,
        ce.end_date,
        ce.description
    FROM hcm.candidate_education ce
    WHERE ce.candidate_id = p_candidate_id
    ORDER BY ce.start_date DESC;
END;
$$ LANGUAGE plpgsql;

-- Create function to validate education dates
CREATE OR REPLACE FUNCTION hcm.validate_education_dates()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.end_date IS NOT NULL AND NEW.start_date > NEW.end_date THEN
        RAISE EXCEPTION 'End date cannot be before start date';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create trigger for date validation
CREATE TRIGGER trg_validate_education_dates
    BEFORE INSERT OR UPDATE ON hcm.candidate_education
    FOR EACH ROW
    EXECUTE FUNCTION hcm.validate_education_dates();

-- Environment-specific data (dev only)
--changeset hcm:003.1
--comment: Add test data for development environment
--context: dev
INSERT INTO hcm.candidate_education (
    candidate_id,
    institution_name,
    degree,
    field_of_study,
    start_date,
    end_date,
    description,
    created_by,
    created_date,
    last_modified_by,
    last_modified_date,
    version
) VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Test University',
    'Bachelor of Science',
    'Computer Science',
    '2018-09-01',
    '2022-05-15',
    'Test education record',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP,
    1
);

--rollback DROP TRIGGER IF EXISTS trg_validate_education_dates ON hcm.candidate_education;
--rollback DROP FUNCTION IF EXISTS hcm.validate_education_dates();
--rollback DROP FUNCTION IF EXISTS hcm.get_candidate_education(UUID);
--rollback DROP TABLE IF EXISTS hcm.candidate_education; 