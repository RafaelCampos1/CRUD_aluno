package com.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Student extends Person{
    private String registration;
    private String schoolClass;
}
