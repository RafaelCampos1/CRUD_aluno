package br.com.escola.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
public class SchoolClassDTO {

    @JsonIgnore
    private Long Id;
    private String firstName;
    @JsonIgnore
    private String lastName;


}
