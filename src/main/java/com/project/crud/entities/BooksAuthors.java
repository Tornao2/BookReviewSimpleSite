package com.project.crud.entities;

import com.project.crud.entities.embeddable.BooksAuthorsId;
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
