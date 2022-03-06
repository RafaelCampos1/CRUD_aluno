package br.com.escola.service;

import br.com.escola.model.SchoolClass;
import br.com.escola.repository.SchoolClassRepository;
import br.com.escola.repository.StudentRepository;
import br.com.escola.exception.BusinessException;
import br.com.escola.enums.ErrorDescription;
import br.com.escola.model.Student;
import br.com.escola.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service// Service
@Slf4j
public class ServiceStudent {

    private final StudentRepository studentRepository;
    private final ServiceSchoolClass serviceSchoolClass;

    public ServiceStudent(StudentRepository studentRepository, ServiceSchoolClass serviceSchoolClass) {
        this.studentRepository = studentRepository;
        this.serviceSchoolClass = serviceSchoolClass;
    }


    public List<Student> getStudent(){

        return studentRepository.findAll();
    }


    public Student saveStudent(Student student){
        if(!Validator.isCPF(student.getRealID())){
            throw new BusinessException(ErrorDescription.INVALID_REALID);
        }

        Optional<SchoolClass> schoolClass =  serviceSchoolClass.getSchoolClass().stream().parallel().filter(SC ->
                SC.getStudentList().size() < 2 ).findAny();

        if(schoolClass.isEmpty()){
            log.info("esta vazio");
        }
        //Boolean bool = schoolClass.isEmpty() ? 1 : 0;

        //tentar usar colection
        log.info(schoolClass.toString());
        ArrayList<Student> listStudent = new ArrayList<>();



        listStudent.add(student);
        studentRepository.save(student);



        log.info("antes do create");
        serviceSchoolClass.saveSchoolClass(new SchoolClass("turma ",listStudent));
        log.info("dps do create");
        return student;
    }

}
