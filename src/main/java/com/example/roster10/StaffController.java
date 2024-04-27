package com.example.roster10;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/staff")
    public ResponseEntity<Object> insert(@RequestBody StaffRequest staffRequest, UriComponentsBuilder uriBuilder) {
        Staff staff = staffService.insert(staffRequest.getName(), staffRequest.getDateOfBirth(), staffRequest.getNearestStation());
        URI location = uriBuilder.path("/staff/{id}").buildAndExpand(staff.getId()).toUri();
        StaffResponse body = new StaffResponse("staff created");
        return ResponseEntity.created(location).body(body);
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<Staff> getStaff(@PathVariable("id") int id) {
        return ResponseEntity.ok(staffService.findStaff(id));
    }

    @PutMapping("/staff/{id}")
    public ResponseEntity<Object> updateStaff(@PathVariable Integer id, @RequestBody StaffRequest staffRequest) {
        staffService.updateStaff(id, staffRequest.getName(), staffRequest.getDateOfBirth(), staffRequest.getNearestStation());
        return ResponseEntity.ok(new StaffResponse("Staff updated"));
    }

}
