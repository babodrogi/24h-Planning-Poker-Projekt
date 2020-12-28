package com.bb.abrishw;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.bb.abrishw.model.Issue;
import com.bb.abrishw.model.Score;
import com.bb.abrishw.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UnitTests {



  @Test
  public void CalculateAverageReturnsCorrectValue(){
    Issue issue1 = new Issue("catfeeding","Feed Dat Cat");
    Score score = new Score(issue1,6);
    Score score2 = new Score(issue1,2);
    issue1.getScores().add(score);
    issue1.getScores().add(score2);
    assertEquals(4,issue1.calculateAverageScore());

  }

  @Test
  public void ReturnVoterNamesReturnsCorrectString(){
    Issue issue1 = new Issue("catfeeding","Feed Dat Cat");
    User user = new User("usher","pass");
    User user2 = new User("ush","pass");
    User user3 = new User("user","pass");
    issue1.getVoters().add(user);
    issue1.getVoters().add(user2);
    issue1.getVoters().add(user3);

    assertEquals("usher,ush,user",issue1.getVoterNamesAsString());
  }
}
