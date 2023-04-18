package com.IMDdatabase.IMSWithDatabase.Repository;

import com.IMDdatabase.IMSWithDatabase.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Integer> {
}
