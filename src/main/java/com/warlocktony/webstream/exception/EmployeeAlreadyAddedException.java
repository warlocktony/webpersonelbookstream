package com.warlocktony.webstream.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Staff already added!!!")
public class EmployeeAlreadyAddedException extends RuntimeException{

    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }
}
