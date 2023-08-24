package com.warlocktony.webstream.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "In name must be only letter!!!")
public class IllegalNameException extends IllegalArgumentException {
}
