package com.school.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "SchoolClass")
public class SchoolClass {

    @Id
    private String id;
    private String schoolClassName;

    @DBRef
    private List<Student> studentList = new ArrayList<>();

}
