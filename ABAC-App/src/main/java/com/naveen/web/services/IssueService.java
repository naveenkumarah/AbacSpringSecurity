package com.naveen.web.services;

import java.util.List;

import com.naveen.web.model.Issue;
import com.naveen.web.model.Project;

public interface IssueService {
	public List<Issue> getIssues(Project project);
	public Issue getIssue(Integer id);
	public void createIssue(Issue issue);
	public void updateIssue(Issue issue);
	public void deleteIssue(Issue issue);
}
