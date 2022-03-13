package br.com.escola.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Student> studentList = new ArrayList<>();

    public SchoolClass(String name,List<Student> studentList) {
        this.studentList = studentList;
        this.name = name;
    }

    public SchoolClass() {
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

}
