package br.com.escola.service;

import br.com.escola.model.SchoolClass;
import br.com.escola.model.Student;
import br.com.escola.repository.SchoolClassRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceSchoolClass {

    private final SchoolClassRepository schoolClassRepository;


    public ServiceSchoolClass(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public List<SchoolClass> getSchoolClass(){
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
