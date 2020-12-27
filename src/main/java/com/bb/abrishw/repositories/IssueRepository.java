package com.bb.abrishw.repositories;

import com.bb.abrishw.model.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueRepository extends CrudRepository<Issue,Integer> {
}
