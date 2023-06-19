
package com.spring.bookstore.Exceptions;

import java.time.LocalDateTime;
import java.util.List;


public class ErrorResponse {

private String error, status, timestamp;

public ErrorResponse(String error, String status) {
    this.error = error;
    this.status = status;
    this.timestamp = LocalDateTime.now().toString();
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

public String getTimestamp() {
    return timestamp;
}

public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
}

public String getError() {
    return error;
}

public void setError(String error) {
    this.error = error;
}

}
