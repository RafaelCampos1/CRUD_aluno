package br.com.escola.service;

import br.com.escola.model.SchoolClass;
import br.com.escola.model.Student;
import br.com.escola.repository.SchoolClassRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<SchoolClass> getSchoolByName(String name) {
        return schoolClassRepository.findByName(name);
    }
    public List<SchoolClass> getSchoolClassName(){
        return schoolClassRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    public List<SchoolClass> getSchoolClasses(){
        return schoolClassRepository.findAll();
    }

    public SchoolClass saveSchoolClass(SchoolClass schoolClass){
        return schoolClassRepository.save(schoolClass);
    }

    public void saveStudentOnExistingClass(List<Student> studentList){
        studentList.add(studentList.get(0));
    }

}
