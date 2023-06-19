
package com.spring.bookstore.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameExistsException extends RuntimeException {

String error_message;

public String getError_message() {
    return error_message;
}

public void setError_message(String error_message) {
    this.error_message = error_message;
}

public UsernameExistsException() {
}

public UsernameExistsException(String message) {
    super(message);
}

}
