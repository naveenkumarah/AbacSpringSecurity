package com.naveen.web.services.impl;

import com.naveen.config.ProjectSecurityUser;
import com.naveen.web.model.Project;
import com.naveen.web.model.ProjectUser;
import com.naveen.web.model.User;
import com.naveen.web.repositories.UserRepository;
import com.naveen.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    public void createUser(ProjectSecurityUser user) {
        /*Assert.isTrue(!userExists(user.getUsername()),"");

        users.put(user.getUsername().toLowerCase(), user);*/
    }

    public boolean userExists(String username) {
        Optional<User> userOptional=userRepository.findByEmail(username);
        return userOptional.isPresent();
    }
    @Override
    public ProjectUser findUserByName(String name) {
        Optional<User> userOptional=userRepository.findByEmail(name.toLowerCase());
        ProjectSecurityUser projectUser=null;
        if(userOptional.isPresent()){
            projectUser=new ProjectSecurityUser(name,null,null);
        }
        return projectUser;
    }

    @Override
    public List<ProjectUser> findUserByProject(Integer projectId) {
        if(projectId == null)
            return null;
        List<ProjectUser> result = new LinkedList<>();
        /*for(ProjectSecurityUser user : users.values()) {
            Project project = user.getProject();
            if(project != null && projectId.equals(project.getId())) {
                result.add(user);
            }
        }*/
        return result;
    }
}
