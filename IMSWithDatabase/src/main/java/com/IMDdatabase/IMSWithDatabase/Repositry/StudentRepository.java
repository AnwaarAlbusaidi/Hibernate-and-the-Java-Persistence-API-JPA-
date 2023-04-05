package com.IMDdatabase.IMSWithDatabase.Repositry;

import com.IMDdatabase.IMSWithDatabase.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
