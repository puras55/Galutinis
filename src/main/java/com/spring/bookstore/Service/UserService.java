
package com.spring.bookstore.Service;

import com.spring.bookstore.Exceptions.NoSuchUsernameException;
import com.spring.bookstore.Model.User;
import com.spring.bookstore.Repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {

  static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  static java.security.SecureRandom rnd = new java.security.SecureRandom();

  public static String randomString(int len) {
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(AB.charAt(rnd.nextInt(AB.length())));
    }
    return sb.toString();
  }

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserDetailsService userDetailsService;


  public User saveUser(User u) {

    User savedUser = userRepository.save(u);
    return savedUser;
//SimpleJpaRepository
  }

  public User findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }


  public boolean usernameExists(String username) {
    User user = userRepository.findByUsername(username);
    return user != null;
  }

  public boolean emailExists(String email) {
    User user = userRepository.findByEmail(email);
    return user != null;
  }

  //@Secured("ROLE_ADMIN")
  public String deleteUser(String username) {
    boolean usernameExists = usernameExists(username);
    if (!usernameExists) {
      throw new NoSuchUsernameException("Username " + username + " not found");
    }
    userRepository.deleteByUsername(username);
    return "Deleted";
  }

}
