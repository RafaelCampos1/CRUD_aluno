package com.school.controller;

import com.school.dto.SchoolClassDTO;
import com.school.model.SchoolClass;
import com.school.model.Student;
import com.school.service.SchoolClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class SchoolClassControllerTest {
    @Mock
    SchoolClassService schoolClassService;

    private SchoolClass schoolClass;
    @InjectMocks
    private SchoolClassController schoolClassController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startSchoolClass();
    }
    @Test
    void getSchoolClass() {
        Mockito.when(schoolClassService.findSchoolByName(Mockito.anyString())).thenReturn(schoolClass);

        ResponseEntity<SchoolClassDTO> schoolClassDTOResponse = schoolClassController.getSchoolClass(schoolClass.getSchoolClassName());
        assertEquals(schoolClassDTOResponse.getStatusCode(), HttpStatus.OK);
    }

    public void startSchoolClass(){
        schoolClass = new SchoolClass();
        schoolClass.setId(1L);
        schoolClass.setSchoolClassName("School Class A");
    }

}