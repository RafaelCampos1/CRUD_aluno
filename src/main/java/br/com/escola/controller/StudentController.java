package br.com.escola.controller;

import br.com.escola.model.Student;
import br.com.escola.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{id}")
    @Operation(summary = "Find a student",description = "Returns a student by their id")
    public Optional<Student> getStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudent(id);
        if(student.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return studentService.getStudent(id);
    }

    @PutMapping("update/student")
    @Operation(summary = "Update a student",description = "Update a student by their id (important to put all student information)")
    public Optional<Student> updateStudent(@RequestBody Student student) {
        Optional<Student> newStudent = studentService.getStudent(student.getId());
        if(newStudent.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return Optional.ofNullable(studentService.updateStudent(student));
    }

    @GetMapping("/students")
    @Operation(summary = "Find all students",description = "Returns all information for each student that is registered in the database")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping(value = "/register/student")
    @Operation(summary = "Register a student",description = "Register a student. Important to put all student information (without the id)")
    public ResponseEntity<Map<String, String>> saveStudent(@RequestBody Student student){
        Map<String, String> response = new HashMap<>();
        try{
            studentService.saveStudent(student);
        }catch(Exception erro){
            response.put("message", erro.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("message", "Student created successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
