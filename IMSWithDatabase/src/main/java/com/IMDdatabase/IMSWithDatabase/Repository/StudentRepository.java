package com.IMDdatabase.IMSWithDatabase.Repository;

import com.IMDdatabase.IMSWithDatabase.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
