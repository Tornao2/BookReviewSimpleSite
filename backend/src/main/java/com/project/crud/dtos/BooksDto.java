package com.project.crud.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class BooksDto {
    private String isbn;
    private String bookTitle;
    private String publisherName;
    private Integer pageNumber;
    private String language;
    private String description;
    private Integer yearOfRelease;
}
