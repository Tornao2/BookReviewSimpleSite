package com.project.crud.repositories;

import com.project.crud.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, String> {
}
