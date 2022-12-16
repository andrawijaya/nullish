package com.enigma.repository;

import com.enigma.model.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseInfoRepository extends JpaRepository<CourseInfo, String> {
}
