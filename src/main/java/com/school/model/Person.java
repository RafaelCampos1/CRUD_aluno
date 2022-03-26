package com.school.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
public class Person {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
}
