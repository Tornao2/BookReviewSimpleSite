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
public class Books {
    @Id
    private String isbn;
    private String bookTitle;
    private String publisherName;
    private Integer pageNumber;
    private String language;
    private String description;
    private Integer yearOfRelease;
}
