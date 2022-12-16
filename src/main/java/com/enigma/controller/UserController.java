package com.enigma.controller;

import com.enigma.Service.IUserService;
import com.enigma.model.response.ErrorResponse;
import com.enigma.model.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/")
public class UserController {
private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getUser(){
      var result =   userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("succes get all user", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id){
        var result = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("succes getby Id", result));
    }
}
