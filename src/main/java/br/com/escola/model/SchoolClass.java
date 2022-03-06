package br.com.escola.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Student> studentList;


    public SchoolClass(String name, List<Student> studentList) {
        this.name = name;
        this.studentList = studentList;
    }

    public SchoolClass() {

    }
}
