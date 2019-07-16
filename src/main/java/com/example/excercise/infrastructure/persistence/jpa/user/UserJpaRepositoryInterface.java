package com.example.excercise.infrastructure.persistence.jpa.user;

import com.example.excercise.domain.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepositoryInterface extends CrudRepository<User, Integer> {
    Optional<User> findById(Integer id);
}
