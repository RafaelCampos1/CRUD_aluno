package br.com.escola.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SchoolClassDTO {

    @JsonIgnore
    private Long Id;
    private String firstName;
    @JsonIgnore
    private String lastName;

}
