package com.school.service;

import com.school.dto.StudentDTO;
import com.school.enums.ErrorDescription;
import com.school.exceptionhandler.ConflictException;
import com.school.exceptionhandler.NotFoundException;
import com.school.model.SchoolClass;
import com.school.model.Student;
import com.school.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j

class StudentServiceTest {


    @Mock
    StudentRepository studentRepository;
    @Mock
    SchoolClassService schoolClassService;

    @InjectMocks//preciso testar os metodos
    StudentService studentService;

    private Student student;
    private Student studentNull;
    private SchoolClass schoolClass;


    //executado antes de qualquer coisa da classe
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        startStudent();
    }

    @Test
    void convertToDTO() {
        Mockito.when(studentRepository.findById(Mockito.anyString())).thenReturn(Optional.of(student));
        Student student = studentService.findById("1L");
        StudentDTO studentDTO = studentService.convertToDTO(student);
        assertNotNull(studentDTO);
        assertEquals(StudentDTO.class,studentDTO.getClass());
    }

    @Test
    void convertToListDTO() {
        Mockito.when(studentRepository.findAll()).thenReturn(List.of(student));
        List<Student> studentList = studentService.findAllStudents();
        List<StudentDTO> studentDTOList = studentService.convertToListDTO(studentList);
        assertNotNull(studentDTOList);
        assertEquals(StudentDTO.class,studentDTOList.get(0).getClass());
    }

    @Test
    void convertToEntity() {
        Mockito.when(studentRepository.findById(Mockito.anyString())).thenReturn(Optional.of(student));
        StudentDTO studentDTO = new ModelMapper().map(studentService.findById("1"), StudentDTO.class);
        Student studentResponse = studentService.convertToEntity(studentDTO);
        assertNotNull(studentResponse);
        assertEquals(Student.class,studentResponse.getClass());
    }

    @Test
    void whenFindAStudentBySameEmailReturnAConflict() {
        Mockito.when(studentRepository.findByEmail(Mockito.anyString()))
                .thenReturn(student);
        ConflictException conflictException = assertThrows(ConflictException.class, () -> {
            studentService.findStudentByEmail(student);
        });
        Assertions.assertEquals(ErrorDescription.SAME_EMAIL.getDescription(),conflictException.getMessage());
    }

    @Test
    void whenFindAStudentBySameCpfReturnAConflict() {
        Mockito.when(studentRepository.findByCpf(Mockito.anyString()))
                .thenReturn(student);
        ConflictException conflictException = assertThrows(ConflictException.class, () -> {
            studentService.findStudentByCpf(student);
        });
        Assertions.assertEquals(ErrorDescription.SAME_CPF.getDescription(),conflictException.getMessage());
    }

    @Test
    void whenFindByIdReturnAnStudent() {
        Mockito.when(studentRepository.findById("1"))
                .thenReturn(Optional.of(student));
        Student student = studentService.findById("1");
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
        Assertions.assertEquals(ErrorDescription.STUDENT_NOT_FOUND.getDescription(),notFoundException.getMessage());

    }

    @Test
    void whenFindAllStudentsReturnListOfStudents() {
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
    void getNewSchoolClassNamee() {
        startSchoolClass();
        List<SchoolClass> schoolClassListZero = schoolClassService.findAllSchoolClasses();
        assertTrue(schoolClassListZero.isEmpty());
        String nameZero = studentService.getNewSchoolClassName();
        assertTrue(nameZero.contains("School Class A"));

        Mockito.when(schoolClassService.findAllSchoolClasses()).thenReturn(List.of(schoolClass));
        List<SchoolClass> schoolClassList = schoolClassService.findAllSchoolClasses();
        assertEquals(schoolClassList.size(),1);
        String name = studentService.getNewSchoolClassName();
        assertTrue(name.contains("School Class"));


    }


    @Test
    void saveStudent() {
        startSchoolClass();
    //    Mockito.when(studentRepository.save(student)).thenReturn(student);

       // Student studentResponse = studentService.saveStudent(student);

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
    }

    private void startSchoolClass(){
        schoolClass = new SchoolClass();
        schoolClass.setId("1");
        schoolClass.setSchoolClassName("rafael");
    }

}