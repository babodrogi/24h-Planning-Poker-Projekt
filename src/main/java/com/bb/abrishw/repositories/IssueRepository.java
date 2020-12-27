package com.bb.abrishw.repositories;

import com.bb.abrishw.model.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue,Integer> {
  List<Issue> findAll();

  @Query(value = "select * from issue join issue_voter on issue.id = issue_voter.issue_id where user_id != ?1",nativeQuery = true)
  List<Issue> findScorableIssuesForSpecificUser(int userId);

}
