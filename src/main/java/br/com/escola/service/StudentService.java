package br.com.escola.service;

import br.com.escola.dto.StudentDTO;
import br.com.escola.enums.ErrorDescription;
import br.com.escola.exceptionhandler.ConflictException;
import br.com.escola.exceptionhandler.NotFoundException;
import br.com.escola.model.SchoolClass;
import br.com.escola.model.Student;
import br.com.escola.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;
    private final SchoolClassService schoolClassService;
    @Autowired
    private ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, SchoolClassService schoolClassService) {
        this.studentRepository = studentRepository;
        this.schoolClassService = schoolClassService;
    }

    public StudentDTO convertToDTO(Student student){
        return modelMapper.map(student,StudentDTO.class);
    }

    public List<StudentDTO> convertToListDTO(List<Student> studentList){
        return modelMapper.map(studentList, new TypeToken<List<StudentDTO>>() {}.getType());
    }

    public Student convertToEntity(StudentDTO studentDTO){
        return modelMapper.map(studentDTO,Student.class);
    }

    public void findStudentByCpf(String cpf) {
        if (studentRepository.findByCpf(cpf) != null)
            throw new ConflictException(ErrorDescription.SAME_CPF);
    }

    public void findStudentByEmail(String email) {
        if (studentRepository.findByEmail(email) != null)
            throw new ConflictException(ErrorDescription.SAME_EMAIL);
    }

    public Student findById(Long id) {
        log.info("dsadsadsa");
        return studentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(ErrorDescription.STUDENT_NOT_FOUND));
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student) {
        student.setSchoolClass(findById(student.getId()).getSchoolClass());
        return studentRepository.save(student);
    }

    public Student saveStudent(Student student) {
       validateStudent(student);
        Optional<SchoolClass> schoolClassLessFive = schoolClassService.findAllSchoolClasses().stream()
                .filter(SC -> SC.getStudentList().size() < 5).findFirst();

        List<Student> listStudent = schoolClassLessFive.isPresent() ?
                schoolClassLessFive.get().getStudentList() : new ArrayList<>();

        if (schoolClassLessFive.isEmpty() ) {
            SchoolClass newSchoolClass = new SchoolClass();
            newSchoolClass.setSchoolClassName(getNewSchoolClassName());
            newSchoolClass.setStudentList( listStudent);
            student.setSchoolClass(schoolClassService.findSchoolClassNameInDESC());
            student.setSchoolClass(newSchoolClass.getSchoolClassName());
            studentRepository.save(student);
            schoolClassService.saveSchoolClass(newSchoolClass);

        }else {
            student.setSchoolClass(schoolClassLessFive.get().getSchoolClassName());
        }
        listStudent.add(student);
        studentRepository.save(student);
        schoolClassService.saveStudentOnExistingClass(listStudent);
        return student;
    }

    public void validateStudent(Student student){
        findStudentByCpf(student.getCpf());
        findStudentByEmail(student.getEmail());
    }

    public String getNewSchoolClassName(){
        String schoolClassNameInDESC = schoolClassService.findSchoolClassNameInDESC();
        String lastWord = String.valueOf(schoolClassNameInDESC.charAt(schoolClassNameInDESC.length() - 1));
        byte[] bytes = lastWord.getBytes(StandardCharsets.US_ASCII);
        int word = bytes[0] + 1;
        lastWord = String.valueOf((char) word);
        return "School Class "+lastWord;
    }

}
