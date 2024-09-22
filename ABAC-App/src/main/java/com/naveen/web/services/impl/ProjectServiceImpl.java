package com.naveen.web.services.impl;

import com.naveen.web.model.Project;
import com.naveen.web.repositories.ProjectRepository;
import com.naveen.web.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getProjects() {
        return  StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Project getProject(Integer id) {
        Optional<Project> projectOpt=projectRepository.findById(id);
        return projectOpt.get();
    }

    @Override
    public void createProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void updateProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.deleteById(project.getId());
    }
}
