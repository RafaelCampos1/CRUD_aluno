package com.school.service;

import com.school.dto.StudentDTO;
import com.school.enums.ErrorDescription;
import com.school.exceptionhandler.ConflictException;
import com.school.exceptionhandler.NotFoundException;
import com.school.model.SchoolClass;
import com.school.model.Student;
import com.school.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;
    private final SchoolClassService schoolClassService;

    public StudentService(StudentRepository studentRepository, SchoolClassService schoolClassService) {
        this.studentRepository = studentRepository;
        this.schoolClassService = schoolClassService;
    }

    public StudentDTO convertToDTO(Student student){
        return new ModelMapper().map(student,StudentDTO.class);
    }

    public List<StudentDTO> convertToListDTO(List<Student> studentList){
        return new ModelMapper().map(studentList, new TypeToken<List<StudentDTO>>() {}.getType());
    }

    public Student convertToEntity(StudentDTO studentDTO){
        return new ModelMapper().map(studentDTO,Student.class);
    }

    public void findStudentByCpf(Student student) {
        if (studentRepository.findByCpf(student.getCpf()) != null)
            throw new ConflictException(ErrorDescription.SAME_CPF);
    }

    public void findStudentByEmail(Student student) {
        if (studentRepository.findByEmail(student.getEmail()) != null)
            throw new ConflictException(ErrorDescription.SAME_EMAIL);
    }

    public Student findById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(ErrorDescription.STUDENT_NOT_FOUND));
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student) {
        validateStudentOnUpdate(student);
        student.setSchoolClass(findById(student.getId()).getSchoolClass());
        return studentRepository.save(student);
    }

    public Student saveStudent(Student student) {

       //validateStudentOnSave(student);
        Optional<SchoolClass> schoolClassLessFive = schoolClassService.findAllSchoolClasses().stream()
                .filter(schoolClass -> schoolClass.getStudentList().size() < 5).findFirst();

        if (schoolClassLessFive.isEmpty() ) {
            SchoolClass newSchoolClass = new SchoolClass();
            newSchoolClass.setSchoolClassName(getNewSchoolClassName());
            newSchoolClass.getStudentList().add(student);
            student.setSchoolClass(newSchoolClass.getSchoolClassName());
            schoolClassService.saveSchoolClass(newSchoolClass);
            return studentRepository.save(student);
        }
        student.setSchoolClass(schoolClassLessFive.get().getSchoolClassName());
        schoolClassLessFive.get().getStudentList().add(student);
        return studentRepository.save(student);
    }

    public void validateStudentOnUpdate(Student student){
        Student studentWithSameCpf = studentRepository.findByCpf(student.getCpf());
        if(studentWithSameCpf!=null && !studentWithSameCpf.getId().equals(student.getId()) )
            findStudentByCpf(student);

        Student studentWithSameEmail = studentRepository.findByEmail(student.getEmail());
        if(studentWithSameEmail!=null && !studentWithSameEmail.getId().equals(student.getId()) )
            findStudentByEmail(student);
    }

    public void validateStudentOnSave(Student student){
        findStudentByCpf(student);
        findStudentByEmail(student);
    }

    public String getNewSchoolClassName(){
        List<SchoolClass> schoolClassList = schoolClassService.findAllSchoolClasses();
        if(schoolClassList.isEmpty()){
            return "School Class A";
        }
        String schoolClassNameInDESC = schoolClassList.get(schoolClassList.size()-1).getSchoolClassName();
        String lastWord = String.valueOf(schoolClassNameInDESC.charAt(schoolClassNameInDESC.length() - 1));
        byte[] bytes = lastWord.getBytes(StandardCharsets.US_ASCII);
        int word = bytes[0] + 1;
        lastWord = String.valueOf((char) word);
        return "School Class "+lastWord;
    }

}
