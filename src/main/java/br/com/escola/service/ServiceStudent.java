package br.com.escola.service;

import br.com.escola.dao.IDaoStudent;
import br.com.escola.model.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service// Service
@Slf4j
//@RequiredArgsConstructor anotacao para nao precisar do construtor
public class ServiceStudent implements IServiceStudent {

    private final IDaoStudent iDaoStudent;

    public ServiceStudent(IDaoStudent iDaoStudent) {
        this.iDaoStudent = iDaoStudent;

    }

    @Override
    public List<Student> getStudent(){

        return iDaoStudent.findAll();
    }

    @Override
    public Student saveStudent(Student student){
        return iDaoStudent.save(student);
    }
}
