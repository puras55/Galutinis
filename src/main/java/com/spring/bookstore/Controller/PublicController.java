
package com.spring.bookstore.Controller;

import com.spring.bookstore.Exceptions.EmailExistsException;
import com.spring.bookstore.Exceptions.UsernameExistsException;
import com.spring.bookstore.JWT.JwtUtil;
import com.spring.bookstore.Model.Authority;
import com.spring.bookstore.Model.AuthorityType;
import com.spring.bookstore.Model.Book;
import com.spring.bookstore.Model.Cart;
import com.spring.bookstore.Model.Order;
import com.spring.bookstore.Model.User;
import com.spring.bookstore.Service.UserService;
import com.spring.bookstore.UserAuthenticationService.UserDetailsImpl;
import com.spring.bookstore.templates.AuthenticationRequest;
import com.spring.bookstore.templates.AuthenticationResponse;
import com.spring.bookstore.templates.RegistrationRequest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public")
public class PublicController {

  @Autowired
  UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;







  @PostMapping(value = "/register")
  public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {

    if (userService.usernameExists(registrationRequest.getUsername())) {
      throw new UsernameExistsException("Username exists");
    } else if (userService.emailExists(registrationRequest.getEmail())) {
      throw new EmailExistsException("Email exists!");
    }

    String encodedPassword = encoder.encode(registrationRequest.getPassword());
    User user = new User(registrationRequest.getUsername(), encodedPassword, registrationRequest.getName(), registrationRequest.getEmail());
    Cart c = new Cart();
    try {
      user.addCart(c);
      userService.saveUser(user);
    } catch (Exception e) {
      return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>("Created", HttpStatus.CREATED);
  }



  @GetMapping("/testMore")
  public String createUserWithId1() {
    User u = new User("username1", "password1", "name1", "mn@ot.com");
    userService.saveUser(u);
    return "Saved";
  }


  @GetMapping("/fetchOrders")
  public Set<Order> fetchAllOrders() {
//    Set<Order> orders = userService.findUserById(1).getOrders();
//    return orders;
    return null;
  }


  @GetMapping("/deleteFirstUser")
  public String deleteUser() {
    userService.deleteUser("user");
    return "deleted";
  }


  @RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
    try {
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
      authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("Invalid details");
    }
    UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(request.getUsername());
    String name = userDetails.getUser().getName();
    String email = userDetails.getUser().getEmail();


    Cookie cookie = new Cookie("jwt",generatedtoken);
    cookie.setMaxAge(60 * 60 * 10);
    cookie.setSecure(false);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    response.addCookie(cookie);

    HttpHeaders headers = new HttpHeaders();
    headers.add("name", name);
    headers.add("email", email);

    return new ResponseEntity<>(new AuthenticationResponse(generatedToken), headers, HttpStatus.OK);
  }


  @RequestMapping(value = "/testUser")
  public String test() {
    User user = new User();
    double num = Math.floor(Math.random() * 10000);
    user.setUsername("user" + String.valueOf((int) (num)));
    user.setPassword(new BCryptPasswordEncoder().encode("test"));
    userService.saveUser(user);
    return "Saved test user!";
  }


  @RequestMapping(value = "/testAdmin")
  public String testDefault() {
    User user = new User();
    double num = Math.floor(Math.random() * 10000);
    user.setUsername("admin" + String.valueOf((int) (num)));
    user.setPassword(new BCryptPasswordEncoder().encode("test"));
    Set<Authority> authorities = new HashSet<>();
    Authority authority = new Authority(AuthorityType.ROLE_ADMIN);
    authorities.add(authority);
    //  user.setAuthorities(authorities);
    userService.saveUser(user);
    return "Saved test user!";
  }


  @GetMapping(value = "/welcome")
  public String welcomeString() {
    return "Welcome! This is working";
  }
}
