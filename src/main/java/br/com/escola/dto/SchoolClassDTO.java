package br.com.escola.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class SchoolClassDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long Id;
    private String name;

}
