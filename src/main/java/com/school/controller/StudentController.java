package com.school.controller;

import com.school.dto.StudentDTO;
import com.school.model.Student;
import com.school.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class StudentController {


    private final StudentService studentService;

    @GetMapping("/student/{id}")
    @Operation(summary = "Find a student",description = "Returns a student by their id")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable String id) {
        return ResponseEntity.ok().body(studentService.convertToDTO(studentService.findById(id)));
    }

    @PutMapping("update/student/{id}")
    @Operation(summary = "Update a student by their id",description = "Update a student by their id (important to put all student information)")
    public ResponseEntity<StudentDTO> updateStudent(@Valid @RequestBody StudentDTO studentDTO, @PathVariable String id) {
        Student student = studentService.convertToEntity(studentDTO);
        student.setId(id);
        return ResponseEntity.ok().body(studentService.convertToDTO(studentService.updateStudent(student)));
    }
    @CrossOrigin
    @GetMapping("/students")
    @Operation(summary = "Find all students",description = "Returns all information for each student that is registered in the database")
    public ResponseEntity<List<StudentDTO>> getStudents() {
        return ResponseEntity.ok().body(studentService.convertToListDTO(studentService.findAllStudents()));
    }

    @PostMapping(value = "/register/student")
    @Operation(summary = "Register a student",description = "Register a student. Important to put all student information (without the id)")
    public ResponseEntity<StudentDTO> saveStudent(@Valid @RequestBody StudentDTO studentDTO) {
        Student student = studentService.saveStudent(studentService.convertToEntity(studentDTO));
            return new ResponseEntity<>(studentService.convertToDTO(student), HttpStatus.CREATED);
        }

}
