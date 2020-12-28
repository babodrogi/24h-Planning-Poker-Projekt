package com.bb.abrishw.repositories;

import com.bb.abrishw.dtos.ScoredIssueBySpecificUserDto;
import com.bb.abrishw.model.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue,Integer> {
  List<Issue> findAll();

  @Query(value = "select title,description,value from issue join score on issue.id = score.issue_id where user_id =?1", nativeQuery = true)
  List<ScoredIssueBySpecificUserDto> justFindIt(int userId);
}
