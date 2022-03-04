package br.com.escola.controller;


import br.com.escola.enums.ErrorDescription;
import br.com.escola.exception.BusinessException;
import br.com.escola.model.Student;
import br.com.escola.service.IServiceStudent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired // deixar por euqnato (parecido com o construtor)
    private IServiceStudent iServiceStudent;




    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudent() throws Exception {
        return ResponseEntity.ok().body(iServiceStudent.getStudent());
    }

    @PostMapping(value = "/register/student")
    public ResponseEntity<Map<String, String>> saveStudent(@RequestBody Student student){
        Map<String, String> response = new HashMap<>();
        try{
            iServiceStudent.saveStudent(student);
        }catch(Exception erro){
            response.put("message", erro.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("message", "Aluno criado com Sucesso");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
