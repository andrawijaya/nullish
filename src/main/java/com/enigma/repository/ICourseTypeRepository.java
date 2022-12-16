package com.enigma.repository;

import com.enigma.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseTypeRepository extends JpaRepository<CourseType, String> {
}
