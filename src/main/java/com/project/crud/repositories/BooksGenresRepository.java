package com.project.crud.repositories;

import com.project.crud.entities.BooksGenres;
import com.project.crud.entities.embeddable.BooksGenresId;
import org.springframework.data.repository.CrudRepository;

public interface BooksGenresRepository extends CrudRepository<BooksGenres, BooksGenresId> {
}
