
package com.spring.bookstore.Repository;

import com.spring.bookstore.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.annotation.Secured;

public interface UserRepository extends CrudRepository<User, Integer> {

  public User findByUsername(String username);

  public User findByEmail(String email);

  //@Secured("ROLE_ADMIN")
  public void deleteByUsername(String username);
}
