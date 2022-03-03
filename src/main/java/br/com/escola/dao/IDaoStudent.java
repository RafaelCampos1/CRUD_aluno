package br.com.escola.dao;

import br.com.escola.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDaoStudent extends JpaRepository<Student, Long> {

}
