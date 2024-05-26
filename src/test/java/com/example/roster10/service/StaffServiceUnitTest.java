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
        Staff staff = Staff.createStaff("John Doe", LocalDate.of(1990, 1, 1), "Station A");
        staffService.insert(staff.getName(), staff.getDateOfBirth(), staff.getNearestStation());

        verify(staffMapper, times(1)).insert(any(Staff.class));
    }
}
