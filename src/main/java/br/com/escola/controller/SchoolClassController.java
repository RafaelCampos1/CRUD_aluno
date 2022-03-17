package br.com.escola.controller;

import br.com.escola.dto.SchoolClassDTO;
import br.com.escola.service.SchoolClassService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @GetMapping("find/school")
    @Operation(summary = "Find a school class by name",description = "Returns a school class by their name")
    public ResponseEntity<SchoolClassDTO> getSchoolClass(@RequestParam String name) {
        return ResponseEntity.ok().body(schoolClassService.convertToDTO(schoolClassService.findSchoolByName(name)));
    }

}
