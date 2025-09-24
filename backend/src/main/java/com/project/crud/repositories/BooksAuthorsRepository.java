package com.project.crud.repositories;

import com.project.crud.backend.entities.BooksAuthors;
import com.project.crud.backend.entities.embeddable.BooksAuthorsId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BooksAuthorsRepository extends CrudRepository<BooksAuthors, BooksAuthorsId> {
    List<BooksAuthors> findByIdIsbn(String isbn);
    List<BooksAuthors> findByIdAuthorId(Integer authorId);
    Optional<BooksAuthors> findById(BooksAuthorsId id);
}
