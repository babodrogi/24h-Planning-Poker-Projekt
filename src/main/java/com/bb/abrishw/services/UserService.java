package com.bb.abrishw.services;

import com.bb.abrishw.model.Issue;
import com.bb.abrishw.model.User;
import com.bb.abrishw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

