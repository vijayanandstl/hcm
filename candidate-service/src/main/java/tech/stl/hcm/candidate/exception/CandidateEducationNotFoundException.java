package tech.stl.hcm.candidate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CandidateEducationNotFoundException extends RuntimeException {
    public CandidateEducationNotFoundException(String message) {
        super(message);
    }
} 