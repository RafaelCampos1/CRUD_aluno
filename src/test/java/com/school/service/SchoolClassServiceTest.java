package com.school.service;

import com.school.dto.SchoolClassDTO;
import com.school.model.SchoolClass;
import com.school.model.Student;
import com.school.repository.SchoolClassRepository;
import com.school.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SchoolClassServiceTest {

    @Mock
    SchoolClassRepository schoolClassRepository;

    @Mock
    StudentRepository studentRepository;

    @InjectMocks//preciso testar os metodos
    SchoolClassService schoolClassService;
    @InjectMocks//preciso testar os metodos
    StudentService studentService;

    private SchoolClass schoolClass;
    private Student student;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startSchoolClass();
    }

    @Test
    void convertToEntity() {
        Mockito.when(schoolClassRepository.findBySchoolClassName(Mockito.anyString())).thenReturn(schoolClass);
        SchoolClassDTO schoolClassDTO = new ModelMapper().map(schoolClassService.findSchoolByName("School Class A"), SchoolClassDTO.class);
        SchoolClass schoolClassResponse = schoolClassService.convertToEntity(schoolClassDTO);
        assertNotNull(schoolClassResponse);
        assertEquals(SchoolClass.class,schoolClassResponse.getClass());
    }

    @Test
    void convertToDTO() {
        Mockito.when(schoolClassRepository.findBySchoolClassName(Mockito.anyString())).thenReturn(schoolClass);
        SchoolClass schoolClass = schoolClassService.findSchoolByName("School Class A");
        SchoolClassDTO schoolClassDTO = schoolClassService.convertToDTO(schoolClass);
        assertNotNull(schoolClassDTO);
        assertEquals(SchoolClassDTO.class,schoolClassDTO.getClass());
    }

    @Test
    void convertToListDTO() {
        Mockito.when(schoolClassRepository.findAll()).thenReturn(List.of(schoolClass));
        List<SchoolClass> schoolClassList = schoolClassService.findAllSchoolClasses();
        List<SchoolClassDTO> schoolClassDTOList = schoolClassService.convertToListDTO(schoolClassList);
        assertNotNull(schoolClassDTOList);
        assertEquals(SchoolClassDTO.class,schoolClassDTOList.get(0).getClass());
    }

    @Test
    void findSchoolByName() {
        Mockito.when(schoolClassRepository.findBySchoolClassName("School Class A")).thenReturn(schoolClass);
        SchoolClass responseSchoolClass = schoolClassService.findSchoolByName("School Class A");
        assertNotNull(responseSchoolClass);
        assertEquals(schoolClass, responseSchoolClass);
    }




    @Test
    void findSchoolClassNameInDESC() {



    }

    @Test
    void findAllSchoolClasses() {
        Mockito.when(schoolClassService.findAllSchoolClasses()).thenReturn(List.of(schoolClass));
        List<SchoolClass> schoolClassList = schoolClassService.findAllSchoolClasses();
        assertNotNull(schoolClassList);
        assertEquals(SchoolClass.class,schoolClassList.get(0).getClass());
    }

    @Test
    void saveSchoolClass() {
        Mockito.when(schoolClassService.saveSchoolClass(Mockito.any())).thenReturn(schoolClass);
        SchoolClass savedSchoolClass = schoolClassService.saveSchoolClass(schoolClass);
        assertNotNull(savedSchoolClass);
    }

    public void startSchoolClass(){
        schoolClass = new SchoolClass();
        schoolClass.setId(1L);
        schoolClass.setSchoolClassName("School Class A");
    }

    public void startStudent(){
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