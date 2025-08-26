package com.project.crud.repositories;

import com.project.crud.entities.BooksAuthors;
import com.project.crud.entities.embeddable.BooksAuthorsId;
import org.springframework.data.repository.CrudRepository;

public interface BooksAuthorsRepository extends CrudRepository<BooksAuthors, BooksAuthorsId> {
}
