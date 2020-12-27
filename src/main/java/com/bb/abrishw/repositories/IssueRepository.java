package com.bb.abrishw.repositories;

import com.bb.abrishw.model.Issue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue,Integer> {
  List<Issue> findAll();
}
