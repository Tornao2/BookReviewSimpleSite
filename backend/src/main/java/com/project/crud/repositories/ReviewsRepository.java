package com.project.crud.repositories;

import com.project.crud.backend.entities.Reviews;
import com.project.crud.backend.entities.embeddable.ReviewsId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewsRepository extends CrudRepository<Reviews, ReviewsId> {
    List<Reviews> findByIdUsername(String username);
    List<Reviews> findByIdIsbn(String isbn);
    Optional<Reviews> findById(ReviewsId id);
}
