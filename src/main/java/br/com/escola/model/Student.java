package br.com.escola.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student extends Person{
    private String registration;
    private String schoolClass;
}
