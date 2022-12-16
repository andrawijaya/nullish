package com.enigma.Service;

import com.enigma.model.User;
import com.enigma.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User[] getAll() {
       return  userRepository.getAll();
    }

    @Override
    public User getById(String id) {
        return userRepository.getById(id);
    }
}
