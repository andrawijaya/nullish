package com.enigma.controller;

import com.enigma.Service.ICourseService;
import com.enigma.model.Course;
import com.enigma.model.request.CourseRequest;
import com.enigma.model.response.PaggingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.repository.spec.SearchCriteria;
import com.enigma.util.QueryOperator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    ICourseService courseService;

    @Autowired
    ModelMapper modelMapper;

    // Get All
    @GetMapping
    public ResponseEntity GetAllCourse() throws Exception{
        List<Course> courses = courseService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get All Course", courses));
    }

    // create course
    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseRequest courseRequest)throws Exception{
        System.out.println(courseRequest);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Course newCourse = modelMapper.map(courseRequest, Course.class);
        System.out.println(newCourse);
        Course result = courseService.create(newCourse);
        System.out.println(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Create course success", result));
    }

    // find by id
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") String id) throws Exception{
        Optional<Course> courses = courseService.get(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(new SuccessResponse<>("Success get Course By id", courses));
    }

    // delete course
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable("id") String id) throws Exception{
        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data with id "+ id + "has been Delete !");
    }

    // update course
    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@PathVariable("id") String id, @RequestBody CourseRequest courseRequest) throws Exception{
        Course newCourse = modelMapper.map(courseRequest, Course.class);
        newCourse.setCourseId(id);
        courseService.update(newCourse, id);
        Course result = newCourse;
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Data with id "+ id + " has been updated !", result));
    }

    // pagging
    @GetMapping(params = {"page", "size", "sortBy", "direction"})
    public ResponseEntity getWithPagging(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "courseId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) throws Exception{
        Page<Course> result = courseService.findWithPagination(page, size, sortBy,direction);
        return ResponseEntity.status(HttpStatus.FOUND).body(new PaggingResponse<>("Get Course Type page "+page+", sort by "+sortBy+", with direction "+direction+", success", result));
    }

    @GetMapping(params = {"key","operator","value"})
    public ResponseEntity getBy(
            @RequestParam(defaultValue = "title") String key,
            @RequestParam String operator,
            @RequestParam String value) throws Exception {

        SearchCriteria criteria = new SearchCriteria();
        criteria.setKey(key);
        criteria.setOperator(Enum.valueOf(QueryOperator.class, operator));
        criteria.setValue(value);
        List<Course> result = courseService.getBy(criteria);

        return ResponseEntity.status(HttpStatus.FOUND).body(new SuccessResponse<>("Get Course by " + key + ", success", result));
    }

    @GetMapping("/type={typeId}")
    public ResponseEntity getCourseByTypeId(@PathVariable("typeId") String typeId) throws Exception{
        List<Course> courses = courseService.getByTypeId(typeId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find Course By Type", courses));
    }

}
