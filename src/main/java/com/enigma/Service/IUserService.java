package com.enigma.Service;

import com.enigma.model.User;

public interface IUserService {
    User[] getAll();

    User getById(String id);
}
