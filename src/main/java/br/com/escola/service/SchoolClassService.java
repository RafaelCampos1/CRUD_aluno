package br.com.escola.service;

import br.com.escola.dto.SchoolClassDTO;
import br.com.escola.enums.ErrorDescription;
import br.com.escola.exceptionhandler.NotFoundException;
import br.com.escola.model.SchoolClass;
import br.com.escola.model.Student;
import br.com.escola.repository.SchoolClassRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;
    @Autowired
    private ModelMapper modelMapper;



    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public SchoolClass convertToEntity(SchoolClassDTO schoolClassDTO){
        return modelMapper.map(schoolClassDTO,SchoolClass.class);
    }
    public SchoolClassDTO convertToDTO(SchoolClass schoolClass){
        return modelMapper.map(schoolClass,SchoolClassDTO.class);
    }
    public List<SchoolClassDTO> convertToListDTO(List<SchoolClass> schoolClassList){
        return modelMapper.map(schoolClassList, new TypeToken<List<SchoolClassDTO>>() {}.getType());
    }

    public SchoolClass findSchoolByName(String name) {
        return schoolClassRepository.findByName(name)
                .orElseThrow(()-> new NotFoundException(ErrorDescription.SCHOOL_CLASS_NOT_FOUND));
    }

    public String findSchoolClassNameInDESC(){
        return schoolClassRepository.findAll(Sort.by(Sort.Direction.DESC, "name")).isEmpty() ? "Turma @" :
                schoolClassRepository.findAll(Sort.by(Sort.Direction.DESC, "name")).get(0).getName();
    }

    public List<SchoolClass> findAllSchoolClasses(){
        return schoolClassRepository.findAll();
    }

    public SchoolClass saveSchoolClass(SchoolClass schoolClass){
        return schoolClassRepository.save(schoolClass);
    }

    public void saveStudentOnExistingClass(List<Student> studentList){
        studentList.add(studentList.get(0));
    }
}
