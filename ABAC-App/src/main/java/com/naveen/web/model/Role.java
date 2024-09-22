package com.naveen.web.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data
@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private UserRole name;
    public Role(UserRole name){
        this.name=name;
    }
}
