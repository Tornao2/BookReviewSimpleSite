package com.project.crud.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class BooksAuthorsDto {
    private String isbn;
    private String name;
}
