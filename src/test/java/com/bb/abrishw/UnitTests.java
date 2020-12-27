package com.bb.abrishw;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.bb.abrishw.model.Issue;
import com.bb.abrishw.model.Score;
import org.junit.jupiter.api.Test;


public class UnitTests {

  @Test
  public void assertCalculateAverage(){
    Issue issue1 = new Issue("catfeeding","Feed Dat Cat");
    Score score = new Score(issue1,6);
    Score score2 = new Score(issue1,2);
    issue1.getScores().add(score);
    issue1.getScores().add(score2);
    assertEquals(4,issue1.calculateAverageScore());

  }
}
