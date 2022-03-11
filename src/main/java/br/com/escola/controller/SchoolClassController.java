package br.com.escola.controller;

import br.com.escola.dto.SchoolClassDTO;
import br.com.escola.model.SchoolClass;
import br.com.escola.service.SchoolClassService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")//TODO VER ISSO
@Slf4j
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("find/school")
    @Operation(summary = "Find a school class by name",description = "Returns a school class by their name")
    public ResponseEntity getSchoolClass(SchoolClassDTO schoolClassDTO) {
        log.info(schoolClassDTO.getFirstName());
        return schoolClassService.getSchoolByName(schoolClassDTO.getFirstName()).isPresent()?
                ResponseEntity.ok().body(modelMapper.map(schoolClassService.getSchoolByName(schoolClassDTO.getFirstName()), SchoolClass.class))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("School Class not found");
    }

}
