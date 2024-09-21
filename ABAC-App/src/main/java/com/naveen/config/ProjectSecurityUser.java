package com.naveen.config;

import java.util.ArrayList;

import com.naveen.web.model.Project;
import com.naveen.web.model.ProjectUser;
import com.naveen.web.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class ProjectSecurityUser extends User implements ProjectUser {
	private static final long serialVersionUID = 8498233196842987555L;
	
	private Project project;
	private UserRole role;

	public ProjectSecurityUser(String username, String password, UserRole role) {
		super(username, password, new ArrayList<GrantedAuthority>(0));
		this.role = role;
	}
	
	public ProjectSecurityUser(String username, String password, Project project, UserRole role) {
		super(username, password, new ArrayList<GrantedAuthority>(0));
		this.role = role;
		this.project = project;
	}

	@Override
	public Project getProject() {
		return project;
	}

	@Override
	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public UserRole getRole() {
		return this.role;
	}

	@Override
	public void setRole(UserRole role) {
		this.role = role;
		
	}

	@Override
	public String getName() {
		return super.getUsername();
	}

	@Override
	public String toString() {
		return "{username:" + getUsername() + ", password: [PROTECTED], enabled:" + isEnabled()
				+ ", accountNonExpired:" + isAccountNonExpired() + ", accountNonLocked:" + isAccountNonLocked()
				+ ", credentialsNonExpired:" + isCredentialsNonExpired() + ", project:" + project + ", role:" + role
				+ "}";
	}
	
	

}
