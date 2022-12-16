package com.enigma.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_courseInfo")
@ToString
public class CourseInfo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Setter @Getter
    private String infoId;

    @Column(name = "duration", nullable = false, length = 100)
    @Setter @Getter
    private String  duration;

    @Column(name = "level", nullable = false, length = 100)
    @Setter @Getter
    private String level;
}
