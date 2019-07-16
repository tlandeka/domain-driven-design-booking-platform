package com.example.excercise.domain.model.user;

import java.util.List;

public interface UserRepository {
    User findById(Integer id);

    void saveAll(List<User> users);
}
