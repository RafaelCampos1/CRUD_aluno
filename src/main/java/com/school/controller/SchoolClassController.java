package com.school.controller;

import com.school.dto.SchoolClassDTO;
import com.school.service.SchoolClassService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @GetMapping("find/school/{name}")
    @Operation(summary = "Find a school class by name",description = "Returns a school class by their name")
    public ResponseEntity<SchoolClassDTO> getSchoolClass(@PathVariable String name) {
        return ResponseEntity.ok().body(schoolClassService.convertToDTO(schoolClassService.findSchoolByName(name)));
    }

}
