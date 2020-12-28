package com.bb.abrishw.repositories;

import com.bb.abrishw.model.Score;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository  extends CrudRepository <Score,Integer> {

  Score findByIssueIdAndUserId(int issueId,int userId);
}
