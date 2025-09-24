package com.project.crud.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Users {
    @Id
    private String username;
    private String password;
    private Boolean isAdmin;
}
