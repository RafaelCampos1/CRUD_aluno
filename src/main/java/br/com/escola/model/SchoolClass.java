package br.com.escola.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Student> studentList = new ArrayList<>();

    public SchoolClass(Long Id,String name, List<Student> studentList) {
        this.Id = Id;
        this.name = name;
        this.studentList = studentList;
    }

    public SchoolClass() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public SchoolClass setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        return null;
    }
}
