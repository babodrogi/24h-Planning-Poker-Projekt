package com.bb.abrishw.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoredIssueBySpecificUserDto {
  private String title;
  private String description;
  private int scoredValue;

  public ScoredIssueBySpecificUserDto(String title, String description, int scoredValue) {
    this.title = title;
    this.description = description;
    this.scoredValue = scoredValue;
  }
}
