package com.example.roster10.service;

import com.example.roster10.Staff;
import com.example.roster10.StaffApplication;
import com.example.roster10.StaffMapper;
import com.example.roster10.StaffService;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = StaffApplication.class)
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StaffServiceIntegrationTest {

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffMapper staffMapper;

    @Test
    @DataSet("datasets/staff.yml")
    @Transactional
    void 挿入と検索のテスト() {
        Staff staff = staffService.insert("chika", LocalDate.of(2000, 7, 1), "Tokyo");
        Staff foundStaff = staffService.findStaff(staff.getId());

        assertNotNull(foundStaff);
        assertEquals("chika", foundStaff.getName());
    }

    @Test
    @DataSet("datasets/staff.yml")
    @Transactional
    void スタッフ更新テスト() {
        Staff staff = staffService.insert("chika", LocalDate.of(2000, 7, 1), "Tokyo");
        staffService.updateStaff(staff.getId(), "Anna", null, null);

        Staff updatedStaff = staffService.findStaff(staff.getId());
        assertEquals("Anna", updatedStaff.getName());
    }

    @Test
    @DataSet("datasets/staff.yml")
    @Transactional
    void 全スタッフ検索テスト() {
        staffService.insert("chika", LocalDate.of(2000, 7, 1), "Tokyo");
        staffService.insert("Ann", LocalDate.of(2000, 2, 2), "Shinagawa");

        List<Staff> staffList = staffService.findAll();
        assertEquals(5, staffList.size());
    }
}
