package com.example.roster10;

import java.time.LocalDate;

public class Staff {

    final Integer id;
    final String name;
    final LocalDate dateOfBirth;
    final String nearestStation;

    public Staff(Integer id, String name, LocalDate dateOfBirth, String nearestStation) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.nearestStation = nearestStation;
    }

    public static Staff createStaff(String name, LocalDate dateOfBirth, String nearestStation) {
        return new Staff(null, name, dateOfBirth, nearestStation);
    }

    public Integer getId() {
        return id;
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
