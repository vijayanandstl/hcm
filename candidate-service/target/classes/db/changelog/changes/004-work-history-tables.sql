--liquibase formatted sql

--changeset hcm:004
--comment: Create work history tables and related functions

-- Create candidate_work_history table
CREATE TABLE hcm.candidate_work_history (
    work_history_id BIGSERIAL PRIMARY KEY,
    candidate_id UUID NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    job_title VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    description TEXT,
    created_by VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    last_modified_by VARCHAR(255) NOT NULL,
    last_modified_date TIMESTAMP NOT NULL,
    version BIGINT NOT NULL,
    CONSTRAINT fk_work_history_candidate FOREIGN KEY (candidate_id) 
        REFERENCES hcm.candidate(candidate_id) ON DELETE CASCADE
);

-- Create indexes
CREATE INDEX idx_work_history_candidate_id ON hcm.candidate_work_history(candidate_id);
CREATE INDEX idx_work_history_company ON hcm.candidate_work_history(company_name);
CREATE INDEX idx_work_history_job_title ON hcm.candidate_work_history(job_title);

-- Create function to get work history
CREATE OR REPLACE FUNCTION hcm.get_candidate_work_history(p_candidate_id UUID)
RETURNS TABLE (
    work_history_id BIGINT,
    company_name VARCHAR,
    job_title VARCHAR,
    start_date DATE,
    end_date DATE,
    description TEXT
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        cwh.work_history_id,
        cwh.company_name,
        cwh.job_title,
        cwh.start_date,
        cwh.end_date,
        cwh.description
    FROM hcm.candidate_work_history cwh
    WHERE cwh.candidate_id = p_candidate_id
    ORDER BY cwh.start_date DESC;
END;
$$ LANGUAGE plpgsql;

-- Create function to validate work history dates
CREATE OR REPLACE FUNCTION hcm.validate_work_history_dates()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.end_date IS NOT NULL AND NEW.start_date > NEW.end_date THEN
        RAISE EXCEPTION 'End date cannot be before start date';
    END IF;
    
    -- Check for overlapping dates
    IF EXISTS (
        SELECT 1 FROM hcm.candidate_work_history
        WHERE candidate_id = NEW.candidate_id
        AND work_history_id != NEW.work_history_id
        AND (
            (NEW.start_date BETWEEN start_date AND COALESCE(end_date, CURRENT_DATE))
            OR (NEW.end_date BETWEEN start_date AND COALESCE(end_date, CURRENT_DATE))
            OR (start_date BETWEEN NEW.start_date AND COALESCE(NEW.end_date, CURRENT_DATE))
        )
    ) THEN
        RAISE EXCEPTION 'Work history dates cannot overlap with existing records';
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create trigger for date validation
CREATE TRIGGER trg_validate_work_history_dates
    BEFORE INSERT OR UPDATE ON hcm.candidate_work_history
    FOR EACH ROW
    EXECUTE FUNCTION hcm.validate_work_history_dates();

-- Environment-specific data (dev only)
--changeset hcm:004.1
--comment: Add test data for development environment
--context: dev
INSERT INTO hcm.candidate_work_history (
    candidate_id,
    company_name,
    job_title,
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
    'Test Company',
    'Software Engineer',
    '2022-06-01',
    NULL,
    'Test work history record',
    'system',
    CURRENT_TIMESTAMP,
    'system',
    CURRENT_TIMESTAMP,
    1
);

--rollback DROP TRIGGER IF EXISTS trg_validate_work_history_dates ON hcm.candidate_work_history;
--rollback DROP FUNCTION IF EXISTS hcm.validate_work_history_dates();
--rollback DROP FUNCTION IF EXISTS hcm.get_candidate_work_history(UUID);
--rollback DROP TABLE IF EXISTS hcm.candidate_work_history; 