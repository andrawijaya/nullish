package com.enigma.Service;

import com.enigma.model.CourseInfo;
import com.enigma.repository.ICourseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInfoService implements ICourseInfoService {
@Autowired
    ICourseInfoRepository courseInfoRepository;

    @Override
    public List<CourseInfo> list() throws Exception {
        return courseInfoRepository.findAll();
    }
}
