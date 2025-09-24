package com.project.crud.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class BooksGenresDto {
    private String isbn;
    private String title;
}
