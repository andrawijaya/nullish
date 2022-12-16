package com.enigma.Service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.model.Course;
import com.enigma.model.CourseType;
import com.enigma.repository.ICourseRepository;
import com.enigma.repository.ICourseTypeRepository;
import com.enigma.repository.spec.CourseSpec;
import com.enigma.repository.spec.SearchCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {
    @Autowired
    ICourseRepository courseRepository;

    @Autowired
    ICourseTypeService courseTypeService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Course> list() throws Exception {
       return courseRepository.findAll();
    }

    @Override
    public Course create(Course data) throws Exception {
        try {
            Optional<CourseType> newCourseType = courseTypeService.getBy(data.getCourseType().getTypeId());
            if(newCourseType.isEmpty()){
                throw new NotFoundException("Id Course Type is not found");
            }
            data.setCourseType(newCourseType.get());
            Course newCourses = courseRepository.save(data);
            return newCourses;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException("Data is exist");
        }
    }

    @Override
    public Optional<Course> get(String id)  {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new NotFoundException();
        }
        return course;
    }

    @Override
    public void update(Course data, String id) {
        try {
            Course existingCourse = get(id).get();
            modelMapper.map(data, existingCourse);
            courseRepository.save(existingCourse);
        } catch (Exception e) {
            throw new NotFoundException("Update Failed Because Id Not Found");
        }
    }

    @Override
    public void delete(String id)  {
        try {
            Course courseExisting = get(id).get();
            courseRepository.delete(courseExisting);
        } catch (Exception e) {
            throw new NotFoundException("Delete Failed Because Id Not Found");
        }
    }

    @Override
    public List<Course> findByTitleContains(String title) {
        List<Course> courses = courseRepository.findByTitleContains(title);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course with " + title + " title is not found");
        } else {
            return courses;
        }
    }

    @Override
    public List<Course> findByDescriptionContains(String description) {
        List<Course> courses = courseRepository.findByDescriptionContains(description);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course with " + description + " description is not found");
        } else {
            return courses;
        }
    }

    @Override
    public List<Course> findByLinkContains(String link) {
        List<Course> courses = courseRepository.findByLinkContains(link);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course with " + link + " link is not found");
        } else {
            return courses;
        }    }

    @Override
    public List<Course> getBy(String keyword, String value) throws Exception {
        keyword = keyword.toLowerCase();
        switch (keyword) {
            case "title":
                return findByTitleContains(value);
            case "description":
                return findByDescriptionContains(value);
            case "link":
                return findByLinkContains(value);
            default:
                return courseRepository.findAll();
        }
    }

    @Override
    public List<Course> getPagging(int size, int page, String sortBy, String direction) throws Exception {
        return null;
    }

    @Override
    public Page<Course> findWithPagination(Integer page, Integer size, String sortBy, String direction) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy);
        Page<Course> courses = courseRepository.findAll(PageRequest.of(page - 1, size, sort));

        return courses;
    }

    @Override
    public List<Course> getBy(SearchCriteria searchCriteria) {
        Specification spec = new CourseSpec().findBy(searchCriteria);
        return courseRepository.findAll(spec);
    }

    @Override
    public List<Course> getByTypeId(String typeId) {
        Specification spec = new CourseSpec().courseTypeId(typeId);
        return courseRepository.findAll(spec);
    }
}
