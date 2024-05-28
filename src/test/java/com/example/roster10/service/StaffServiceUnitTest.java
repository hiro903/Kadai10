package com.example.roster10.service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.roster10.Staff;
import com.example.roster10.StaffMapper;
import com.example.roster10.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

public class StaffServiceUnitTest {

    @Mock
    private StaffMapper staffMapper;

    @InjectMocks
    private StaffService staffService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsert() {
        Staff staff = Staff.createStaff("Anna", LocalDate.of(2001, 1, 1), "Tokyo");
        staffService.insert(staff.getName(), staff.getDateOfBirth(), staff.getNearestStation());

        verify(staffMapper, times(1)).insert(any(Staff.class));
    }
    @Test
    public void testFindStaff() {
        when(staffMapper.findById(1)).thenReturn(Optional.of(new Staff(1, "Anna", LocalDate.of(2001, 1, 1), "Tokyo")));

        Staff staff = staffService.findStaff(1);

        assertNotNull(staff);
        assertEquals("Anna", staff.getName());
    }
}
