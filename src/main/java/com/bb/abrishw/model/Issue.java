package com.bb.abrishw.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
}
