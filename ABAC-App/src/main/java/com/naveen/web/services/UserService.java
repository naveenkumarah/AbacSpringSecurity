package com.naveen.web.services;

import java.util.List;

import com.naveen.web.model.ProjectUser;
import com.naveen.web.model.User;

public interface UserService {
	ProjectUser findUserByName(String name);
	List<ProjectUser> findUserByProject(Integer projectId);
}
