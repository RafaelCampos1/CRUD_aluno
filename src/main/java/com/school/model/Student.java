package com.school.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;



@Setter
@Getter
@Document(collection = "Student")
public class Student extends Person{
    private String registration;
    private String schoolClass;
}
