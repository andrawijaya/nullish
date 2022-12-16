package com.enigma.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
public class CourseInfoRequest {
    @NotBlank(message = "duration id required")
    @Getter @Setter
    private String duration;

    @NotBlank(message = "level is required")
    @Getter @Setter
    private String level;

}
