package com.project.crud.repositories;

import com.project.crud.entities.BooksAuthors;
import com.project.crud.entities.embeddable.BooksAuthorsId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BooksAuthorsRepository extends CrudRepository<BooksAuthors, BooksAuthorsId> {
    List<BooksAuthors> findByIdIsbn(String isbn);
    List<BooksAuthors> findByIdAuthorId(Integer authorId);
}
