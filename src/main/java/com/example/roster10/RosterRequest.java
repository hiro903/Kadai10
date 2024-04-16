package com.example.roster10;

import java.time.LocalDate;

public class RosterRequest {

    private String name;

    private LocalDate dateOfBirth;
    private String nearestStation;

    public RosterRequest(String name, LocalDate dateOfBirth, String nearestStation) {
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
