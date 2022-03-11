package br.com.escola.service;

import br.com.escola.model.Student;
import br.com.escola.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class StudentServiceTest {

    public static final String RAFAEL       = "rafael";
    public static final String CAMPOS       = "campos";
    public static final String HOTMAIL      = "rafael@hotmail.com";
    public static final String REAL_ID      = "123";
    public static final String REGISTRATION = "111";
    public static final String SCHOOL_CLASS = "Turma A";
    public static final long ID             = 1L;
    private Student student;
    private Optional<Student> studentOptional;

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startStundet();
    }

    @Test
    void getStudentByRealId() {
    }

    @Test
    void getStudentByEmail() {
    }

    @Test
    void whenFindByIdReturnTheStudent() {
        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(studentOptional);
        Optional<Student> response = studentService.getStudent(ID);

        // do jupiter== assegure p mim q ambos sao iguauis, primeiro e oq eu espero receber, e o segundo e oq retornou
        Assertions.assertEquals(Optional.class, response.getClass());
    }

    @Test
    void getStudents() {
    }

    @Test
    void validateStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void saveStudent() {
    }

    private void startStundet(){
      //  student = new Student(ID, RAFAEL, CAMPOS, HOTMAIL, REAL_ID, REGISTRATION, SCHOOL_CLASS);
        // optional de novo estudante
     //   studentOptional = Optional.of(new Student(ID, RAFAEL, CAMPOS, HOTMAIL, REAL_ID, REGISTRATION, SCHOOL_CLASS));
    }
}