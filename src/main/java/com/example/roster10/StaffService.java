package com.example.roster10;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffMapper staffMapper;

    public StaffService(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    public Staff insert(String name, LocalDate dateOfBirth, String nearestStation) {
        Staff staff = Staff.createStaff(name, dateOfBirth, nearestStation);
        staffMapper.insert(staff);
        return staff;
    }
}
