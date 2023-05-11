package com.IMDdatabase.IMSWithDatabase.service;

import com.IMDdatabase.IMSWithDatabase.Repository.StaffRepository;
import com.IMDdatabase.IMSWithDatabase.model.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;

    public Optional <Staff> getSpecificStaff(int id ){
        logger.info("Get staff with id: " + id);
        return staffRepository.findById(id);
    }
    public List<Staff> getStaffInfo(){
        logger.info("Get all staff" );
        return staffRepository.findAll();
    }
    public Staff hireStaff(Staff staff ){
        staffRepository.save(staff);
        logger.info("Register staff with id: " + staff.id);
        return staff;
    }

    public  Optional<Staff> fireStaff(int id){
        Optional<Staff> staff = getSpecificStaff(id);
       staffRepository.deleteById(id);
        logger.info("Delete staff with id: " + id);
        return staff;
    }

    public Optional<Staff> updateStaffInfo(int id, Staff upToDateStaff){
        Optional<Staff> foundStaff = getSpecificStaff(id);
        foundStaff.ifPresent(
                (currentStaff) -> {
                    currentStaff.name = upToDateStaff.name;
                    currentStaff.email = upToDateStaff.email;
                    currentStaff.salary = upToDateStaff.salary;

                    staffRepository.save(currentStaff);
                }
        );
        logger.info("updated staff with id: " + id);
        return foundStaff;
    }

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

}
