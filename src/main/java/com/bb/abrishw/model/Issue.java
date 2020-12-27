package com.bb.abrishw.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private String Descrption;
  @ManyToMany(mappedBy = "issuesVotedFor")
  private List<User> voters;
  @OneToMany(mappedBy = "issue")
  private List<Score> scores;

  public Issue(String title, String descrption, List<User> voters, List<Score> scores) {
    this.title = title;
    Descrption = descrption;
    this.voters = voters;
    this.scores = scores;
  }
}
