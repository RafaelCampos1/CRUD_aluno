package br.com.escola.repository;

import br.com.escola.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass,Long> {
    Optional<SchoolClass> findBySchoolClassName(String name);
}
