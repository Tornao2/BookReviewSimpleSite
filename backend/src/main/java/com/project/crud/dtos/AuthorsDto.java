package com.project.crud.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class AuthorsDto {
    private Integer authorId;
    private String name;
    private Integer yearOfBirth;
    private String countryOfBirth;
}