package com.project.crud.repositories;

import com.project.crud.entities.Genres;
import org.springframework.data.repository.CrudRepository;

public interface GenresRepository extends CrudRepository<Genres, Integer> {
}
