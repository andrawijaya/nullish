package com.enigma.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
public class CourseRequest {
    @NotBlank(message = "title is required")
    @Getter @Setter
    private String title;

    @Getter @Setter
    private String description;

    @NotBlank(message = "link is required")
    @Getter @Setter
    private String link;

    @Getter @Setter
    private CourseInfoRequest courseInfo;

    @Getter @Setter
    private CourseTypeIdRequest courseType;

}
