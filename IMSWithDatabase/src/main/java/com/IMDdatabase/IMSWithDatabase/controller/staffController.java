package com.IMDdatabase.IMSWithDatabase.controller;


import com.IMDdatabase.IMSWithDatabase.model.Staff;
import com.IMDdatabase.IMSWithDatabase.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class staffController {
    @Autowired
    StaffService staffService;

    @GetMapping
    public List<Staff> getStaff(){
        return staffService.getStaffInfo();
    }

    @GetMapping(path="/{id}")
    public  Optional<Staff> getStaffById(@PathVariable int id){
        return staffService.getSpecificStaff(id);
    }
    @PostMapping
    public Staff registerStaff(@RequestBody Staff staff){
       staffService.hireStaff(staff);
       return staff;
    }

    @PutMapping(path="/{id}")
    public  Optional<Staff> updateStaff(@PathVariable int id, @RequestBody Staff staff){
        return staffService.updateStaffInfo(id,staff);
    }
    @DeleteMapping(path="/{id}")
    public Optional<Staff> DeleteStaff(@PathVariable int id){
        return staffService.fireStaff(id);
    }
}
