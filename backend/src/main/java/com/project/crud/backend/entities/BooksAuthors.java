package com.project.crud.backend.entities;

import com.project.crud.backend.entities.embeddable.BooksAuthorsId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class BooksAuthors {
    @EmbeddedId
    private BooksAuthorsId id;
}
