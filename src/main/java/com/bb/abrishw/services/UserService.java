package com.bb.abrishw.services;

import com.bb.abrishw.model.User;
import com.bb.abrishw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}

