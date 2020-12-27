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
    User user2 = new User("user2","password2");
    Issue issue1 = new Issue("catfeeding","Feed Dat Cat");
    Score score = new Score(user1,issue1,13);
    Score score2 = new Score(user1,issue1,8);


    userService.save(user1);
    userService.save(user2);
    issueService.save(issue1);


    user1.getScoresGiven().add(score);
    user1.getIssuesVotedFor().add(issue1);
    issue1.getScores().add(score);
    issue1.getScores().add(score2);
    issue1.getVoters().add(user1);

    userService.save(user1);
    issueService.save(issue1);

    Issue issue2 = new Issue("something","Soething Something");
    Score score3 = new Score(user2,issue2,1);
    Score score4 = new Score(user1,issue2,5);
    user2.getIssuesVotedFor().add(issue2);

    issue2.getScores().add(score3);
    issue2.getScores().add(score4);
    issue2.getVoters().add(user2);

    issueService.save(issue2);
    userService.save(user2);

    Issue issue3 = new Issue("YOO","ZO ot adofdsfjk");
    Issue issue4 = new Issue("valami","valami valami");

    issueService.save(issue3);
    issueService.save(issue4);

  }
}
