package br.com.escola.controller;


import br.com.escola.model.Student;
import br.com.escola.service.ServiceStudent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class StudentController {

    @Autowired // deixar por euqnato (parecido com o construtor)
    private ServiceStudent serviceStudent;

    @GetMapping("/students")
    public ResponseEntity<Optional<Student>> getStudent() throws Exception {
        Optional<Student> student1 = serviceStudent.getStudent().stream().parallel().filter(student ->
                student.getId() == 4).findAny();
        return ResponseEntity.ok().body(student1);


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
