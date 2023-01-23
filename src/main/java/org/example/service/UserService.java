package org.example.service;

import org.example.domain.User;
import org.example.exception.DuplicationIdException;
import org.example.repository.MemoryUserRepository;
import org.example.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(MemoryUserRepository memoryUserRepository){
        this.userRepository = memoryUserRepository;
    }

    public void UserRegister(User user) {
        User findUser = userRepository.findById(user.getId());
        if (findUser != null) throw new DuplicationIdException("이미 사용중인 ID 입니다.");
    }
}
