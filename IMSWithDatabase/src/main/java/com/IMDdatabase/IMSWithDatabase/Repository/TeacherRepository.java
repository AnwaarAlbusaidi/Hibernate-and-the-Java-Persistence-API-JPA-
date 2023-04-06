package com.IMDdatabase.IMSWithDatabase.Repository;

import com.IMDdatabase.IMSWithDatabase.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
