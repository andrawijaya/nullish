package com.enigma.Service;

import com.enigma.model.Course;
import com.enigma.model.CourseType;
import com.enigma.repository.spec.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> list() throws Exception;

    Course create(Course data) throws Exception;

    Optional<Course> get(String id) throws Exception;

    void update(Course data, String id) throws Exception;

    void delete(String id) throws Exception;

    List<Course> findByTitleContains(String title);

    List<Course> findByDescriptionContains(String description);

    List<Course> findByLinkContains(String link);

    List<Course> getBy(String keyword, String value) throws Exception;

    List<Course> getPagging(int size, int page, String sortBy, String direction) throws Exception;


    Page<Course> findWithPagination(Integer page, Integer size, String sortBy, String direction) throws Exception;

    List<Course> getBy(SearchCriteria searchCriteria);

    List<Course> getByTypeId(String typeId);
}
