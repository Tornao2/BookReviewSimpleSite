package com.project.crud.repositories;

import com.project.crud.backend.entities.BooksGenres;
import com.project.crud.backend.entities.embeddable.BooksGenresId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BooksGenresRepository extends CrudRepository<BooksGenres, BooksGenresId> {
    List<BooksGenres> findByIdTitle(String title);
    List<BooksGenres> findByIdIsbn(String isbn);
    Optional<BooksGenres> findById(BooksGenresId id);
}
