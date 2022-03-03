package br.com.escola.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class Person {

    @Id
    private Integer id;
    private String firstName;
    private String secondName;
    private String realID;
    private String email;

}
