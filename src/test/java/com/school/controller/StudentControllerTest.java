package com.school.controller;

import com.school.dto.StudentDTO;
import com.school.model.Student;
import com.school.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class StudentControllerTest {

    @Mock//preciso testar os metodos
    StudentService studentService;

    @InjectMocks
    StudentController studentController;

    private Student student;
    private StudentDTO studentDTO;


    //executado antes de qualquer coisa da classe
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        startStudent();
    }

    @Test
    void getStudent() {
        Mockito.when(studentService.findById(Mockito.anyString())).thenReturn(student);
        ResponseEntity<StudentDTO> studentDTOResponse = studentController.getStudent(student.getId());

        assertEquals(studentDTOResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void updateStudent() {
        Mockito.when(studentService.updateStudent(Mockito.any())).thenReturn(student);
        Mockito.when(studentService.convertToEntity(Mockito.any())).thenReturn(student);
        ResponseEntity<StudentDTO> studentDTOResponse = studentController.updateStudent(studentDTO,studentDTO.getId());

        assertEquals(studentDTOResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getStudents() {
        Mockito.when(studentService.findAllStudents()).thenReturn(List.of(student));
        ResponseEntity<List<StudentDTO>> listResponseEntity = studentController.getStudents();

        assertEquals(listResponseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    void saveStudent() {
        Mockito.when(studentService.saveStudent(Mockito.any())).thenReturn(student);
        ResponseEntity<StudentDTO> responseEntity = studentController.saveStudent(studentDTO);

        assertEquals(responseEntity.getStatusCode(),HttpStatus.CREATED);
    }

    private void startStudent(){
        student = new Student();
        student.setId("1");
        student.setFirstName("rafael");
        student.setLastName("Campos");
        student.setCpf("081");
        student.setEmail("tetinha");
        student.setSchoolClass("turma A");
        student.setRegistration("Registration mockada");
        studentDTO = new ModelMapper().map(student,StudentDTO.class);
    }
}