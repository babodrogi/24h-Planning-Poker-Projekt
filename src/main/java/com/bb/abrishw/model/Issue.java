package com.bb.abrishw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private String description;
  @ManyToMany(mappedBy = "issuesVotedFor")
  private List<User> voters;
  @OneToMany(mappedBy = "issue",cascade = CascadeType.ALL)
  private List<Score> scores;

  public Issue(String title, String description) {
    voters = new ArrayList<>();
    scores = new ArrayList<>();
    this.title = title;
    this.description = description;
  }

  public double calculateAverageScore(){
    double avg;
    int sum;
    sum = scores.stream()
        .mapToInt(score -> score.getValue())
        .sum();
    avg = (double) sum/scores.size();
    return avg;
  }

  public int getScoreValueByUser(int userId){
    return scores.stream()
        .filter(score -> score.getUser().getId()== userId)
        .mapToInt(score -> score.getValue())
        .findFirst()
        .getAsInt();
  }

  public String getVoterNamesAsString(){
    List<String> names;
    names = voters.stream().map(voter -> voter.getUsername()).collect(Collectors.toList());
    return String.join(",",names);
  }
}
