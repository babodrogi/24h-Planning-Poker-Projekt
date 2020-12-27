package com.bb.abrishw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String username;
  private String password;
  @ManyToMany
  @JoinTable(name = "issue_voter", joinColumns = {
      @JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {
          @JoinColumn(name = "issue_id", referencedColumnName = "id")})
  private List<Issue> issuesVotedFor;
  @OneToMany(mappedBy = "user")
  private List<Score> scoresGiven;

  public User(String username, String password){
    issuesVotedFor = new ArrayList<>();
    scoresGiven = new ArrayList<>();
    this.username = username;
    this.password = password;
  }
}
