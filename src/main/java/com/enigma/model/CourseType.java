package com.enigma.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "m_courseType")
@ToString
public class CourseType {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Getter @Setter
    private String typeId;

    @Column(name = "typeName", nullable = false, length = 100)
    @Getter @Setter
    private String typeName;

    @OneToMany(mappedBy = "courseType",fetch = FetchType.LAZY)
    @JsonManagedReference
//    @JsonBackReference
    @Setter @Getter
    @ToString.Exclude
    private List<Course> courseList;
}
