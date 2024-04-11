package com.example.roster10;

import java.time.LocalDate;

public class Roster {

    private Integer id;
    private String name;
    private LocalDate dateOfBirth;
    private String nearestStation;

    public Roster(Integer id, String name, LocalDate dateOfBirth, String nearestStation) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.nearestStation = nearestStation;
    }

    public static Roster createStaff(String name, LocalDate dateOfBirth, String nearestStation) {
        return new Roster(null, name, dateOfBirth, nearestStation);
    }
}
