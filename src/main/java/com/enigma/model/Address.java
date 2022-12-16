package com.enigma.model;

import lombok.Getter;
import lombok.Setter;

public class Address {
    @Getter @Setter
    private String street;

    @Getter @Setter
    private String suite;

    @Getter @Setter
    private String city;

    @Getter @Setter
    private String zipcode;

    @Getter @Setter
    private Geo geo;

}
