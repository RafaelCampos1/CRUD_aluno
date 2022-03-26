package com.school.repository;

import com.school.model.SchoolClass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends MongoRepository<SchoolClass,String> {
    SchoolClass findBySchoolClassName(String schoolClassName);
}
