package br.com.escola.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @NotBlank(message = "{registration.not.blank}")
    private String registration;
    private String schoolClass;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return Id;
    }

    @JsonIgnore
    public void setId(Long id) {
        Id = id;
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getSchoolClass() {
        return schoolClass;
    }

    @JsonIgnore
    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }
}
