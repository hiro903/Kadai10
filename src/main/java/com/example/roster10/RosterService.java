package com.example.roster10;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RosterService {
    private final RosterMapper rosterMapper;

    public RosterService(RosterMapper rosterMapper) {
        this.rosterMapper = rosterMapper;
    }

    public Roster insert(String name, LocalDate dateOfBirth, String nearestStation) {
        Roster roster = Roster.createStaff(name, dateOfBirth, nearestStation);
        rosterMapper.insert(roster);
        return roster;
    }

}
