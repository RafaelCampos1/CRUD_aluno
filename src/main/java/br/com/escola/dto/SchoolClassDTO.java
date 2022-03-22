package br.com.escola.dto;

import br.com.escola.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SchoolClassDTO {

    private Long Id;
    private String schoolClassName;
    private List<Student> studentList = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return Id;
    }
    @JsonIgnore
    public void setId(Long id) {
        Id = id;
    }
}
