package com.bb.abrishw.services;

import com.bb.abrishw.model.Score;
import com.bb.abrishw.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
  private ScoreRepository scoreRepository;

  @Autowired
  public ScoreService(ScoreRepository scoreRepository) {
    this.scoreRepository = scoreRepository;
  }

  public void save(Score score){
    scoreRepository.save(score);
  }
}
