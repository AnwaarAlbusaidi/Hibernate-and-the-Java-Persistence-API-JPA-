package com.IMDdatabase.IMSWithDatabase.service;

import com.IMDdatabase.IMSWithDatabase.Repository.StaffRepository;
import com.IMDdatabase.IMSWithDatabase.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;

    public Optional <Staff> getSpecificStaff(int id ){
        return staffRepository.findById(id);
    }
    public List<Staff> getStaffInfo(){

        return staffRepository.findAll();
    }
    public Staff hireStaff(Staff teacher ){
        staffRepository.save(teacher);
        return teacher;
    }

    public  Optional<Staff> fireStaff(int id){
        Optional<Staff> staff = getSpecificStaff(id);
       staffRepository.deleteById(id);
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
        return foundStaff;
    }
}
