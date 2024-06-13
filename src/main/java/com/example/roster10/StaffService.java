package com.example.roster10;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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

    public Staff findStaff(int id) {
        Optional<Staff> staff = this.staffMapper.findById(id);
        return staff.orElseThrow(() -> new StaffNotFoundException("Staff with id " + id + " not found"));
    }

    public void updateStaff(int id, String name, LocalDate dateOfBirth, String nearestStation) {
        // まず、スタッフを検索する
        Optional<Staff> staffOptional = staffMapper.findById(id);
        // スタッフが見つからない場合は例外をスローする
        Staff existingStaff = staffOptional.orElseThrow(() -> new StaffNotFoundException("Staff with id " + id + " not found"));

        // リクエストで送られてきた値が null でない場合にのみ更新する
        if (Objects.nonNull(name)) {
            existingStaff.setName(name);
        }
        if (Objects.nonNull(dateOfBirth)) {
            existingStaff.setDateOfBirth(dateOfBirth);
        }
        if (Objects.nonNull(nearestStation)) {
            existingStaff.setNearestStation(nearestStation);
        }
        // 更新をデータベースに反映する
        staffMapper.updateStaff(existingStaff);
    }

    public void deleteStaffById(int id) {
        Optional<Staff> staffOptional = staffMapper.findById(id);
        Staff deleteStaff = staffOptional.orElseThrow(() -> new StaffNotFoundException("Staff with id " + id + " not found"));
        staffMapper.deleteById(deleteStaff);

    }

    public List<Staff> findAll() {
        return this.staffMapper.findAll();
    }
}
