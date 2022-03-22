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
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;
    private final SchoolClassService schoolClassService;

    public StudentService(StudentRepository studentRepository, SchoolClassService schoolClassService, ModelMapper modelMapper) {
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

    public Student findById(Long id) {
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
        validateStudentOnSave(student);

        Optional<SchoolClass> schoolClassLessFive = schoolClassService.findAllSchoolClasses().stream()
                .filter(SC -> SC.getStudentList().size() < 5).findFirst();
            log.info("dsadsadsadsa");
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
        String schoolClassNameInDESC = schoolClassService.findSchoolClassNameInDESC();
        String lastWord = String.valueOf(schoolClassNameInDESC.charAt(schoolClassNameInDESC.length() - 1));
        byte[] bytes = lastWord.getBytes(StandardCharsets.US_ASCII);
        int word = bytes[0] + 1;
        lastWord = String.valueOf((char) word);
        return "School Class "+lastWord;
    }

}
