package com.example.roster10;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StaffMapperTest {

    @Autowired
    private StaffMapper staffMapper;

    @Test
    @DataSet(value = {"datasets/staff.yml"}, cleanBefore = true)
    @Transactional
    void ユーザーが取得できること() {
        List<Staff> staffList = staffMapper.findAll();

        List<Staff> expectedList = List.of(
                new Staff(1, "chika", LocalDate.of(2000, 7, 1), "Tokyo"),
                new Staff(2, "airi", LocalDate.of(2005, 8, 13), "Meguro"),
                new Staff(3, "nanami", LocalDate.of(1998, 10, 25), "Kichijoji")
        );

        assertEquals(expectedList.size(), staffList.size());

        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getId(), staffList.get(i).getId());
            assertEquals(expectedList.get(i).getName(), staffList.get(i).getName());
            assertEquals(expectedList.get(i).getDateOfBirth(), staffList.get(i).getDateOfBirth());
            assertEquals(expectedList.get(i).getNearestStation(), staffList.get(i).getNearestStation());
        }
    }

    @Test
    @DataSet(cleanBefore = false)
    @Transactional
    void ユーザーが挿入できること() {
        Staff newStaff = new Staff(null, "Anna", LocalDate.of(2010, 1, 2), "Fukuoka");
        staffMapper.insert(newStaff);

        assertNotNull(newStaff.getId());

        Optional<Staff> insertedStaff = staffMapper.findById(newStaff.getId());
        assertTrue(insertedStaff.isPresent());
        assertEquals("Anna", insertedStaff.get().getName());
        assertEquals(LocalDate.of(2010, 1, 2), insertedStaff.get().getDateOfBirth());
        assertEquals("Fukuoka", insertedStaff.get().getNearestStation());
    }

    @Test
    @DataSet(value = "datasets/staff.yml", cleanBefore = true)
    @Transactional
    void ユーザーが更新できること() {
        Staff updatedStaff = new Staff(1, "chika_updated", LocalDate.of(2000, 7, 1), "Tokyo_updated");
        staffMapper.updateStaff(updatedStaff);

        Optional<Staff> staff = staffMapper.findById(1);
        assertTrue(staff.isPresent());
        assertEquals("chika_updated", staff.get().getName());
        assertEquals("Tokyo_updated", staff.get().getNearestStation());
    }

    @Test
    @DataSet(value = {"datasets/staff.yml"}, cleanBefore = true)
    @Transactional
    void ユーザーが削除できること() {
        staffMapper.deleteById(new Staff(1, null, null, null));

        Optional<Staff> deletedStaff = staffMapper.findById(1);
        assertFalse(deletedStaff.isPresent());
    }

}
