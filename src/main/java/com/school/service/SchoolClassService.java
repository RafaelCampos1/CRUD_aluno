package com.school.service;

import com.school.dto.SchoolClassDTO;
import com.school.enums.ErrorDescription;
import com.school.exceptionhandler.NotFoundException;
import com.school.model.SchoolClass;
import com.school.model.Student;
import com.school.repository.SchoolClassRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolClassService {


    private final SchoolClassRepository schoolClassRepository;

    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public SchoolClass convertToEntity(SchoolClassDTO schoolClassDTO){
        return new ModelMapper().map(schoolClassDTO,SchoolClass.class);
    }
    public SchoolClassDTO convertToDTO(SchoolClass schoolClass){
        return new ModelMapper().map(schoolClass,SchoolClassDTO.class);
    }
    public List<SchoolClassDTO> convertToListDTO(List<SchoolClass> schoolClassList){
        return new ModelMapper().map(schoolClassList, new TypeToken<List<SchoolClassDTO>>() {}.getType());
    }

    public SchoolClass findSchoolByName(String name) {
        return Optional.of(schoolClassRepository.findBySchoolClassName(name))
                .orElseThrow(()-> new NotFoundException(ErrorDescription.SCHOOL_CLASS_NOT_FOUND));
    }

    public String findSchoolClassNameInDESC(){
        return schoolClassRepository.findAll(Sort.by(Sort.Direction.DESC, "schoolClassName")).isEmpty() ? "Turma @" :
                schoolClassRepository.findAll(Sort.by(Sort.Direction.DESC, "schoolClassName")).get(0).getSchoolClassName();
    }

    public List<SchoolClass> findAllSchoolClasses(){
        return schoolClassRepository.findAll();
    }

    public SchoolClass saveSchoolClass(SchoolClass schoolClass){
        return schoolClassRepository.save(schoolClass);
    }

}
