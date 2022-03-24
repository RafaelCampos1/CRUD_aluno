package com.school.dto;

import com.school.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
