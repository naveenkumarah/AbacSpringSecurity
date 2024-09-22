package com.naveen;

import com.naveen.web.model.*;
import com.naveen.web.repositories.IssueRepository;
import com.naveen.web.repositories.ProjectRepository;
import com.naveen.web.repositories.RoleRepository;
import com.naveen.web.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AbacApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private IssueRepository issueRepository;

    public static void main(String[] args) {
        SpringApplication.run(AbacApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Set<Role> roleSet = new HashSet<Role>(){{
            add(new Role(UserRole.ADMIN));
            add(new Role(UserRole.PM));
            add(new Role(UserRole.DEVELOPER));
            add(new Role(UserRole.TESTER));
        }};

        roleRepository.saveAll(roleSet);

        User adminUser=userRepository.save(User.builder().email("naveen@gmail.com")
                .fullName("Naveen")
                .password(passwordEncoder.encode("password"))
                .roles(roleRepository.findAllByName(UserRole.ADMIN)).build());

        User pmUser=userRepository.save(User.builder().email("kumar@gmail.com")
                .fullName("Kumar")
                .password(passwordEncoder.encode("password"))
                .roles(roleRepository.findAllByName(UserRole.PM)).build());


        Project webProject=projectRepository.save(Project.builder().name("Web project").description("Web application Project").users(
                new HashSet<User>(){{
                    add(adminUser);
                    add(pmUser);
                }}
        ).build());

        Issue newIssue=issueRepository.save(Issue.builder().name("Not working").description("Application Not working")
                .assignedTo("naveen@gmail.com").type(IssueType.BUG).status(IssueStatus.ASSIGNED).project(webProject).createdBy("SYSTEM").build());

    }
}