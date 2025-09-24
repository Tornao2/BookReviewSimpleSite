package com.project.crud.backend.entities.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@EqualsAndHashCode
public class BooksAuthorsId implements Serializable {
    private String isbn;
    private Integer authorId;
}
