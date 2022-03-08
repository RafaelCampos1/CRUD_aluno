package br.com.escola.service;

import br.com.escola.model.SchoolClass;
import br.com.escola.repository.StudentRepository;
import br.com.escola.exception.BusinessException;
import br.com.escola.enums.ErrorDescription;
import br.com.escola.model.Student;
import br.com.escola.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ServiceStudent {

    private final StudentRepository studentRepository;
    private final ServiceSchoolClass serviceSchoolClass;

    public ServiceStudent(StudentRepository studentRepository, ServiceSchoolClass serviceSchoolClass) {
        this.studentRepository = studentRepository;
        this.serviceSchoolClass = serviceSchoolClass;
    }

    public Optional<Student> getStudent(Long id){
        return studentRepository.findById(id);
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student){
        if(!Validator.isCPF(student.getRealID())){
            throw new BusinessException(ErrorDescription.INVALID_REALID);
        }
        return studentRepository.save(student);
    }


    public Student saveStudent(Student student){
        if(!Validator.isCPF(student.getRealID())){
            throw new BusinessException(ErrorDescription.INVALID_REALID);
        }
        Optional<SchoolClass> schoolClass =  serviceSchoolClass.getSchoolClass().stream()
                .filter(SC -> SC.getStudentList().size() < 5).findFirst();

        List<Student> listStudent = schoolClass.isPresent() ?
                schoolClass.get().getStudentList()
                : new ArrayList<>();

        listStudent.add(student);
        studentRepository.save(student);

        if(schoolClass.isPresent()){
           serviceSchoolClass.saveStudentOnExistingClass(listStudent);
        }
        else{
            SchoolClass newSchoolClass = new SchoolClass();
            newSchoolClass.setName("Turma ");
            newSchoolClass.setStudentList(listStudent);
            serviceSchoolClass.saveSchoolClass(newSchoolClass);
        }

        return student;
    }

}
