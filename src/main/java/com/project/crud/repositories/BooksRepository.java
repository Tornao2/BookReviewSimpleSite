package com.project.crud.repositories;

import com.project.crud.entities.Books;
import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<Books, String> {

}
