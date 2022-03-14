package br.com.escola.repository;

import br.com.escola.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByCpf(String cpf);
    Student findByEmail(String email);

}
