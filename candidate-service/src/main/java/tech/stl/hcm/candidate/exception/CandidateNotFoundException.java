package tech.stl.hcm.candidate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.stl.hcm.common.exception.ResourceNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CandidateNotFoundException extends ResourceNotFoundException {
    public CandidateNotFoundException(String message) {
        super(message);
    }
} 