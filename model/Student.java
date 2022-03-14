package br.com.escola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student extends Person{
    private String registration;
    @JsonIgnore
    private String schoolClass;
}
