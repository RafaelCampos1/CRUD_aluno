package com.school.repository;

import com.school.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass,Long> {
    SchoolClass findBySchoolClassName(String schoolClassName);
}
