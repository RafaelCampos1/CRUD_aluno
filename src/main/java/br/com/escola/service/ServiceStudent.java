package br.com.escola.service;

import br.com.escola.model.SchoolClass;
import br.com.escola.repository.StudentRepository;
import br.com.escola.exception.BusinessException;
import br.com.escola.enums.ErrorDescription;
import br.com.escola.model.Student;
import br.com.escola.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
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
        Optional<SchoolClass> schoolClass =  serviceSchoolClass.getSchoolClasses().stream()
                .filter(SC -> SC.getStudentList().size() < 5).findFirst();

        List<Student> listStudent = schoolClass.isPresent() ?
                schoolClass.get().getStudentList()
                : new ArrayList<>();

        List<SchoolClass> schoolClassList = serviceSchoolClass.getSchoolClass();

        if(schoolClass.isEmpty()){
            SchoolClass newSchoolClass = new SchoolClass();
            if(schoolClassList.isEmpty()){
                student.setSchoolClass("Turma A");
                newSchoolClass.setName("Turma A");
            }else{
                String lastWord = String.valueOf(schoolClassList.get(0).getName().charAt(schoolClassList.get(0).getName().length() - 1));
                byte[] bytes = lastWord.getBytes(StandardCharsets.US_ASCII);
                int word = bytes[0]+1;
                lastWord = String.valueOf((char) word);
                newSchoolClass.setName("Turma "+ lastWord);
                student.setSchoolClass(schoolClassList.get(0).getName());
            }

            newSchoolClass.setStudentList(listStudent);
            student.setSchoolClass(newSchoolClass.getName());
            studentRepository.save(student);
            serviceSchoolClass.saveSchoolClass(newSchoolClass);
        }

        if(schoolClass.isPresent())
            student.setSchoolClass(schoolClassList.get(0).getName());
        listStudent.add(student);
        studentRepository.save(student);
        serviceSchoolClass.saveStudentOnExistingClass(listStudent);
        return student;
    }

}
