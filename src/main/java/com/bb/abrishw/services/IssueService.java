package com.bb.abrishw.services;

import com.bb.abrishw.model.Issue;
import com.bb.abrishw.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {

  private IssueRepository issueRepository;
  private UserService userService;

  @Autowired
  public IssueService(IssueRepository issueRepository, UserService userService) {
    this.issueRepository = issueRepository;
    this.userService = userService;
  }




  public void save(Issue issue){
    issueRepository.save(issue);
  }

  public List<Issue> findAll(){
   return issueRepository.findAll();
  };

  public List<Issue> findScorableIssuesForSpecificUser(int userId){
    return issueRepository.findAll().stream().filter(issue -> !issue.getVoters().contains(userService.findById(userId))).collect(Collectors.toList());
  }

  public Issue findById(int issueId){
    return issueRepository.findById(issueId).orElse(null);
  }

}
