package com.enigma.repository;

import com.enigma.exception.RestTemplateException;
import com.enigma.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;

@Repository
public class UserRepository implements IUserRepository {

    @Value("${USER_SVC}")
    private String serviceUserUri;

    private RestTemplate restTemplate;

    public UserRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User[] getAll() {
        try{
            ResponseEntity<User[]> response = restTemplate.getForEntity(serviceUserUri, User[].class);
            return response.getBody();
        }catch (Exception e){
            return null;
        }
    }

        @Override
        public User getById(String id) {
            try{
                ResponseEntity<User> response = restTemplate.getForEntity(serviceUserUri+"/"+id, User.class);
                return response.getBody();
            }catch (Exception e){
                return null;
            }
        }
}
