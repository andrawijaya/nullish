package com.enigma.Service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.model.Course;
import com.enigma.model.CourseType;
import com.enigma.repository.ICourseTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseTypeService implements ICourseTypeService {

    @Autowired
    ICourseTypeRepository courseTypeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<CourseType> list() {
        List<CourseType> courseTypes= courseTypeRepository.findAll();
        return courseTypes;
    }

    @Override
    public CourseType create(CourseType data) {
        try {
            CourseType newCourseType = courseTypeRepository.save(data);
            return newCourseType;
        } catch (Exception e) {
            throw new EntityExistException("Data is exist");
        }
    }

    @Override
    public Optional<CourseType> getBy(String id) {
        Optional<CourseType> courseType = courseTypeRepository.findById(id);
        if (courseType.isEmpty()) {
            throw new NotFoundException("Course Not Found");
        }
        return courseType;
    }

    @Override
    public void update(CourseType data, String id)  {
        try {
            CourseType existingCourse = getBy(id).get();
            modelMapper.map(data, existingCourse);
            courseTypeRepository.save(existingCourse);
        } catch (Exception e) {
            throw new NotFoundException("Update Failed Because Id Not Found");
        }
    }

    @Override
    public void delete(String id) {
        try {
            CourseType courseExisting = getBy(id).get();
            courseTypeRepository.delete(courseExisting);
        } catch (Exception e) {
            throw new NotFoundException("Delete Failed Because Id Not Found");
        }
    }

    @Override
    public List<CourseType> getBy(String keyword, String value) {
        return null;
    }

    @Override
    public List<CourseType> getPagging(int size, int page, String sortBy, String direction) {
        return null;
    }

    @Override
    public Page<CourseType> findWithPagination(Integer page, Integer size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy);
        Page<CourseType> courseTypes = courseTypeRepository.findAll(PageRequest.of(page - 1, size, sort));
        return courseTypes;
    }

//    @Override
//    public CourseType create(CourseType courseType) throws Exception {
//        try {
//            CourseType newCourseType = courseRepository.save(courseType);
//            return newCourseType;
//        } catch (DataIntegrityViolationException e) {
//            throw new Exception(e.getMessage());
//        }
//    }

}
