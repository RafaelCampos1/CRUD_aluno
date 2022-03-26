package com.school.repository;

import com.school.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByCpf(String cpf);
    Student findByEmail(String email);

}
