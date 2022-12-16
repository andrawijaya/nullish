package com.enigma.controller;
import com.enigma.Service.ICourseService;
import com.enigma.Service.ICourseTypeService;
import com.enigma.model.CourseType;
import com.enigma.model.request.CourseTypeRequest;
import com.enigma.model.response.PaggingResponse;
import com.enigma.model.response.SuccessResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping(value = "/courseType")
public class CourseTypeController {
    @Autowired
    ICourseTypeService courseTypeService;

    @Autowired
    private ModelMapper modelMapper;

    // Get All Course Type
    @GetMapping
    public ResponseEntity getAllCourseType() throws Exception {
        List<CourseType> courseTypes = courseTypeService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes Get All Course Type", courseTypes));
    }

    // Create Course Type
    @PostMapping
    public ResponseEntity createCourseType(@Valid  @RequestBody CourseTypeRequest courseTypeRequest) throws Exception {
        CourseType newCourseType = modelMapper.map(courseTypeRequest, CourseType.class);
        CourseType result = courseTypeService.create(newCourseType);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success Created Course", result));
    }

    // Get By ID
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) throws Exception{
        Optional< CourseType> courseType = courseTypeService.getBy(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(new SuccessResponse<>("Success Get CourseType by Id", courseType));
    }

    // Delete By id
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) throws Exception{
        courseTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data with id " + id + " has been Delete !");
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity updateCourseType(@PathVariable("id") String id, @RequestBody CourseTypeRequest courseTypeRequest) throws Exception{
        CourseType newCourseType = modelMapper.map(courseTypeRequest, CourseType.class);
        newCourseType.setTypeId(id);
        courseTypeService.update(newCourseType, id);
        CourseType result = newCourseType;
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Data with id "+ id + " has been Updated !",result));
    }

    // pagging
    @GetMapping(params = {"page", "size", "sortBy", "direction"})
    public ResponseEntity getWithPagination(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "typeId") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) throws Exception{

        Page<CourseType> result = courseTypeService.findWithPagination(page,size,sortBy,direction);

        return ResponseEntity.status(HttpStatus.FOUND).body(new PaggingResponse<>("Get Course Type page "+page+", sort by "+sortBy+", with direction "+direction+", success", result));


    }

}
