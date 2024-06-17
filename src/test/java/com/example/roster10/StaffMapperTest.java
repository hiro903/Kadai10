package com.example.roster10;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = StaffApplication.class)
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StaffMapperTest {

    @Autowired
    private StaffMapper staffMapper;

    @Test
    @DataSet(value = "datasets/staff.yml", cleanBefore = true)
    @ExpectedDataSet("expected_datasets/after_find_all.yml")
    @Transactional
    void ユーザーが取得できること() {
        List<Staff> staffList = staffMapper.findAll();

    }

    @Test
    @DataSet(value = "datasets/staff.yml", cleanBefore = true, executeScriptsBefore = "/sqlannotation/delete-staff.sql")
    @ExpectedDataSet("expected_datasets/after_insert.yml")
    @Transactional
    void ユーザーが挿入できること() {
        Staff newStaff = new Staff(null, "Anna", LocalDate.of(2001, 1, 1), "Tokyo");
        staffMapper.insert(newStaff);

        assertNotNull(newStaff.getId());

    }

    @Test
    @DataSet(value = "datasets/staff.yml", cleanBefore = true)
    @ExpectedDataSet("expected_datasets/after_update.yml")
    @Transactional
    void ユーザーが更新できること() {
        Staff updatedStaff = new Staff(1, "Anna", LocalDate.of(2001, 1, 1), "Tokyo");
        staffMapper.updateStaff(updatedStaff);

    }

    @Test
    @DataSet(value = {"datasets/staff.yml"}, cleanBefore = true)
    @ExpectedDataSet("expected_datasets/after_delete.yml")
    @Transactional
    void ユーザーが削除できること() {
        staffMapper.deleteById(1);

    }
}
