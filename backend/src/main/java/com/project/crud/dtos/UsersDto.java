package com.project.crud.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class UsersDto {
    private String username;
    private Boolean isAdmin;
}
