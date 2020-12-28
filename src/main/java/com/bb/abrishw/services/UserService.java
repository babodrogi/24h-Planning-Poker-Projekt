package com.bb.abrishw.services;

import com.bb.abrishw.model.User;
import com.bb.abrishw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Service
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void save(User user){
    userRepository.save(user);
  }

  public List<User> findAll(){
    return userRepository.findAll();
  }

  public User findByUsername(String username){
    return userRepository.findByUsername(username);

  }

  public User findById(int userId){
    return userRepository.findById(userId).orElse(null);
  }

  public void setCookieForUser(String token,HttpServletResponse response){
    Cookie cookie = new Cookie("authToken",token);
    cookie.setHttpOnly(true);
    cookie.setMaxAge(60 * 60);
    cookie.setPath("/");
    response.addCookie(cookie);
  }

  public void logoutUser(HttpServletResponse response){
    Cookie cookie = new Cookie("authToken", null);
    cookie.setMaxAge(0);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    response.addCookie(cookie);
  }

}

