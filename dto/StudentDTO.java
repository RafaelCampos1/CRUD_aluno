package br.com.escola.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private String registration;
    private String schoolClass;
}
