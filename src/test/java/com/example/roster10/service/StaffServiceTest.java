package com.example.roster10.service;

import com.example.roster10.Staff;
import com.example.roster10.StaffMapper;
import com.example.roster10.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StaffServiceTest {

    @InjectMocks
    StaffService staffService;

    @Mock
    StaffMapper staffMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void 存在するユーザーのIDを指定したときに正常にユーザーが返されること() {
        Staff expectedStaff = new Staff(1, "erika", LocalDate.of(2000, 7, 1), "tokyo");
        doReturn(Optional.of(expectedStaff)).when(staffMapper).findById(1);

        Staff actual = staffService.findStaff(1);

        assertThat(actual).isEqualTo(expectedStaff);
        verify(staffMapper, times(1)).findById(1);
    }

    @Test
    public void 登録処理テスト() {
        Staff staff = Staff.createStaff("Anna", LocalDate.of(2001, 1, 1), "Tokyo");

        staffService.insert(staff.getName(), staff.getDateOfBirth(), staff.getNearestStation());

        verify(staffMapper, times(1)).insert(any(Staff.class));
    }
}


//    @Test
//    public void スタッフ検索テスト() {
//        when(staffMapper.findById(1)).thenReturn(Optional.of(new Staff(1, "Anna", LocalDate.of(2001, 1, 1), "Tokyo")));
//        Staff staff = staffService.findStaff(1);
//        assertNotNull(staff);
//        assertEquals("Anna", staff.getName());
//    }
