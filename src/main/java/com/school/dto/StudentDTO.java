package com.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StudentDTO {


    private String id;
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
    public String getId() {
        return id;
    }

    @JsonIgnore
    public void setId(String id) {
        this.id = id;
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
