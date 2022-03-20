package br.com.escola.service;

import br.com.escola.dto.StudentDTO;
import br.com.escola.enums.ErrorDescription;
import br.com.escola.exceptionhandler.ConflictException;
import br.com.escola.exceptionhandler.NotFoundException;
import br.com.escola.model.Student;
import br.com.escola.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j

class StudentServiceTest {


    @Mock
    StudentRepository studentRepository;
    SchoolClassService schoolClassService;

    @InjectMocks//preciso testar os metodos
    StudentService studentService;

    private Student student;
    private StudentDTO studentDTO;

    //executado antes de qualquer coisa da classe
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        startStudent();
    }

    @Test
    void convertToDTO() {
     //   Mockito.when()
    }

    @Test
    void convertToListDTO() {
    }

    @Test
    void convertToEntity() {
    }

    @Test
    void whenFindAStudentBySameCpfReturnAConflict() {
        Mockito.when(studentRepository.findByEmail(Mockito.anyString()))
                .thenReturn(student);
        ConflictException conflictException = assertThrows(ConflictException.class, () -> {
            studentService.findStudentByEmail(student.getEmail());
        });
        Assertions.assertEquals(ErrorDescription.SAME_EMAIL.getErrorDescription(),conflictException.getMessage());
    }

    @Test
    void whenFindByIdReturnAnStudent() {
        Mockito.when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        Student student = studentService.findById(1L);
        Assertions.assertEquals(Student.class,student.getClass());
        Assertions.assertNotNull(Student.class);
    }

    @Test
    void whenDontFindByIdReturnAnObjectNotFound() {
        Mockito.when(studentRepository.findById(student.getId()))
                .thenReturn(Optional.of(student));

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> {
            studentService.findById(student.getId()+1);
        });
        Assertions.assertNotNull(notFoundException);
        Assertions.assertEquals(ErrorDescription.STUDENT_NOT_FOUND.getErrorDescription(),notFoundException.getMessage());

    }

    @Test
    void whenFindAllStudentsReturnListOfStudents() {
        //mockando p dar um save falso
        Mockito.when(studentRepository.findAll()).thenReturn(List.of(student));
        List<Student> studentList = studentService.findAllStudents();
        assertNotNull(studentList);
        Assertions.assertEquals(Student.class, studentList.get(0).getClass());
    }

    @Test
    void updateStudent() {
        Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.save(student)).thenReturn(student);

        Student studentResponse = studentService.updateStudent(student);
        assertNotNull(studentResponse);
        assertEquals(Student.class, studentResponse.getClass());
    }

    @Test
    void saveStudent() {
        Mockito.when(studentRepository.save(student)).thenReturn(student);

        Student studentResponse = studentService.saveStudent(student);

    }

    @Test
    void validateStudent() {
    }

    @Test
    void getNewSchoolClassName() {
    }

    private void startStudent(){
        student = new Student();
        student.setId(1L);
        student.setFirstName("rafael");
        student.setLastName("Campos");
        student.setCpf("081");
        student.setEmail("tetinha");
        student.setSchoolClass("turma A");
        student.setRegistration("Registration mockada");
    }

}