package org.example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectDataVacationException extends RuntimeException {
    public IncorrectDataVacationException(String message) {
        super(message);
    }
}

