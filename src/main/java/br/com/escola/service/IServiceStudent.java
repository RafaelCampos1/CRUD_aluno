package br.com.escola.service;

import br.com.escola.model.Student;

import java.util.List;

public interface IServiceStudent {
    List<Student> getStudent();
    Student saveStudent(Student student);
}
