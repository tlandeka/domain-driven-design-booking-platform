package com.example.excercise.infrastructure.persistence.jpa.user;

import com.example.excercise.domain.model.user.User;
import com.example.excercise.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserJpaRepository implements UserRepository {

    @Autowired
    UserJpaRepositoryInterface crudRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> item = this.crudRepository.findById(id);
        if(!item.isPresent()){
            return null;
        }

        return item.get();
    }

    @Override
    public void saveAll(List<User> users) {
        Object o  = crudRepository.saveAll(users);
    }
}
