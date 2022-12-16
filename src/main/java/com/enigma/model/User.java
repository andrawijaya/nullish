package com.enigma.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private Address address;
    @Getter @Setter
    private String phone;
    @Getter @Setter
    private String website;
    @Getter @Setter
    private Company company;
}
