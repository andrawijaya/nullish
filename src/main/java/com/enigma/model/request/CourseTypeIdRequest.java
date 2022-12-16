package com.enigma.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
public class CourseTypeIdRequest {
    @NotBlank(message = "typeName is required")
    @Getter @Setter
    private String typeId;
}
