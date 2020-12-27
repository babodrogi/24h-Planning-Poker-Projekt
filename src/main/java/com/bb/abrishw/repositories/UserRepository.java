package com.bb.abrishw.repositories;

import com.bb.abrishw.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer>{
  List<User> findAll();
}
