package com.project.crud.entities.embeddable;

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
public class BooksGenresId implements Serializable {
    private String isbn;
    private Integer genreId;
}
