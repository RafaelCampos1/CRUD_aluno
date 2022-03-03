package br.com.escola.controller;


import br.com.escola.model.Student;
import br.com.escola.service.IServiceStudent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j

public class StudentController {

    IServiceStudent iServiceStudent;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudent() throws Exception {
        return ResponseEntity.ok().body(iServiceStudent.getStudent());
    }

    @PostMapping(value = "/register/student")
    //@RequestBody Map<String, String> jsonValues, Aluno aluno,Usuario usuario
    public ResponseEntity<Map<String, String>> saveAluno(@RequestBody Student student){

        Map<String, String> response = new HashMap<>();
        log.info("dsadas1111   "+student);
        log.info("dsadas1111   "+student.getEmail());
        try{
            iServiceStudent.saveStudent(student);
        }catch(Exception erro){
            response.put("message", erro.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        response.put("message", "Aluno criado com Sucesso");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
