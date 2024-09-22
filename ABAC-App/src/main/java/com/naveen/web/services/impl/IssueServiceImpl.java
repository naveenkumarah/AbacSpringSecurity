package com.naveen.web.services.impl;

import com.naveen.web.model.Issue;
import com.naveen.web.model.Project;
import com.naveen.web.repositories.IssueRepository;
import com.naveen.web.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;
    @Override
    public List<Issue> getIssues(Integer projectId) {
        return new ArrayList<>(issueRepository.findAllByProjectId(projectId));
    }

    @Override
    public Issue getIssue(Integer id) {
        Optional<Issue> issueOpt=issueRepository.findById(id);
        return issueOpt.get();
    }

    @Override
    public void createIssue(Issue issue) {
        issueRepository.save(issue);
    }

    @Override
    public void updateIssue(Issue issue) {
        issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Integer issueId) {
        issueRepository.deleteById(issueId);
    }
}
