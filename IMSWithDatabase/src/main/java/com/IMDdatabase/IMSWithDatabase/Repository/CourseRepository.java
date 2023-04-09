package com.IMDdatabase.IMSWithDatabase.Repository;

import com.IMDdatabase.IMSWithDatabase.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
}
