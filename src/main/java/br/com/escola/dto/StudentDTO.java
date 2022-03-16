package br.com.escola.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StudentDTO {

    private Long Id;
    @NotBlank(message = "{firstName.not.blank}")
    private String firstName;
    @NotBlank(message = "{lastName.not.blank}")
    private String lastName;
    @NotBlank(message = "{email.not.blank}")
    private String email;
    @NotBlank(message = "{cpf.not.blank}")
    private String cpf;
    private String registration;
    private String schoolClass;

}
