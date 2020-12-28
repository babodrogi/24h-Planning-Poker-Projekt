package com.bb.abrishw.services;

import com.bb.abrishw.dtos.ScoredIssueBySpecificUserDto;
import com.bb.abrishw.dtos.ScoringDto;
import com.bb.abrishw.model.Issue;
import com.bb.abrishw.model.Score;
import com.bb.abrishw.model.User;
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
    return issueRepository.findAll()
        .stream()
        .filter(issue -> !issue.getVoters().contains(userService.findById(userId)))
        .collect(Collectors.toList());
  }

  public List<ScoredIssueBySpecificUserDto> findAlreadyScoredIssuesForSpecificUser(int userId){
    return issueRepository.findAll()
        .stream()
        .filter(issue -> issue.getVoters().contains(userService.findById(userId)))
        .map(issue -> new ScoredIssueBySpecificUserDto(issue.getTitle(),issue.getDescription(), issue.getScoreValueByUser(userId)))
        .collect(Collectors.toList());
  }

  public Issue findById(int issueId){
    return issueRepository.findById(issueId).orElse(null);
  }

  public void conductScoringProcess(ScoringDto scoringDto){
    Issue issue = findById(scoringDto.getIssueId());
    User user = userService.findById(scoringDto.getUserId());
    Score score = new Score(user,issue,Integer.parseInt(scoringDto.getScoreValue()));

    user.getScoresGiven().add(score);
    user.getIssuesVotedFor().add(issue);
    issue.getVoters().add(user);
    issue.getScores().add(score);

    userService.save(user);
    save(issue);
  }
}
