package com.bb.abrishw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Score {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne
  private User user;
  @ManyToOne
  private Issue issue;
  private int value;

  public Score(User user, Issue issue, int value) {
    this.user = user;
    this.issue = issue;
    this.value = value;
  }

  public Score(Issue issue, int value) {
    this.issue = issue;
    this.value = value;
  }
}
