package com.school.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Setter
@Getter
public class Student extends Person{
    private String registration;
    private String schoolClass;
}
