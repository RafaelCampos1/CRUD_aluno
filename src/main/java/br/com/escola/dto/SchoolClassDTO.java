package br.com.escola.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SchoolClassDTO {


    private Long Id;
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return Id;
    }
    @JsonIgnore
    public void setId(Long id) {
        Id = id;
    }
}
