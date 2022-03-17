package br.com.escola.controller;

import br.com.escola.dto.StudentDTO;
import br.com.escola.model.Student;
import br.com.escola.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{id}")
    @Operation(summary = "Find a student",description = "Returns a student by their id")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok().body(studentService.convertToDTO(studentService.findById(id)));
    }

    @PutMapping("update/student/{id}")
    @Operation(summary = "Update a student by their id",description = "Update a student by their id (important to put all student information)")
    public ResponseEntity<StudentDTO> updateStudent(@Valid @RequestBody StudentDTO studentDTO, @PathVariable Long id) {
        Student student = studentService.convertToEntity(studentDTO);
        student.setId(id);
        return ResponseEntity.ok().body(studentService.convertToDTO(studentService.updateStudent(student)));
    }

    @GetMapping("/students")
    @Operation(summary = "Find all students",description = "Returns all information for each student that is registered in the database")
    public ResponseEntity<List<StudentDTO>> getStudents() {
        return ResponseEntity.ok().body(studentService.convertToListDTO(studentService.findAllStudents()));
    }

    @PostMapping(value = "/register/student")
    @Operation(summary = "Register a student",description = "Register a student. Important to put all student information (without the id)")
    public ResponseEntity<StudentDTO> saveStudent(@Valid @RequestBody StudentDTO studentDTO) {
        if(studentDTO == null)
            log.info("qqqqqqq");
        Student student = studentService.saveStudent(studentService.convertToEntity(studentDTO));
            return new ResponseEntity<>(studentService.convertToDTO(student), HttpStatus.CREATED);
        }

}
