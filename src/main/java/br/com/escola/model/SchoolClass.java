package br.com.escola.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SchoolClass {

    @Id
    private Integer Id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Student> studentList;

}
