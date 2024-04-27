package com.example.roster10;

import java.time.LocalDate;

public class Staff {

    private Integer id;
    private String name;
    private LocalDate dateOfBirth;
    private String nearestStation;

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

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }
}
