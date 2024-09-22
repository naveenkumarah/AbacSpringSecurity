package com.naveen.web.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
			name = "project_users",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<User> users = new HashSet<>();
	
}
