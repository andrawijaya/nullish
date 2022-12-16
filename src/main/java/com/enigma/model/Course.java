package com.enigma.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_course")
@ToString
public class Course {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Setter @Getter
    private String courseId;

    @Column(name = "title", nullable = false , length = 100)
    @Setter @Getter
    private String title;

    @Column(name = "description", length = 200)
    @Setter @Getter
    private String description;

    @Column(name = "link", nullable = false, length = 200)
    @Setter @Getter
    private String link;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "courseInfo")
    @Setter @Getter
    private CourseInfo courseInfo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "typeId", referencedColumnName = "typeId", nullable = false)
    @JsonBackReference
//    @JsonManagedReference
//    @JsonIgnore
    @Setter @Getter
    private CourseType courseType;


}
