package org.example.repository;

import org.example.domain.User;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserRepository implements UserRepository{

    private Map<Long, User> users = new HashMap<>();

    @Override
    public void register(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User findById(Long id) {
        return users.get(id);
    }
}
