package com.project.crud.repositories;

import com.project.crud.entities.Authors;
import org.springframework.data.repository.CrudRepository;

public interface AuthorsRepository extends CrudRepository<Authors, Integer> {
}
