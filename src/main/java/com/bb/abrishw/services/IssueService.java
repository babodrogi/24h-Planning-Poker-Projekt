package com.bb.abrishw.services;

import com.bb.abrishw.model.Issue;
import com.bb.abrishw.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {

  private IssueRepository issueRepository;

  @Autowired
  public IssueService(IssueRepository issueRepository) {
    this.issueRepository = issueRepository;
  }

  public void save(Issue issue){
    issueRepository.save(issue);
  }
}
