package com.example.roster10.service;

import com.example.roster10.Staff;
import com.example.roster10.StaffMapper;
import com.example.roster10.StaffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StaffServiceTest {
    @InjectMocks
    StaffService staffService;

    @Mock
    StaffMapper staffMapper;

    @Test
    public void 存在するユーザーのIDを指定したときに正常にユーザーが返されること() {
        doReturn(Optional.of(new Staff(1, "erika", LocalDate.of(2000, 07, 01), "tokyo"))).when(staffMapper).findById(1);
        Staff actual = staffService.findStaff(1);
        assertThat(actual).isEqualTo(new Staff(1, "erika", LocalDate.of(2000, 07, 01), "tokyo"));
        verify(staffMapper, times(1)).findById(1);
    }

}
