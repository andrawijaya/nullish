package com.enigma.controller;

import com.enigma.Service.ICourseInfoService;
import com.enigma.model.Course;
import com.enigma.model.CourseInfo;
import com.enigma.model.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courseInfo")
public class CourseInfoController {
    @Autowired
    ICourseInfoService courseInfoService;

    // Get All
    @GetMapping
    public ResponseEntity GetAllCourse() throws Exception{
        List<CourseInfo> courses = courseInfoService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get All Course", courses));
    }
}
