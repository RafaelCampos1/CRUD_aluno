package br.com.escola.controller;

import br.com.escola.dto.SchoolClassDTO;
import br.com.escola.model.SchoolClass;
import br.com.escola.service.SchoolClassService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
@Slf4j
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("find/school")
    @Operation(summary = "Find a school class by name",description = "Returns a school class by their name")
    public ResponseEntity<SchoolClass> getStudent(@RequestBody SchoolClassDTO schoolClassDTO) {
        return ResponseEntity.ok().body(
                modelMapper.map(
                        schoolClassService.getSchoolByName(schoolClassDTO.getFirstName()), SchoolClass.class));
    }

}
