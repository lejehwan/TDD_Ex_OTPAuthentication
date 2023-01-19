package org.example.repository;

import org.example.domain.User;

public interface UserRepository {

    void register(User user);

    User findById(Long id);
}
