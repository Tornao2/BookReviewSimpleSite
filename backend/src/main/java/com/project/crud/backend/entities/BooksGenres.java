package com.project.crud.backend.entities;

import com.project.crud.backend.entities.embeddable.BooksGenresId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class BooksGenres {
    @EmbeddedId
    private BooksGenresId id;
}
