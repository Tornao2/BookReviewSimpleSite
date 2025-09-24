package com.project.crud.repositories;

import com.project.crud.backend.entities.Authors;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorsRepository extends CrudRepository<Authors, Integer> {
    boolean existsByNameAndYearOfBirthAndCountryOfBirth(String name, Integer yearOfBirth, String countryOfBirth);
    Optional<Authors> findByNameAndYearOfBirthAndCountryOfBirth(String name, Integer yearOfBirth, String countryOfBirth);
}
