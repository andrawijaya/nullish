package com.enigma.Service;

import com.enigma.model.Course;
import com.enigma.model.CourseType;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ICourseTypeService {

    // get all
    List<CourseType> list() throws Exception;

    // create data
    CourseType create(CourseType data) throws Exception;

    // getBy id
    Optional<CourseType> getBy(String id) throws Exception;

    // update
    void update(CourseType data, String id) throws Exception;

    // delete
    void delete(String id) throws Exception;

    // get by with params
    List<CourseType> getBy(String keyword, String value) throws Exception;

    // get pagging with query method
    List<CourseType> getPagging(int size, int page, String sortBy, String direction) throws Exception;

    // get pagging with pageable
    Page<CourseType> findWithPagination(Integer page, Integer size, String sortBy, String direction) throws Exception;

}
