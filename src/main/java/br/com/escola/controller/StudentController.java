package br.com.escola.controller;


import br.com.escola.model.Student;
import br.com.escola.service.ServiceStudent;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class StudentController {

    @Autowired
    private ServiceStudent serviceStudent;

    @GetMapping("/student/{id}")
    public Optional<Student> getStudent(@PathVariable Long id) {
        Optional<Student> student = serviceStudent.getStudent(id);
        if(student.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return serviceStudent.getStudent(id) ;
    }

    @PutMapping("update/student")
    public Optional<Student> updateStudent(@RequestBody Student student) {
        Optional<Student> newStudent = serviceStudent.getStudent(student.getId());
        if(newStudent.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return Optional.ofNullable(serviceStudent.updateStudent(student));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return serviceStudent.getStudents();
    }

    @PostMapping(value = "/register/student")
    public ResponseEntity<Map<String, String>> saveStudent(@RequestBody Student student){
        Map<String, String> response = new HashMap<>();
        try{
            serviceStudent.saveStudent(student);
        }catch(Exception erro){
            response.put("message", erro.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("message", "Aluno criado com Sucesso");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
