
package com.spring.bookstore.templates;

import java.io.Serializable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;


public class AuthenticationRequest implements Serializable {

private String username, password;

public AuthenticationRequest() {
}


public AuthenticationRequest(String username, String password) {
    this.username = username;
    this.password = password;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

}
