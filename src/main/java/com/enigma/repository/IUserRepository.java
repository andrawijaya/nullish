package com.enigma.repository;

import com.enigma.model.User;

public interface IUserRepository {
    User[] getAll();

    User getById(String id);

}
