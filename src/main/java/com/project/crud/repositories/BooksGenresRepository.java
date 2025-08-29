package com.project.crud.repositories;

import com.project.crud.entities.BooksGenres;
import com.project.crud.entities.embeddable.BooksGenresId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BooksGenresRepository extends CrudRepository<BooksGenres, BooksGenresId> {
    List<BooksGenres> findByIdGenreId(Integer genreId);
    List<BooksGenres> findByIdIsbn(String isbn);
}
