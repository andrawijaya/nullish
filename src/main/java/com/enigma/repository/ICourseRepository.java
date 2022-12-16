package com.enigma.repository;

import com.enigma.model.Course;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, String> {
    @Query("select c from Course c where c.title like %?1%")
    List<Course>    findByTitleContains(String title);

    @Query("select c from Course c where c.description like %?1%")
    List<Course> findByDescriptionContains(String description);

    @Query("select c from Course c where c.link like %?1%")
    List<Course> findByLinkContains(String link);

    List<Course> findAll(Specification specification);

}
