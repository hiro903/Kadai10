package com.example.roster10;

import java.time.LocalDate;

public class StaffRequest {

    private final String name;

    private final LocalDate dateOfBirth;
    private final String nearestStation;

    public StaffRequest(String name, LocalDate dateOfBirth, String nearestStation) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.nearestStation = nearestStation;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNearestStation() {
        return nearestStation;
    }
}
