package com.bb.abrishw;

import com.bb.abrishw.model.Issue;
import com.bb.abrishw.model.Score;
import com.bb.abrishw.model.User;
import com.bb.abrishw.services.IssueService;
import com.bb.abrishw.services.ScoreService;
import com.bb.abrishw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AbrisHwApplication  implements CommandLineRunner {

  private UserService userService;
  private IssueService issueService;
  private ScoreService scoreService;

  @Autowired
  public AbrisHwApplication(UserService userService, IssueService issueService, ScoreService scoreService) {
    this.userService = userService;
    this.issueService = issueService;
    this.scoreService = scoreService;
  }

  public static void main(String[] args)  {
    SpringApplication.run(AbrisHwApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    User user1 = new User("user1","password1");
    Issue issue1 = new Issue("catfeeding","Feed Dat Cat");
    Score score = new Score(user1,issue1,13);

    userService.save(user1);
    issueService.save(issue1);


    user1.getScoresGiven().add(score);
    user1.getIssuesVotedFor().add(issue1);
    issue1.getScores().add(score);
    issue1.getVoters().add(user1);

    userService.save(user1);
    issueService.save(issue1);

  }
}
