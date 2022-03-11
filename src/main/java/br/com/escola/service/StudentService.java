package br.com.escola.service;

import br.com.escola.enums.ErrorDescription;
import br.com.escola.exception.BusinessException;
import br.com.escola.model.SchoolClass;
import br.com.escola.model.Student;
import br.com.escola.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SchoolClassService schoolClassService;

    public StudentService(StudentRepository studentRepository, SchoolClassService schoolClassService) {
        this.studentRepository = studentRepository;
        this.schoolClassService = schoolClassService;
    }

    public String getStudentByCpf(String cpf) {
        return (studentRepository.findByCpf(cpf)==null) ?
                null : String.valueOf(studentRepository.findByCpf(cpf).getCpf());
    }

    public String getStudentByEmail(String email) {
        return (studentRepository.findByEmail(email)==null) ?
                null : String.valueOf(studentRepository.findByEmail(email).getEmail());
    }

    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student,Long id){
        return getStudent(id).isPresent() ?
            studentRepository.save(student) : null;
    }

    public Student saveStudent(Student student) {
        validateStudent(student);

        Optional<SchoolClass> schoolClass =  schoolClassService.getSchoolClasses().stream()
                .filter(SC -> SC.getStudentList().size() < 5).findFirst();

        List<Student> listStudent = schoolClass.isPresent() ?
                schoolClass.get().getStudentList() : new ArrayList<>();

        List<SchoolClass> schoolClassList = schoolClassService.getSchoolClassName();

        if(schoolClass.isEmpty()) {
            SchoolClass newSchoolClass = new SchoolClass();
            if(schoolClassList.isEmpty()){
                student.setSchoolClass("Turma A");
                newSchoolClass.setName("Turma A");
            } else {
                //TODO generateClassName(): FAZER ESSA METODO COLOCAR TUDO DENTRO
                String lastWord = String.valueOf(schoolClassList.get(0).getName().charAt(schoolClassList.get(0).getName().length() - 1));
                byte[] bytes = lastWord.getBytes(StandardCharsets.US_ASCII); // A = 63
                int word = bytes[0]+1;//
                lastWord = String.valueOf((char) word);
                newSchoolClass.setName("Turma "+ lastWord);
                student.setSchoolClass(schoolClassList.get(0).getName());
            }

            newSchoolClass.setStudentList(listStudent);
            student.setSchoolClass(newSchoolClass.getName());
            studentRepository.save(student);
            schoolClassService.saveSchoolClass(newSchoolClass);
        }

        if(schoolClass.isPresent())
            student.setSchoolClass(schoolClassList.get(0).getName());
        listStudent.add(student);
        studentRepository.save(student);
        schoolClassService.saveStudentOnExistingClass(listStudent);
        return student;
    }

    public Student validateStudent(Student student){
        String studentCpf = getStudentByCpf(student.getCpf());
        if(student.getCpf().equals(studentCpf))
            throw new BusinessException(ErrorDescription.SAME_CPF);

        String studentEmail = getStudentByEmail(student.getEmail());
        if(student.getEmail().equals(studentEmail))
            throw new BusinessException(ErrorDescription.SAME_EMAIL);
        return student;
    }

}
