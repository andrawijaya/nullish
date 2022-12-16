package com.enigma.repository.spec;

import com.enigma.model.CourseType;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

@ToString
public class CourseTypeSpec {
    //    public Specification<CourseType>  typeNameLike(String name) {
//        return new Specification<CourseType>(){
//            @Override
//            public Predicate toPredicate(Root<CourseType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.like(root.get("typeName"),"%"+ name+"%");
//            }
//        };
//    }
    public Specification<CourseType> typeNameLike(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("typeName"), "%" + name + "%"));
    }
}
