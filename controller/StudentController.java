package br.com.escola.controller;

import br.com.escola.dto.StudentDTO;
import br.com.escola.exception.BusinessException;
import br.com.escola.model.Student;
import br.com.escola.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/student/{id}")
    @Operation(summary = "Find a student",description = "Returns a student by their id")
    public ResponseEntity getStudent(@PathVariable Long id) {
        return studentService.getStudent(id).isPresent() ?
                ResponseEntity.ok().body(modelMapper.map(studentService.getStudent(id),StudentDTO.class))
                :
                ResponseEntity.status(HttpStatus.FORBIDDEN).body("Student not found");
    }

    @PutMapping("update/student/{id}")
    @Operation(summary = "Update a student by their id",description = "Update a student by their id (important to put all student information)")
    public ResponseEntity updateStudent(@RequestBody Student student, @PathVariable Long id) {
        student.setId(id);
        return studentService.getStudent(id).isPresent() ?
                ResponseEntity.ok().body(modelMapper.map(studentService.updateStudent(student),StudentDTO.class))
                :
                ResponseEntity.status(HttpStatus.FORBIDDEN).body("Student not found");
    }

    @GetMapping("/students")
    @Operation(summary = "Find all students",description = "Returns all information for each student that is registered in the database")
    public ResponseEntity getStudents() {
        return studentService.getStudents().size() > 0 ?
                ResponseEntity.ok().body(modelMapper.map(studentService.getStudents(), new TypeToken<List<StudentDTO>>() {}.getType()))
                :
                ResponseEntity.status(HttpStatus.FORBIDDEN).body("We dont have students yet");
    }

    @PostMapping(value = "/register/student")
    @Operation(summary = "Register a student",description = "Register a student. Important to put all student information (without the id)")
    public ResponseEntity saveStudent(@RequestBody Student student) {
        try{
            return ResponseEntity.ok().body(modelMapper.map(studentService.saveStudent(student), StudentDTO.class));
        }catch(BusinessException exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

}
