package com.naveen.web.repositories;

import com.naveen.web.model.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Integer> {
    Set<Issue> findAllByProjectId(Integer projectId);

}
